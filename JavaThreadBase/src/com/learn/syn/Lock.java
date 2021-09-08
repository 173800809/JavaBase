package com.learn.syn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁
 *      1. 从JDK5.0开始，java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当
 *      2. java.util.concurrent.locks.Lock接口是控制多个线程对共享资源进行访问的工具。锁提供了对共享资源的独占访问，
 *      每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象
 *      3. ReentrantLock类实现了Lock，它拥有与synchronized相同的并发性和内存语义，在实现线程安全的控制中，
 *      比较常用的是ReentrantLock，可以显示加锁、释放锁
 * synchronized与Lock的对比
 *      1. Lock是显示锁(手动开启和关闭锁，要记得关闭)；synchronized是隐式锁，出了作用域自动释放锁
 *      2. Lock只有代码块锁，synchronized有代码块锁和方法锁
 *      3. 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性(提供更多的子类)
 *      4. 优先使用顺序
 *          Lock > 同步代码块(已经进入方法体，分配了相应资源) > 同步方法(在方法体之外)
 */
public class Lock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
    }
}
class Ticket implements Runnable{

    int ticketNums = 10;
    /**
     * 锁需要放在run方法以外，为什么？
     *      尝试解释理由：在run方法以外是加在此共享类上面的锁(之前synchronized块，获取obj的问题)
      */
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        lock.lock();
        try {
            while (true) {
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
