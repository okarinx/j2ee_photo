package com.company.project.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/12/09.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final ResultCode SUCCESS = ResultCode.SUCCESS;
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject request) {
        String username = request.getString("username");
        String password = request.getString("password");
        User user = userService.getUserByName(username);

        if (user == null) {
            return ResultGenerator.genFailResult("用户不存在");
        } else if (!password.equals(user.getPassword())) {
            return ResultGenerator.genFailResult("密码错误");
        } else {
            String token = userService.updateTokenByName(username);
            JSONObject resData = new JSONObject();
            Result result = new Result();
            result.setCode(SUCCESS);
            result.setMessage("SUCCESS");
            resData.put("token", token);
            result.setData(resData);
            return result;
        }
    }

    @PostMapping("/getInfo")
    public Result getInfo(@RequestBody JSONObject request) {
        String token = request.getString("token");
        User user = userService.getUserByToken(token);
        if (user == null) {
            return new Result()
                    .setCode(ResultCode.UNAUTHORIZED)
                    .setMessage("登录过期，请重新登录");
        } else {
            JSONObject resData = new JSONObject();
            Result result = new Result();
            result.setCode(SUCCESS);
            result.setMessage("SUCCESS");
            resData.put("token", token);
            resData.put("name", user.getName());
            JSONArray role = new JSONArray();
            role.add(user.getRole());
            resData.put("roles", role);
            resData.put("avatar", "http://106.54.82.79:8110/avator/avator.png");
            result.setData(resData);

            return result;
        }
    }

    @GetMapping("/all_user")
    public Result getUserList() {
        List<User> users = userService.getUserList();

        return ResultGenerator.genSuccessResult(users);
    }

    @PostMapping("/update_role")
    public Result updateRole(@RequestParam String name) {
        User user = userService.updateRoleByName(name);
        return ResultGenerator.genSuccessResult();
    }

}
