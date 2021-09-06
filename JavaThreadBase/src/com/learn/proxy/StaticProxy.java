package com.learn.proxy;

import com.learn.utils.WeddingCompany;
import com.learn.utils.You;

/**
 * 静态代理总结：
 *      真实对象和代理对象都要实现同一个接口
 *      代理对象要代理真实角色
 * 好处：
 *      代理对象可以做很多真实对象做不了的事情
 *      真实对象专注做自己的事情
 */
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();

        // 优化代码
        new WeddingCompany(new You()).HappyMarry();

//        WeddingCompany weddingCompany = new WeddingCompany(you);
//        weddingCompany.HappyMarry();
    }
}
