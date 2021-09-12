package com.li.learn.producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Condition唤醒指定对象。
 *      Condition和对象对应的判断值都需要独立
 */
public class ConditionImprove {
    public static void main(String[] args) {
        Data1 data1 = new Data1();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) data1.printA();
        },"A").start();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) data1.printB();
        },"B").start();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) data1.printC();
        },"C").start();

    }
}

class Data1{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;// 1A,2B,3C

    public void printA(){
        lock.lock();
        try{
            while(number != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAA");
            // 唤醒指定的人B
            number =2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try{
            while(number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBB");
            // 唤醒指定的人C
            number =3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            while(number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCC");
            // 唤醒指定的人B
            number =1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}