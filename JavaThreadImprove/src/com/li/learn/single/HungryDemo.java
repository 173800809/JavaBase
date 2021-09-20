package com.li.learn.single;

/**
 * 饿汉式
 *      1. 问题：会出现浪费空间的情况
 */
public class HungryDemo {
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private HungryDemo(){

    }
    private final static HungryDemo HUNGRY_DEMO = new HungryDemo();

    public static HungryDemo getInstance(){
        return HUNGRY_DEMO;
    }
}
