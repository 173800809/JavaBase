package com.li.learn.single;

/**
 * 懒汉式-枚举类
 *      1. 安全性的原因：可以枚举类不能通过反射构造函数进行新建一个新的对象。下面这个代码会报错
 *          Constructor<XXX> constructor = XXX.class.getDeclaredConstructor();
 *      2. 补充：枚举类不存在无参构造函数(虽然在代码中写了无参构造，但是在实际生成的class文件中没有无参构造函数)
 */
enum EnumSingleTon{
    INSTANCE;
    public void doSomething(){

        System.out.println("测试单例模式执行的代码");
    }

}

public class Lazy_Enum_Demo {
    public static void main(String[] args) {
        EnumSingleTon.INSTANCE.doSomething();
    }
}
