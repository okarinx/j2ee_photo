package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ModifyPhoto;
import com.company.project.service.ModifyPhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/12/20.
*/
@RestController
@RequestMapping("/modify/photo")
public class ModifyPhotoController {
    @Resource
    private ModifyPhotoService modifyPhotoService;

    @PostMapping("/add")
    public Result add(ModifyPhoto modifyPhoto) {
        modifyPhotoService.save(modifyPhoto);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        modifyPhotoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ModifyPhoto modifyPhoto) {
        modifyPhotoService.update(modifyPhoto);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ModifyPhoto modifyPhoto = modifyPhotoService.findById(id);
        return ResultGenerator.genSuccessResult(modifyPhoto);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        PageHelper.startPage(page, size);
        List<ModifyPhoto> list = modifyPhotoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
