package com.learn.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WebDownloader {
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
