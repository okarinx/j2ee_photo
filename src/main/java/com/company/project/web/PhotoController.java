package com.company.project.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.model.*;
import com.company.project.service.FavorsService;
import com.company.project.service.ModifyPhotoService;
import com.company.project.service.PhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.format.annotation.DateTimeFormat;
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
    @Resource
    private ModifyPhotoService modifyPhotoService;
    @Resource
    private FavorsService favorsService;

    @CrossOrigin
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer photoId,@RequestParam String userName) {
        // 删除图片
        Photo oldphoto = photoService.findById(photoId);
        String coverName = oldphoto.getFileUrl().substring(31);
        System.out.println(coverName);
        String destFileName = "/var/www/html/image/" + coverName;
        File preDelFile = new File(destFileName);
        if (preDelFile.exists())
            preDelFile.getAbsoluteFile().delete();
        // 删除收藏信息
        favorsService.deleteById(photoId);
        // 删除photo表中信息
        photoService.deleteById(photoId);

        //记录操作
        ModifyPhoto modifyPhoto=new ModifyPhoto();
        modifyPhoto.setPhotoId(photoId);
        modifyPhoto.setOperation("删除");
        modifyPhoto.setUserId(userName);
        modifyPhoto.setBjsOperate(new Date());
        modifyPhotoService.save(modifyPhoto);
        return ResultGenerator.genSuccessResult();
    }

    @CrossOrigin
    @PostMapping("/delete_list")
    public Result delete(@RequestBody JSONObject request) {
        List<Integer> photoIdList= (List<Integer>) request.get("photoIdList");
        String userName = request.getString("userName");
        for(int i =0;i<photoIdList.size();i++) {
            int photoId = photoIdList.get(i);
            // 删除图片
            Photo oldphoto = photoService.findById(photoId);
            String coverName = oldphoto.getFileUrl().substring(31);
            System.out.println(coverName);
            String destFileName = "/var/www/html/image/" + coverName;
            File preDelFile = new File(destFileName);
            if (preDelFile.exists())
                preDelFile.getAbsoluteFile().delete();
            // 删除收藏信息
            favorsService.deleteById(photoId);
            // 删除photo表中信息
            photoService.deleteById(photoId);

            //记录操作
            ModifyPhoto modifyPhoto = new ModifyPhoto();
            modifyPhoto.setPhotoId(photoId);
            modifyPhoto.setOperation("删除");
            modifyPhoto.setUserId(userName);
            modifyPhoto.setBjsOperate(new Date());
            modifyPhotoService.save(modifyPhoto);
        }

        return ResultGenerator.genSuccessResult();
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
                                        @RequestParam(defaultValue = "%%") String scale,
                                        @RequestParam(defaultValue = "1970-01-01") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateBegin,
                                        @RequestParam(defaultValue = "2100-01-01") @DateTimeFormat(pattern="yyyy-MM-dd")  Date dateEnd) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(Photo.class);
        condition.createCriteria()
                .andLike("displayName", '%' + displayName + '%')
                .andLike("scale", scale)
                .andLike("country", country)
                .andLessThanOrEqualTo("bjsTakePhoto",dateEnd)
                .andGreaterThanOrEqualTo("bjsTakePhoto",dateBegin);
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

        // 生成图片名
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
        // 插入图片
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

        // 记录操作
        ModifyPhoto modifyPhoto = new ModifyPhoto();
        modifyPhoto.setBjsOperate(new Date());
        modifyPhoto.setOperation("新建图片");
        modifyPhoto.setUserId(uploaderName);
        modifyPhoto.setPhotoId(photoService.getLastId() + 1);
        modifyPhotoService.save(modifyPhoto);


        return ResultGenerator.genSuccessResult();
    }

    @CrossOrigin
    @PostMapping("/update_photo")
    public Result updatePhoto(@RequestParam(required = false) MultipartFile cover,
                              @RequestParam int photoId,
                              @RequestParam String displayName,
                              @RequestParam String country,
                              @RequestParam String position,
                              @RequestParam String coordinates,
                              @RequestParam String scale,
                              @RequestParam String uploaderName) {


        // 更新信息
        Photo photo = new Photo();
        photo.setId(photoId);
        photo.setDisplayName(displayName);
        photo.setCountry(country);
        photo.setPosition(position);
        photo.setCoordinates(coordinates);
        photo.setScale(scale);
        photo.setUploaderName(uploaderName);
        Date date = new Date();
        photo.setBjsTakePhoto(date);
        try {
            photoService.update(photo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("更新失败");
        }

        if (cover != null) {
            // 更新图片覆盖原有的图片
            Photo oldphoto = photoService.findById(photoId);
            String coverName = oldphoto.getFileUrl().substring(31);
            System.out.println(coverName);
            String destFileName = "/var/www/html/image/" + coverName;
            File preDelFile = new File(destFileName);
            if (preDelFile.exists())
                preDelFile.getAbsoluteFile().delete();
            File destFile = new File(destFileName);
            try {
                cover.transferTo(destFile);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result()
                        .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                        .setMessage("更新文件失败");
            }
        }

        // 记录操作
        ModifyPhoto modifyPhoto = new ModifyPhoto();
        modifyPhoto.setBjsOperate(new Date());
        modifyPhoto.setOperation("修改");
        modifyPhoto.setUserId(uploaderName);
        modifyPhoto.setPhotoId(photoId);
        modifyPhotoService.save(modifyPhoto);


        return ResultGenerator.genSuccessResult();
    }

    @CrossOrigin
    @GetMapping("/favor_photo_list")
    public Result favorPhotoList(@RequestParam String userName) {

        List<Photo> photos = photoService.getFavorPhotoList(userName);

        return ResultGenerator.genSuccessResult(photos);
    }
}
