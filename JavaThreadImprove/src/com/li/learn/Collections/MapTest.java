package com.li.learn.Collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map安全相关
 *      1. 最新：ConcurrentHashMap<>()
 *      2. 其他:
 *          Collections.synchronizedMap(new HashMap<>());
 */
public class MapTest {
    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
