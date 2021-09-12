package com.li.learn.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 锁的8个问题
 *      7. 增加1个静态同步方法，1个普通同步方法,只有一个对象；两个线程先打印 发短信 还是打电话？1.打电话 2.发短信
 *      8. 增加1个静态同步方法，1个普通同步方法，有两个对象；两个线程先打印 发短信 还是打电话？ 1.打电话 2.发短信
 * 原因：
 *      1. 因为static synchronized方法锁的是class，非static synchronized方法锁的是对象。所以是独立的，按序执行(谁执行快谁先出来)
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(()->{
            phone1.sendSms();
        },"A").start();
//        new Thread(()->{
//            phone1.call();
//        },"B").start();
        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}
class Phone4{
    public static synchronized void sendSms() {
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
