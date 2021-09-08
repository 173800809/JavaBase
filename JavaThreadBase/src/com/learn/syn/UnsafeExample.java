package com.learn.syn;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程不安全示例
 *      ArrayList的线程不安全（下面的list.size()不一定是整数）
 */
public class UnsafeExample {
    public static void main(String[] args) throws InterruptedException {

        // 不安全示例
        List<String> unsafeList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()-> {
                try {
                    Thread.sleep(1);// 通过sleep放大异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                unsafeList.add(Thread.currentThread().getName());
            }).start();
        }


        // 安全示例
        CopyOnWriteArrayList<String> safeList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                safeList.add(Thread.currentThread().getName());
            }).start();
        }

        Thread.sleep(10000);
        System.out.println("不安全的ArrayList大小：" + unsafeList.size());
        System.out.println("安全的CopyOnWriteArrayList大小：" + safeList.size());

    }
}
