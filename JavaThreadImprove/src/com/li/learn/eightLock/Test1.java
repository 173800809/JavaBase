package com.li.learn.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 锁的8个问题
 *      1. 标准情况下，两个线程先打印 发短信 还是打电话？ 1.发短信，2.打电话
 *      2. sendSms延迟4秒，两个线程先打印 发短信 还是打电话？ 1.发短信，2.打电话
 * 原因：synchronized锁的是方法的拥有者，两个方法用的是同一个锁，谁先拿到谁先执行。因为phone的sendSms在main函数中被先调用，所以先执行
 */
public class Test1 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone1 {
    // synchronized锁的是方法的拥有者
    // 两个方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
