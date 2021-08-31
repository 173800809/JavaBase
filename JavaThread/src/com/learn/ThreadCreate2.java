package com.learn;

import com.learn.utils.WebDownloader;

/**
 * 实现多线程方式2：实现Runnable接口
 * 启动线程：传入目标对象+Thread对象.start
 *
 * 补充：获取当前线程名：Thread.currentThread().getName()
 */
public class ThreadCreate2 implements Runnable{
    private String url;
    private String name;

    public ThreadCreate2(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run(){
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("当前线程名:"+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadCreate2 t1 = new ThreadCreate2("https://cdn.cnbj1.fds.api.mi-img.com/middle.community.vip.bkt/905a9a6cf0ce92b42d01bdc4408c4610","图片1");
        ThreadCreate2 t2 = new ThreadCreate2("https://www.xiaomi.cn/post/30058678","图片2");
        ThreadCreate2 t3 = new ThreadCreate2("https://www.xiaomi.cn/post/30057843","图片3");

        new Thread(t1, "线程1").start();
        new Thread(t2, "线程2").start();
        new Thread(t3, "线程3").start();
    }
}


