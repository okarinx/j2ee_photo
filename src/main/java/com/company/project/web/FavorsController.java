package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Favors;
import com.company.project.service.FavorsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/12/17.
 */
@RestController
@RequestMapping("/favors")
public class FavorsController {
    @Resource
    private FavorsService favorsService;

    @PostMapping("/add")
    public Result add(Favors favors) {
        favorsService.save(favors);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        favorsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Favors favors) {
        favorsService.update(favors);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Favors favors = favorsService.findById(id);
        return ResultGenerator.genSuccessResult(favors);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Favors> list = favorsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @CrossOrigin
    @GetMapping("/favors")
    public Result favors(@RequestParam String userName, @RequestParam int photoId) {

        String r = favorsService.setFavors(userName, photoId);

        return new Result().setCode(ResultCode.SUCCESS)
                            .setMessage(r);
    }
}
