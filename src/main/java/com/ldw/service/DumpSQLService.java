package com.ldw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DumpSQLService {

    @Autowired
    private SendEmailService sendEmailService;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 备份数据库方法
     * @param host
     * @param port
     * @param username
     * @param password
     * @param dbName
     * @param currentDate
     * @throws Exception
     */
    public void dataBaseDump(String host, String port, String username, String password, String dbName, String currentDate) throws Exception {
     //定义备份路径文件夹
        String path = "E:/数据库备份文件/";


    //如果该文件夹不存在，就自己创建
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        String fileName = dbName + currentDate + ".sql";

        String dataFile = path + fileName;
        System.out.println(dataFile);
        //拼接备份的cmd命令
        //String cmd="cmd /c mysqldump -h" + host + " -P" + port + " -u" + username + " -p" + password + " " + dbName + " > " + dataFile;
        String cmd="cmd /c "+"D:\\phpstudy_pro\\Extensions\\MySQL8.0.12\\bin\\mysqldump -h" + host + " -P" + port + " -u " + username + " -p" + password + " " + dbName + " > " + dataFile;
        System.out.println(cmd);
        try{
            Process exec = Runtime.getRuntime().exec(cmd);
            System.out.println(exec.toString());
            System.out.println("数据库备份成功");
        }catch(Exception e){
            System.out.println("数据库备份失败");
        }
      
    }

    /**
     * 定时发送数据库备份是否成功
     * @throws Exception
     */

    //@Scheduled(cron = "0 * * * * *")//每分钟发送
     @Scheduled(cron ="30 10 1 * * ?")//每天1点10分30秒触发任务,运行的情况下
    public void dump() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println(currentDate);
        dataBaseDump("localhost","3306","root","root","hamburgmall",currentDate);
        sendEmailService.sendSimpleEmail(from,"1244943676@qq.com","数据库备份",currentDate+"数据库备份成功");
    }
}