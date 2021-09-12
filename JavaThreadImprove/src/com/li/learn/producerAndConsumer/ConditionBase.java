package com.li.learn.producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 多线程时可能会存在"虚假唤醒"，所以"wait"需要写在循环(while)中，避免wait后面的代码被执行
 * 2. Condition的方法，等待对应的是await(), 唤醒对应的是signal():唤醒一个 和signalAll():唤醒全部
 */
public class ConditionBase {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.decrement();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.increment();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.decrement();
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) data.increment();
        }, "D").start();


    }

}

// 需要面向OOP编程
class Data {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {

        lock.lock();
        try {
            /**
             * 需要使用while
             */
            while (number != 0) {
                /**
                 * 方法注意
                 */
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            /**
             * 需要使用while
             */
            while (number == 0) {
                /**
                 * 方法注意
                 */
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
