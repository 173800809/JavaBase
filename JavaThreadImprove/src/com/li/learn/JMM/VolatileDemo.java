package com.li.learn.JMM;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile
 *      1. 保证可见性：不同线程操作的主存变量会一致(不会单独每个线程的工作内存)
 *      2. 不保证原子性：
 *          如何保证原子性：使用原子类-java.util.concurrent.atomic包下的类
 *      3. 保证指令不被重排(因为使用CPU指令：内存屏障)
 *          a. 解释：源代码->编译器优化的重排->指令并行也可能会重排->内存系统也会重排->执行
 *          b. 补充：处理器在进行指令重排的时候，会考虑数据之间的依赖性
 *          c. 例如：a b x y的4个值默认都是0。结果可能是x=0,y=0(原因就是因为指令重排)
 *              线程A    线程B
 *              x=a      y=b
 *              b=1      a=2
 *
 * 补充：下面代码的目的就是让main线程最后才执行
 *         yield():释放我的CPU时间片(比如某个时间段,虽然我获得了cpu的执行权,但是并不满足执行的条件, 把cpu的执行权让给了其他的线程.)
 *         while(Thread.activeCount() > 2) {// 每个main函数最少两个线程：main和gc
 *             // 这个属于main线程
 *             Thread.yield();
 *         }
 */
public class VolatileDemo {

    private volatile static int num = 0;
    // 原子类没有自动拆装箱功能
    private volatile static AtomicInteger cnt = new AtomicInteger();

    // 非原子类加
    public static void add(){
        num++;
    }
    // 原子类加(自加1)
    public static void addOfAtomic(){
        cnt.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. 保证可见性测试
//        canSee();
        // 2. 不保证原子性测试
//        unAtomic();

        // 补充. 原子类保证原子性试验
        atomic();

    }

    private static void atomic() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    addOfAtomic();
                }
            }).start();
        }

        // yield():释放我的CPU时间片(比如某个时间段,虽然我获得了cpu的执行权,但是并不满足执行的条件, 把cpu的执行权让给了其他的线程.)
        while(Thread.activeCount() > 2) {// 每个main函数最少两个线程：main和gc
            // 这个属于main线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + cnt);
    }

    private static void unAtomic() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        // yield():释放我的CPU时间片(比如某个时间段,虽然我获得了cpu的执行权,但是并不满足执行的条件, 把cpu的执行权让给了其他的线程.)
        while(Thread.activeCount() > 2) {// 每个main函数最少两个线程：main和gc
            // 这个属于main线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }

    private static void canSee() throws InterruptedException {
        new Thread(() -> {
            while (num == 0){

            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        num = 1;
        System.out.println(num);
    }
}
