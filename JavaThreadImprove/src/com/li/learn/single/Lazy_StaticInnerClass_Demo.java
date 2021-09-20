package com.li.learn.single;

import java.lang.reflect.Constructor;

/**
 * 懒汉式-静态内部类：
 *      1. 实现单例模式是线程安全的
 *      2. 会有给反射和反序列化攻击
 *           Constructor<XXX> constructor = XXX.class.getDeclaredConstructor();
 *           constructor.setAccessible(true);
 *           constructor.newInstance();
 */
public class Lazy_StaticInnerClass_Demo {

    private Lazy_StaticInnerClass_Demo(){

    }
    public static Lazy_StaticInnerClass_Demo getInstance(){
        return InnerClass.LAZY_STATIC_INNER_CLASS_DEMO;
    }

    public static class InnerClass{
        private static final Lazy_StaticInnerClass_Demo LAZY_STATIC_INNER_CLASS_DEMO = new Lazy_StaticInnerClass_Demo();
    }

    public static void main(String[] args) throws Exception {

        Lazy_StaticInnerClass_Demo lazy_staticInnerClass_demo = Lazy_StaticInnerClass_Demo.getInstance();

        Constructor<Lazy_StaticInnerClass_Demo> constructor = Lazy_StaticInnerClass_Demo.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Lazy_StaticInnerClass_Demo newSingleTon = constructor.newInstance();

        System.out.println(lazy_staticInnerClass_demo);
        System.out.println(newSingleTon);
    }
}
