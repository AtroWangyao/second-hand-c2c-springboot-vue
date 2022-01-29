package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.*;
import com.wangyao.backend.service.CommentService;
import com.wangyao.backend.service.GoodService;
import com.wangyao.backend.service.PictureService;
import com.wangyao.backend.service.UserService;
import com.wangyao.backend.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("good")
public class GoodController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @RequestMapping("/addGood")
    public String addGood(@RequestBody Good good) {
        System.out.println(good);
        int goodId = goodService.addGood(good); //此处good已获取到ID
        System.out.println(goodId);
        goodService.bindGoodAndTagByGood(good);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("goodId",goodId);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/deleteGood")
    public String deleteGood(@RequestBody Good good) {
        System.out.println(good);
        if(goodService.deleteGood(good) == true){
            return "suc";
        }else{
            return "def";
        }

    }
    @RequestMapping("/updateGood")
    public String updateGood(@RequestBody Good good)
    {
        System.out.println(good);
        if(goodService.updateGood(good) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allGood")
    public Object allGood()
    {
        List<Good> goodList = goodService.getAllGood();
        System.out.println(goodList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",goodList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/allGoodByUserId")
    public Object allGoodByUserId(@RequestParam("userId")int userId)
    {
        List<Good> goodList = goodService.getAllGoodByUserId(userId);
        System.out.println(goodList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",goodList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/allGoodWithUser")
    public Object allGoodWithUser()
    {
        List<Good> goodList = goodService.getAllGoodWithUser();
        System.out.println(goodList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",goodList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/getGood")
    public Object getGoodById(@RequestParam("goodId")int id){
        Good good = goodService.getGoodById(id);
        String categoryName = goodService.getCategoryById(good.getCategoryId()).getName();
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("good",good);
        res.put("categoryName",categoryName);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/getGoodDetail")
    public Object getGoodDetailById(@RequestParam("goodId")int id){
        Good good = goodService.getGoodById(id);
        List<Comment> commentList = commentService.getAllCommentByGoodId(id);
        String categoryName = goodService.getCategoryById(good.getCategoryId()).getName();
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("good",good);
        res.put("categoryName",categoryName);
        res.put("commentList",commentList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/getGoodAndUserAddressId")
    public Object getGoodAndUserAddressId(@RequestParam("goodId")int goodId){
        Good good = goodService.getGoodById(goodId);
        User user = userService.getUserById(good.getUserId());
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("good",good);
        res.put("userAddressId",user.getAddressId());
        return JSON.toJSONString(res);
    }
    @RequestMapping("/addCategory")
    public String addCategory(@RequestBody Category category)
    {
        System.out.println(category);
        if(goodService.addCategory(category) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(@RequestBody Category category)
    {
        System.out.println(category);
        if(goodService.updateCategory(category) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(@RequestBody Category category)
    {
        System.out.println(category);
        if(goodService.deleteCategory(category) == true)
        {
            System.out.println("suc");
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allCategory")
    public Object allCategory()
    {
        List<Category> categoryList = goodService.getAllCategory();
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",categoryList);
        return JSON.toJSONString(res);

    }
    @RequestMapping("/addTag")
    public String addTag(@RequestBody Tag tag)
    {
        System.out.println(tag);
        if(goodService.addTag(tag) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/updateTag")
    public String updateTag(@RequestBody Tag tag)
    {
        System.out.println(tag);
        if(goodService.updateTag(tag) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/deleteTag")
    public String deleteTag(@RequestBody Tag tag)
    {
        System.out.println(tag);
        if(goodService.deleteTag(tag) == true)
        {
            System.out.println("suc");
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allTag")
    public Object allTag()
    {
        List<Tag> tagList = goodService.getAllTag();
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",tagList);
        return JSON.toJSONString(res);

    }
    @RequestMapping("/getTagById")
    public Object getTagById(int id)
    {
        Tag tag = goodService.getTagById(id);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",tag);
        return JSON.toJSONString(res);

    }
    @RequestMapping("/deletePicture")
    public String deletePicture(@RequestParam("id")int id)
    {
        System.out.println(id);
        if(pictureService.deletePicture(new Picture(id)) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @Value("${upload-folder.content}")
    public String path;
    //商品图上传(主图,辅图),并且绑定商品与图片(通过中间表)
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] MultipartFile ,
                             @RequestParam("type")int[] type,
                             @RequestParam("goodId")int goodId) {
        System.out.println(MultipartFile.length + " " +goodId);
        for(int i=0;i<MultipartFile.length;i++)
        {
            String pictureName =  Upload.uploadFile(path,MultipartFile[i]); //图片的名字 就是 图片的src,此处上传图片到上传路径下
            Picture picture = new Picture(pictureName, type[i]);//封装一个picture用于添加
//            System.out.println(picture);
            pictureService.addPicture(picture);//此时picture已经获得了Id
            pictureService.bindGoodAndPictureByGoodIdAndPictureId(goodId,picture.getId());
        }
        return "suc";
    }

}
