package com.li.learn.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * 锁的8个问题
 *      3. 增加了一个普通方法后，两个线程先打印 发短信 还是打电话 还是hello？ hello(普通方法)
 *      4. 两个对象两个同步方法，两个线程先打印 发短信 还是打电话？ 1.打电话 2.发短信
 * 原因：
 *      1. 普通方法不受锁的影响，所以先执行(获取锁会浪费时间)
 *      2. synchronized锁对象是方法的调用者
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        new Thread(()->{
            phone2.call();
        },"B").start();
        new Thread(()->{
            phone1.hello();
        },"C").start();
    }
}
class Phone2 {
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

    public void hello(){
        System.out.println("hello");
    }
}
