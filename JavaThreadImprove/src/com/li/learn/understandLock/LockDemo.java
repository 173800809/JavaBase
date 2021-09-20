package com.li.learn.understandLock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ReentrantLock锁的理解
 *      1. 公平锁、非公平锁：ReentrantLock默认是非公平锁，可以通过构造方法设置为公平锁
 *      2. 可重入锁(递归锁)：synchronized只有一个锁，ReentrantLock可以设置多个锁(lock)，并要等价有多个解锁(unlock)
 *      3. 自旋锁(如下面代码示例)：只会在加锁时需要自旋锁。如果没有获得锁就一直自旋直到等到锁
 *      4. 死锁：定位死锁问题的步骤(查看堆栈信息)
 *          a. 定位进程号：jps -l
 *          b. 找到死锁问题：jstack 进程号
 *
 */
public class LockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myLock");

        // 自旋锁(为了等待锁)
        while(!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myUnlock");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo lock = new LockDemo();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }
        },"T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }
        },"T2").start();
    }
}
