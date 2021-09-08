package com.learn.threadImprove;

/**
 * 线程优先级
 *      1. 线程优先级用数字表示，范围从1-10
 *          1.1 Thread.MIN_PRIORITY = 1
 *          1.2 Thread.MAX_PRIORITY = 10
 *          1.3 Thread.NORM_PRIORITY = 5
 *      2. 使用以下方式改变或获取优先级
 *          getPriority()
 *          setPriority(int xxx)
 */
public class Priority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread t1 = new Thread(myPriority,"a");
        Thread t2 = new Thread(myPriority,"b");
        Thread t3 = new Thread(myPriority,"c");
        Thread t4 = new Thread(myPriority,"d");
        Thread t5 = new Thread(myPriority,"e");

        t1.start();
        t2.setPriority(1);
        t2.start();
        t3.setPriority(10);
        t3.start();
        t4.setPriority(-1);//会报错
        t4.start();
        t5.setPriority(11);//会报错
        t5.start();
    }
}

class MyPriority implements Runnable{


    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }
}
