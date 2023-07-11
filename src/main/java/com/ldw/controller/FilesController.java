package com.ldw.controller;


import cn.hutool.core.io.FileUtil;
import com.ldw.common.Result;
import com.ldw.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author HP刘德伟
 */
@RestController
public class FilesController {

    @Value("${server.ip}")
    private String serverIp;
    @Value("${server.port}")
    private String serverPort;
    @Value("${files.uploads.path}")
    private String filesUploadPath;


    @PostMapping("/uploads")
    public Result uploads(@RequestParam MultipartFile file) throws IOException {
        //获取文件类型 image/png
        String contentType = file.getContentType();
        //图片格式
        ArrayList<String> listType = new ArrayList<>();
        listType.add("image/png");
        listType.add("image/jpg");
        listType.add("image/jpeg");
        //视频格式
        listType.add("video/mp4");

        if (!listType.contains(contentType)){
            throw new CustomException("图片只允许png,jpg,jpeg格式，视频只允许mp4格式");
        }
        InputStream inputStream = file.getInputStream();
        //生成文件名 uuid
        String uuid = UUID.randomUUID().toString();
        //获取文件扩展名
        String fix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //生成文件完整名称
        String fileName = uuid + fix;
        //生成文件完整路径
        String absolutePath = filesUploadPath + fileName;

        File folder = new File(filesUploadPath);
        //若文件夹不存在则递归创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //写入到服务器
        int len = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
        while ((len = inputStream.read())!= -1){
            fileOutputStream.write(len);
        }
        fileOutputStream.close();
        inputStream.close();

        HashMap<Object, Object> map = new HashMap<>();
        map.put("url","http://"+serverIp+":"+serverPort+"/views?fileName="+fileName);
        map.put("alt",file.getOriginalFilename());
        map.put("realName",fileName);
        return Result.success(map);

    }

    @GetMapping("/views")
    public void views(@RequestParam String fileName, HttpServletResponse response) throws IOException {
       // System.out.println(fileName);
//        String str =fileName;
//        String str1 = str.substring( 0,str.length() - 1);
//        String[] split = str1.split("/");
        String[] split = fileName.split("/");
        String name = split[split.length - 1];
        //System.out.println(name);
        File file = new File(filesUploadPath + name);
        //社渚输出流格式
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(name,"UTF-8"));

        //任意类型的二进制流
        response.setContentType("application/octet-stream");
        //读取文件字节流
        outputStream.write(FileUtil.readBytes(file));
        outputStream.flush();
        outputStream.close();


    }

}
