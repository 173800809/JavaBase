package com.li.learn.lamada;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * lambda原理1：function包下常用接口
 *      1. Function：一个输入，一个输出；     补充：T输入，R输出 Function<T, R>
 *      2. Predicate：一个输入，输出值只能是布尔值；补充：T输入，Predicate<T>
 *      3. Consumer：一个输入，没有输出；    补充：T输入，Consumer<T>
 *      4. Supplier：没有输入，只有输出；    补充：Supplier
 */
public class FunctionDemo {
    public static void main(String[] args) {

        /**
         * 泛型可写可不写，尽量写比较好，见名知意
         */
//        Function function = (str) -> {return str;};
        Function<Integer, String> function = (i) -> {
            return String.valueOf(i);
        };
        System.out.println(function.apply(12));

        Predicate<String> predicate = (str) -> {
            return str.isEmpty();
        };
        System.out.println(predicate.test(""));

        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("abc");

        Supplier supplier = ()->{return 1024;};
        System.out.println(supplier.get());
    }
}
