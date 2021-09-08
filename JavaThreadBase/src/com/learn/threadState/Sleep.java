package com.learn.threadState;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程休眠：
 *      1. sleep(时间)指定当前线程阻塞的毫秒数
 *      2. sleep存在异常InterruptedException
 *      3. sleep时间到达后线程进入就绪状态
 *      4. sleep可以模拟网络延迟，倒计时等
 *      5. 每一个对象都有一个锁，sleep不会释放锁
 */
// 模拟倒计时
public class Sleep {
    public static void main(String[] args) {
        // 获取系统当前时间
        Date startTime = new Date(System.currentTimeMillis());// 获得系统当前时间

        while(true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
