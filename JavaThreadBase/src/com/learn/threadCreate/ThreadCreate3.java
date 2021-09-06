package com.learn.threadCreate;

import com.learn.utils.WebDownloader;

import java.util.concurrent.*;

/**
 * 实现多线程方式2：实现Callable接口
 * 启动线程：
 *      创建执行服务：ExecutorService ser = Executors.newFixedThreadPooL(1);
 *      提交执行：Future<Boolean> result1 = ser.submit(t1);
 *      获取结果：boolean r1 = result.get()
 *      关闭服务：ser.shutdownNow()
 *
 * 补充：option+回车：是idea自动处理(包括异常、导报)
 */
public class ThreadCreate3 implements Callable<Boolean> {
    private String url;
    private String name;

    public ThreadCreate3(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call(){
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载文件名为:"+name);
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadCreate3 t1 = new ThreadCreate3("https://cdn.cnbj1.fds.api.mi-img.com/middle.community.vip.bkt/905a9a6cf0ce92b42d01bdc4408c4610","图片1");
        ThreadCreate3 t2 = new ThreadCreate3("https://www.xiaomi.cn/post/30058678","图片2");
        ThreadCreate3 t3 = new ThreadCreate3("https://www.xiaomi.cn/post/30057843","图片3");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        // 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        ser.shutdownNow();

    }
}
