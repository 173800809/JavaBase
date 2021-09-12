package com.li.learn.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock(读写锁)
 *      1. 独占锁(写锁):一次只能被一个线程占用
 *      2. 共享锁(读锁):多个线程可以同时占有
 *      补充：
 *          读-读：可以共存
 *          读-写：不能共存
 *          写-写：不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCacheLock = new MyCacheLock();

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() ->{
                myCacheLock.put(String.valueOf(temp),String.valueOf(temp));
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(String.valueOf(temp));
            },String.valueOf(temp)).start();
        }
    }
}
class MyCacheLock{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
