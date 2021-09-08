package com.learn.lambda;

import com.learn.utils.ILike;

/**
 * lambda 推导过程
 * 1. 理解Functional Interface(函数式接口)是学习Java8 lambda表达式的关键所在
 * 2. 函数式接口的定义
 *      2.1 任何接口，如果只包含唯一一个抽象方法(补充：接口的方法默认是abstract<抽象>方法)，那么它就是一个函数式接口
 *      2.2 对于函数式接口，我们可以通过lambda表达式来创建该接口的对象
 */
public class TestLambda {

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        /**
         *  静态内部类的实现方法
         */
        like = new Like2();
        like.lambda();



        // 实现方法3：局部内部类
        class Like3 implements ILike{
            @Override
            public void lambda(){
                System.out.println("I like lambda3");
            }
        }

        like = new Like3();
        like.lambda();

        // 实现方法4：匿名内部类，没有类的名称，必须借助接口或父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4");
            }
        };
        like.lambda();

        // 实现方法5：用lambda简化
        like = () -> {
            System.out.println("I like lambda5");
        };
        like.lambda();
    }

    // 实现方法2:静态内部类
    static class Like2 implements ILike{
        @Override
        public void lambda(){
            System.out.println("I like lambda2");
        }
    }
}

// 实现方法1
class Like implements ILike{
    @Override
    public void lambda(){
        System.out.println("I like lambda1");
    }
}


