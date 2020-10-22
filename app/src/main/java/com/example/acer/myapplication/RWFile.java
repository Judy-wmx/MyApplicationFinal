package com.example.acer.myapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by acer on 2018/6/8.
 */

public class RWFile {
    //读txt文件
    public static String readFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int len=inputStream.available();
            byte []buffer=new byte[len];
            inputStream.read(buffer);
            inputStream.close();
            String content=new String(buffer);
            return content;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    //写txt文件
    public static void writeFile(String filename,String content) {
        File file = new File(filename);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
