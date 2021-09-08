package com.learn.threadState;

/**
 * 停止线程：
 *      1. 不推荐使用JDK提供的stop()、destroy()方法。已废弃
 *      2. 推荐线程自己停下来：
 *          2.1 建议使用一个标志位进行终止变量，当flag=false，则终止线程运行
 *          2.2 利用次数
 *
 * 补充1：Java的线程是不允许启动两次的，第二次调用必然会抛出IllegalThreadStateException
 *      比如：下方的
 *             t.start();
 *             t.start();
 *           第一个会一直执行，第二个会报错。
 *      注意这样特殊场景
 * 补充2：多线程中，一个线程出现异常，另一个线程会正常执行
 */
public class Stop implements Runnable{

    private boolean flag = true;

    @Override
    public void run(){
        int i = 0;
        while(this.flag){
            System.out.println("子线程执行了" + i++ +"次");
        }
    }

    private void stop(){
        this.flag = false;
    }


    public static void main(String[] args) {
        Stop stop = new Stop();
        Thread t = new Thread(stop);
        try {
            t.start();
            t.start();
        }catch (IllegalThreadStateException e){
            System.out.println("有异常");
            stop.stop();
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("main线程跑了" + i +"次");
            if(i == 90) {
                stop.stop();
                System.out.println("子线程该停止了");
            }
        }
    }
}
