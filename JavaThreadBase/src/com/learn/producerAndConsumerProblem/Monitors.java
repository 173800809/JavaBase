package com.learn.producerAndConsumerProblem;

/**
 * 解决生产者消费者问题方法1：管程法(Monitors)
 *      1. 生产者：负责生产数据的模块(可能是方法、对象、线程、进程)
 *      2. 消费者：负责处理数据的模块(可能是方法、对象、线程、进程)
 *      3. 缓冲区：消费者不能直接使用生产者的数据，他们之间有个"缓冲区"
 *      补充：生产者将生产好的数据放入缓冲区，消费者从缓冲区拿出数据
 */
public class Monitors {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Producer(container).start();
        new Consumer(container).start();
    }
}

class Producer extends Thread {
    SynContainer container;
    public Producer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了"+i+"只鸡");
        }
    }
}
class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("消费了--"+container.pop().id + "只鸡");
        }
    }
}
class Chicken {
    int id;
    public Chicken(int id){
        this.id = id;
    }
}

class SynContainer{
    // 设置一个容器的大小
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    // 生产者放入产品(加锁)
    public synchronized void push(Chicken chicken)  {
        if(count == chickens.length){
            // 生产者等待消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        chickens[count] = chicken;
        count++;
        // 通知消费者可以消费了
        this.notifyAll();
    }

    // 消费者消费产品(加锁)
    public synchronized Chicken pop() {
        if(count == 0){
            // 消费者等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];

        // 吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }

}
