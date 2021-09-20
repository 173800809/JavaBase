package com.li.learn.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式-DCL(Double Check Lock：双重校验锁)
 *      1. 为什么要加两次判断 if(lazy_dcl_demo == null)
 *          a. 第一次是为了避免无意义加锁：单例初始化后，不会再次进入synchronized方法浪费时间
 *          b. 第二次是为了实际初始化对象
 *      2. 通过反射破坏单例模式
 *          a. 通过反射获得构造方法：Constructor
 *              Constructor<XXX> constructor = XXX.class.getDeclaredConstructor(null);
 *              constructor.setAccessible(true);
 *              constructor.newInstance();
 *          b. 通过反射获得变量
 *              Field xx = XX.class.getDeclaredField("xx");
 *              xx.setAccessible(true);
 *      3. 为什么需要加volatile？
 *          a. 解释：为了防止指令重排，导致多线程有的线程无法获得成功初始化的对象
 *          b. 说明：lazy_dcl_demo = new Lazy_DCL_Demo()的加载顺序
 *              (1) 分配内存
 *              (2) 执行构造方法，初始化对象
 *              (3) 把这个对象指向这个空间
 *          c. 举例：线程B没有获得初始化的对象
 *              线程A执行顺序 123
 *              线程B执行顺序 132
 *
 */
public class Lazy_DCL_Demo {
    private static boolean lee1 = false;

    private Lazy_DCL_Demo(){
        synchronized(Lazy_DCL_Demo.class){
            if(lee1 == false){
                lee1 = true;
            }else{
                throw new RuntimeException("有反射破坏异常");
            }
        }
    }

    private volatile static Lazy_DCL_Demo lazy_dcl_demo;

    public static Lazy_DCL_Demo getInstance(){
        if(lazy_dcl_demo == null){
            synchronized (Lazy_DCL_Demo.class){
                if(lazy_dcl_demo == null){
                    lazy_dcl_demo = new Lazy_DCL_Demo();
                }
            }
        }

        return lazy_dcl_demo;
    }

    public static void main(String[] args) throws Exception {

//        Lazy_DCL_Demo instance = Lazy_DCL_Demo.getInstance();

        Field lee1 = Lazy_DCL_Demo.class.getDeclaredField("lee1");
        lee1.setAccessible(true);

        Constructor<Lazy_DCL_Demo> declaredConstructor = Lazy_DCL_Demo.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        Lazy_DCL_Demo instance = declaredConstructor.newInstance();

        lee1.set(instance,false);

        Lazy_DCL_Demo instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }

}
