package com.learn.syn;

/**
 * 1. 线程同步方法，Synchronized。存在如下问题
 *      1.1. 一个线程持有锁会导致其他所有需要此锁的线程挂起
 *      1.2. 在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题
 *      1.3. 如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能问题
 * 2. 两种用法
 *      2.1 synchronized方法
 *      2.2 synchronized块
 *          synchronized(Obj){}
 *          Obj称为同步监视器
 *              Obj可以是任何对象，但是推荐使用"共享资源"作为同步监视器
 *              同步方法中无须指定同步监视器，因为同步方法的同步监视器就是this，就是这个对象本身，或者是class(反射中讲解)
 *          同步监视器的执行过程
 *              第一个线程访问，锁定同步监视器，执行其中代码
 *              第二个线程访问，发现同步监视器被锁定，无法访问
 *              第一个线程访问完毕，解锁同步监视器
 *              第二个线程访问，发现同步监视器没有锁，然后锁定并访问
 */
public class Synchronized {
    public static void main(String[] args) {
        Account account = new Account(1000,"公共账户");
        new Thread(new Drawing(account, 900, "我")).start();
        new Thread(new Drawing(account, 200, "其他人")).start();


    }
}
class Account {
    int money;
    String name;

    public Account(int money, String name){
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread{
    Account account;
    int drawingMoney;
    int nowMoney;
    public Drawing(Account account, int drawingMoney ,String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        /**
         * 这里就是锁 共享资源(变化的量)
         */
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money = account.money - drawingMoney;
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.name + "余额为：" + account.money);
            System.out.println(this.getName() + "手里的钱为：" + nowMoney);
        }
    }
}