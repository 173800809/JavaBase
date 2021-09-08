package com.learn.threadState;

/**
 * 合并线程：
 *      概念：待此线程执行完成后，再执行其他线程。本线程执行过程中，其他线程阻塞。（插队）
 */
public class Join implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP来了"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Join join = new Join();
        Thread thread = new Thread(join);
        thread.start();

        for (int i = 0; i < 50; i++) {
            System.out.println("main线程"+i);
            thread.join();
        }
    }
}
