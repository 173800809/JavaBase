package com.li.learn.lamada;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ForkJoin(大任务拆成小任务)
 *      1. 应用背景：在大数据量的时候使用
 *      2. 工作特点：工作窃取(线程B执行完了，从线程A窃取未执行完的数据)
 *      3. 如何使用ForkJoin
 *          a. 需要使用ForkJoinPool来执行（需要注入ForkJoinTask）
 *          b. 计算任务forkJoinPool.execute(ForkJoinTask task)
 *          c. 计算类需要进程ForkJoinTask
 *          d. ForkJoinTask有实现子类有：CountedCompleter ， RecursiveAction ， RecursiveTask
 *          f. 实现方法继承实现子类
 * 补充：int long 等基本类型的拆装箱很浪费时间(下面测试：在10倍左右浪费)，尽量使用基础类型(int,long,double)
 *
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
//        test2();
//        test3();
    }


    private static void test1() {
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间" + (end - start));
    }
    private static void test2() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinTest(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);

        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间" + (end - start));
    }
    private static void test3() {

        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0,Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间" + (end - start));
    }
}

@Data
class ForkJoinTest extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    //临界值
    private Long temp = 10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if((end - start) <temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else { //
            long middle = (start + end) / 2;//中间值
            ForkJoinTest task1 = new ForkJoinTest(start, middle);
            task1.fork();// 拆分任务，把任务压入线程队列
            ForkJoinTest task2 = new ForkJoinTest(middle+1, end);
            task2.fork();// 拆分任务，把任务压入线程队列
            return task1.join()+task2.join();
        }
    }
}
