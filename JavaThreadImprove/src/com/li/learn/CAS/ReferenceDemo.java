package com.li.learn.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用
 *      1. 原理：使用乐观锁(在原有CAS的基础上 + 版本号<等价于下面方法的Stamp>)
 *      2. 问题：如果xxx是在-128和127以外的基础类型，则compareAndSet会执行失败 - new AtomicStampedReference<>(xxx,1);
 *         a. 原因：Integer对象是在IntegerCache.cache产生，会复用已有对象，这个区间内的Integer对象可以直接使用==进行判断。
 *                 但是这个区间以外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑。
 *
 */
public class ReferenceDemo {
    static Integer num = 2000;

    static AtomicStampedReference<Integer> atomicStampedReference = new
            AtomicStampedReference<>(num,1);

    public static void main(String[] args) {
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("a1 -> "+ stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(num,
                                                 num + 100,
                                                 atomicStampedReference.getStamp(),
                                                 atomicStampedReference.getStamp() + 1);

            System.out.println("a2 -> "+ atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(num,
                                                                    num - 100,
                                                                    atomicStampedReference.getStamp(),
                                                                    atomicStampedReference.getStamp() + 1));
            System.out.println("a3 -> "+atomicStampedReference.getStamp());
        },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1 -> "+ stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(atomicStampedReference.compareAndSet(num,
                                                                    num + 1000,
                                                                    stamp,
                                                                    stamp + 1));
            System.out.println("b2 -> "+atomicStampedReference.getStamp());
        },"b").start();
    }
}
