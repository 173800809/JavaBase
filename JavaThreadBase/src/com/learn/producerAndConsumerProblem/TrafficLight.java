package com.learn.producerAndConsumerProblem;

/**
 * 解决生产者消费者问题方法2：信号灯法(TrafficLight)
 *      使用标志位flag去通知线程
 */
public class TrafficLight {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2 == 0){
                this.tv.play("快乐大本营播放中");
            }else{
                this.tv.play("抖音记录美好生活");
            }
        }
    }
}

class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}
class TV{
    String voice; // 表演的节目
    boolean flag = true;

    // 表演
    public synchronized void play(String voice){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了:"+voice);
        // 通知观众观看
        this.notifyAll();
        this.voice = voice;
        this.flag = !this.flag;
    }

    // 观看
    public synchronized void watch(){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了:"+voice);
        // 通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }




}








