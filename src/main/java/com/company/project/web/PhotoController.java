package com.company.project.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Photo;
import com.company.project.model.User;
import com.company.project.service.PhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.company.project.core.ResultCode.SUCCESS;

/**
 * Created by CodeGenerator on 2019/12/16.
 */
@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Resource
    private PhotoService photoService;

    @PostMapping("/add")
    public Result add(Photo photo) {
        photoService.save(photo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        photoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Photo photo) {
        photoService.update(photo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Photo photo = photoService.findById(id);
        return ResultGenerator.genSuccessResult(photo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Photo> list = photoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @CrossOrigin
    @PostMapping("/new_photo")
    public Result newPhoto(@RequestParam MultipartFile cover,
                           @RequestParam String displayName,
                           @RequestParam String country,
                           @RequestParam String position,
                           @RequestParam String coordinates,
                           @RequestParam String scale,
                           @RequestParam String uploaderName) {

        String coverName = UUID.randomUUID().toString() + ".jpg";
        String destFileName = "/var/www/html/image/" + coverName;
        File destFile = new File(destFileName);
        try {
            cover.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result()
                    .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                    .setMessage("上传文件失败");
        }
        Photo photo = new Photo();
        photo.setDisplayName(displayName);
        photo.setCountry(country);
        photo.setPosition(position);
        photo.setCoordinates(coordinates);
        photo.setScale(scale);
        photo.setFileUrl("http://106.54.82.79:8110/image/"+coverName);
        photo.setUploaderName(uploaderName);
        Date date = new Date();
        photo.setBjsTakePhoto(date);
        photoService.save(photo);

        return ResultGenerator.genSuccessResult();
    }
}
