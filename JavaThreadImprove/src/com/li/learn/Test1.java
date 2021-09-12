package com.li.learn;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private synchronized void test() throws InterruptedException {
        Test1 test = new Test1();
        test.wait();
    }
}
