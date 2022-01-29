package com.wangyao.backend.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Upload {
    /**
     *
     * @param savePath 保存路径
     * @return 图片的名称
     */
    public  static String uploadFile(String savePath, MultipartFile multipartFile) {
        String filePath = savePath + multipartFile.getOriginalFilename();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getName());
        return file.getName();
    }
}
