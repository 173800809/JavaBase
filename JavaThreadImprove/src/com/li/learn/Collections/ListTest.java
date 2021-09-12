package com.li.learn.Collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List安全相关：
 *      1. 最新的：CopyOnWriteArrayList<>()：关键点->读写分离
 *      2. 其他：
 *          Vector<>();
 *          Collections.synchronizedList(new ArrayList<>());
 */
public class ListTest {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString());
            },String.valueOf(i)).start();
        }

    }

}

