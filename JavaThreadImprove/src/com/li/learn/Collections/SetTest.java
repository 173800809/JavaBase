package com.li.learn.Collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Set安全相关
 *      1. 最新：CopyOnWriteArraySet<>()
 *      2. 其他：
 *          Collections.synchronizedSet(new HashSet<>())
 *      补充：JDK本身没有ConcurrentHashSet，不过guava实现了这个类
 */
public class SetTest {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString());
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
