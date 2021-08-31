package com.learn;

import com.learn.utils.WebDownloader;

/**
 * 实现多线程方式1：继承Thead类
 * 启动线程：指类对象.start()
 */
public class ThreadCreate1 extends Thread {
    private String url;
    private String name;

    public ThreadCreate1(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        ThreadCreate1 t1 = new ThreadCreate1("https://cdn.cnbj1.fds.api.mi-img.com/middle.community.vip.bkt/905a9a6cf0ce92b42d01bdc4408c4610","图片1");
        ThreadCreate1 t2 = new ThreadCreate1("https://www.xiaomi.cn/post/30058678","图片2");
        ThreadCreate1 t3 = new ThreadCreate1("https://www.xiaomi.cn/post/30057843","图片3");
        t1.start();
        t2.start();
        t3.start();
    }
}

