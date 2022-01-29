package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.Certification;
import com.wangyao.backend.bean.Picture;
import com.wangyao.backend.bean.User;
import com.wangyao.backend.bean.UserAddress;
import com.wangyao.backend.dao.GoodDao;
import com.wangyao.backend.service.PictureService;
import com.wangyao.backend.service.UserService;
import com.wangyao.backend.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @RequestMapping("/add")
    public String addUser(@RequestBody User user)
    {
        System.out.println(user);
        if(userService.addUser(user) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/update")
    public String updateUser(@RequestBody User user)
    {
        System.out.println(user);
        if(userService.updateUser(user) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/delete")
    public String deleteUser(@RequestBody User user)
    {
        System.out.println(user);
        if(userService.deleteUser(user) == true)
        {
            System.out.println("suc");
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/getUserById")
    public Object getUserById(@RequestParam("userId")int userId) {
        User user = userService.getUserById(userId);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data", user);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/allUser")
    public Object allUser()
    {
        List<User> userList = userService.getAllUser();
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",userList);
        return JSON.toJSONString(res);

    }
    @RequestMapping("/getCertification")
    public Object getCertificationByUserId(@RequestParam("userId")int userId)
    {
        Certification certification = userService.getAllCertificationByUserId(userId);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",certification);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/addCertification")
    public String addUser(@RequestBody Certification certification)
    {
        System.out.println(certification);
        if(userService.addCertification(certification) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/updateCertification")
    public String updateCertification(@RequestBody Certification certification)
    {
        System.out.println(certification);
        if(userService.updateCertification(certification) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    //用户头像上传
    @Value("${upload-folder.content}")
    public String path;
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile,@RequestParam("type") int type) {
        System.out.println(type);
        String pictureName =  Upload.uploadFile(path,multipartFile); //图片的名字 就是 图片的src,此处上传图片到上传路径下
        Picture picture = new Picture(pictureName, type);//封装一个picture用于添加,因为用户上传的肯定是头像(或者身份证),所以这里type是3或者4
//        System.out.println(picture);
        pictureService.addPicture(picture);//此时picture已经获得了Id(其实这个picture插入并没有什么用...因为用户的头像路径时保存在用户表的....算了,先写上吧)
        return pictureName; //把图片名字(路径)传给前台,让前台把这个封装在用户表单里.
    }
    //用户身份证上传
    @PostMapping("/uploadIDCard")
    public String uploadIDCard(@RequestParam("file") MultipartFile multipartFiles[]) {
        System.out.println(multipartFiles);
        HashMap<String, Object> res = new HashMap<String, Object>();
        String pictureName;
        for(int i=0;i<multipartFiles.length;i++){
            pictureName = Upload.uploadFile(path,multipartFiles[i]);//图片的名字 就是 图片的src,此处上传图片到上传路径下
            res.put("pictureName"+Integer.toString(i) ,pictureName);
            Picture picture = new Picture(pictureName, 4);//封装一个picture用于添加,因为用户上传的肯定是头像(或者身份证),所以这里type是3或者4
            pictureService.addPicture(picture);//此时picture已经获得了Id(其实这个picture插入并没有什么用...因为用户的头像路径时保存在用户表的....算了,先写上吧)
        }
        return JSON.toJSONString(res);
    }

}




