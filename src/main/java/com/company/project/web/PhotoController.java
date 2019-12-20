package com.company.project.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Photo;
import com.company.project.model.PhotoDetail;
import com.company.project.model.SortItem;
import com.company.project.model.User;
import com.company.project.service.PhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;

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
    @PostMapping("/photo_list_by_condition")
    public Result getConditionPhotoList(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "0") Integer size,
                                     @RequestParam(defaultValue = "") String displayName,
                                     @RequestParam(defaultValue = "%%") String country,
                                     @RequestParam(defaultValue = "%%") String scale) {
        System.out.println(displayName+country+scale);
        PageHelper.startPage(page, size);
        Condition condition = new Condition(Photo.class);
        condition.createCriteria()
                .andLike("displayName",'%'+displayName+'%')
                .andLike("scale",scale)
                .andLike("country",country);
        condition.orderBy("id").asc();
        List<Photo> list = photoService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @CrossOrigin
    @GetMapping("/country_list")
    public Result getCountryList() {

        List<SortItem> list = photoService.getCountryList();
        return ResultGenerator.genSuccessResult(list);
    }

    @CrossOrigin
    @GetMapping("/scale_list")
    public Result getScaleList() {

        List<SortItem> list = photoService.getScaleList();
        return ResultGenerator.genSuccessResult(list);
    }

    @CrossOrigin
    @GetMapping("/photo_detail")
    public Result newPhoto(@RequestParam String userName,
                           @RequestParam int photoId) {

        PhotoDetail photoDetail = photoService.getPhotoDetail(photoId, userName);

        return ResultGenerator.genSuccessResult(photoDetail);
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
        photo.setFileUrl("http://106.54.82.79:8110/image/" + coverName);
        photo.setUploaderName(uploaderName);
        Date date = new Date();
        photo.setBjsTakePhoto(date);
        photoService.save(photo);

        return ResultGenerator.genSuccessResult();
    }


}
