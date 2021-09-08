package com.learn.threadState;

/**
 * 1.线程方法
 *      1.1 setPriority(int newPriority): 更改线程的优先级
 *      1.2 static void sleep(long millis)：在指定的毫秒数内让当前正在执行的线程休眠
 *      1.3 void join()：等待该线程终止
 *      1.4 static void yield()：暂停当前正在执行的线程对象，并执行其他线程
 *      1.5 void interrupt()：中断线程(废弃)，别用这个方式
 *      1.6 boolean isAlive()：测试线程是否处于活动状态
 * 2. 线程的状态
 *      2.1 NEW：尚未启动的线程处于此状态
 *      2.2 RUNNABLE：在Java虚拟机中执行的线程处于此状态
 *      2.3 BLOCKED：被阻塞等待监视器的线程处于此状态
 *      2.4 WAITING：正在等待另一个线程执行特定动作的线程处于此状态
 *      2.5 TIMED_WAITING：正在等待另一个线程执行动作到达指定等待时间的线程处于此状态
 *      2.6 TERMINATED：已退出的线程处于此状态
 */
public class State {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("///");
        });

        // 观察初始状态
        Thread.State state = thread.getState();
        System.out.println(state);

        // 观察启动后
        thread.start();// 启动线程
        state = thread.getState();
        System.out.println(state);

        while(state != Thread.State.TERMINATED) {// 只要线程不终止，就一直输出状态
            Thread.sleep(1000);
            state = thread.getState();// 更新线程状态
            System.out.println(state);
        }
    }
}
