package com.li.learn.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS-原子类
 *      1. 原理：Compare and set（比较并交换）
 *          a. 底层方法：public final boolean compareAndSet(int except, int update)
 *          b. 补充：CAS是CPU的并发原语
 *          c. 实现流程：比较当前工作内存中的值和主内存中的值，如果这个值是期望的，那么则执行操作！如果不是就一直循环
 *      2. 缺点：
 *          a. 循环会耗时(自旋锁)
 *          b. 一次性只能保证一个共享变量的原子性
 *          c. ABA问题
 */
public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }
}
