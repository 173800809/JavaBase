package com.li.learn.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue(阻塞队列)
 *      1. 方法       test1(抛出异常)    test2(有返回值，不抛出异常)    test3(阻塞等待)    test4(超时等待)
 *         添加           add               offer                      put      offer( , 时间值, 时间单位)
 *         移除         remove              poll                      take        poll(时间值, 时间单位)
 *      检测队首元素     element             peek
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
//        test4();
    }
    public static void test1(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        /**
         * 会抛出异常
         */
//        System.out.println(blockingQueue.add("d"));
        System.out.println("-------------------------------");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        /**
         * 会抛出异常
         */
        System.out.println(blockingQueue.remove());
    }
    public static void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        /**
         * 不会抛出异常，只会返回false
         */
        System.out.println(blockingQueue.offer("d"));
        System.out.println("-------------------------------");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        /**
         * 不会抛出异常，只会返回false
         */
        System.out.println(blockingQueue.poll());
    }
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        /**
         * 代码会一直阻塞
         */
        blockingQueue.put("d");
        System.out.println("-------------------------------");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        /**
         * 代码会一直阻塞
         */
        System.out.println(blockingQueue.take());
    }
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        /**
         * 代码会在超时时间之后，结束阻塞
         */
        blockingQueue.offer("d",2, TimeUnit.SECONDS);
        System.out.println("-------------------------------");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        /**
         * 代码会在超时时间之后，结束阻塞
         */
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
    }
}
