package com.li.learn.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 锁的8个问题
 *      5. 增加两个静态同步方法，只有一个对象；两个线程先打印 发短信 还是打电话？1.发短信 2.打电话
 *      6. 增加两个静态同步方法，有两个对象；两个线程先打印 发短信 还是打电话？ 1.发短信 2.打电话
 * 原因：
 *      1. static synchronized锁的是class类，一个java程序中同一个class类有多个对象，但是只有一个class类。所以执行顺序是调用顺序
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();
        new Thread(() -> {
            phone1.call();
        }, "A").start();
        new Thread(() -> {
            phone2.call();
        }, "B").start();

    }

}

class Phone3 {
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }
}

