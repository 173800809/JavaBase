package com.li.learn.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1. Thread启动的几种方式
 *      1.1 new Thread(new Runnable()).start()
 *      1.2 new Thread(new FutureTask<V>()).start()
 *      1.3 new Thread(new FutureTask<V>(Callable)).start()
 * 2. FutureTask(适配类，适配Callable和Thread)
 *      2.1 FutureTask的get可能会产生阻塞，需要把他放到最后，或者使用异步通信来处理
 *      2.2 FutureTask的get结果会有缓存
 *      补充：FutureTask只会执行一次的原因(下面B线程不会执行)：一旦计算完成，则无法重新启动或取消计算（除非使用runAndReset()调用计算 ）
 */
public class CallableTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        try {
            Integer o= (Integer)futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() +"执行call()");
        Thread.sleep(1000);
        return 100;
    }
}
