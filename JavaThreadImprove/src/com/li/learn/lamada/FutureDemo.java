package com.li.learn.lamada;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Future(异步回调)
 * 1. 初衷：对将来的某个事件的结果进行建模
 * 2. CompletableFuture(异步调用)的方法
 *      a. 异步执行
 *      b. 成功回调  T CompletableFuture.supplyAsync
 *      c. 失败回调  U CompletableFuture.supplyAsync
 * 补充：异步回调的结果要写到代码的最后，不然起不到异步回调的作用
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的 runAsync 异步回调
//        runAsync();

        // 有返回值的 supplyAsync 异步回调(例如ajax，成功和失败的回调)
        supplyAsync();

        return;
    }

    private static void supplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync => Integer");
            int i = 10 / 0;
            return 1024;
        });

        /**
         * t是正常执行结果：CompletableFuture.supplyAsync的返回值
         * u是异常执行结果：就是报错信息
         */
        Integer result =  completableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t);
            System.out.println("u=>" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; // 可以获得错误的返回结果(比如200，或401)
        }).get();

        System.out.println(result);
    }

    private static void runAsync() throws InterruptedException, ExecutionException {
        // 没有返回值的 runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync => void");
        });

        /**
         * 下面代码有顺序要求，异步回调方法需要放在最后，不然没有起到异步回调的作用
         *          completableFuture.get()已经获得回调接口的时候才会执行
         */
        System.out.println("11");
        completableFuture.get();
    }
}
