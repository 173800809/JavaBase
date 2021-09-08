package com.learn.threadImprove;

/**
 * 守护线程(daemon)
 *      1. 线程分为用户线程和守护线程
 *      2. 虚拟机必须确保用户线程执行完毕
 *      3. 虚拟机不用等待守护线程执行完毕（用户线程执行完毕，虚拟机就会收到停止）
 *      4. 守护线程举例：后台记录操作日志、监控内存、垃圾回收等
 *      5. 线程默认是用户线程
 *
 */
public class Daemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread t1 = new Thread(god);
        t1.setDaemon(true);// 默认是false表示是用户线程
        t1.start();

        new Thread(you).start();
    }
}

class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝保佑着你");
        }
    }
}

class You implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 36500; i++) {
            System.out.println("你每天都开心的活着");
        }
    }
}