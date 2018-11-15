package com.juzix.word.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinx
 * @date 2018/11/15 11:21
 * Desc:
 */
public class Test {
    public static Map map = new HashMap();
    public static void main(String[] args) {
        Map newMap = map;
        newMap.put(1,1);
        System.out.println(newMap);
        System.out.println(map);
        System.out.println("------------");

//        // 增量
//        newMap.put(2,2);
//        System.out.println(newMap);
//        System.out.println(map);
//        System.out.println("------------");

        Map newMap1 = new HashMap();
        newMap1.put(3,3);
        //重置
        newMap = newMap1;
        System.out.println(newMap1);
        System.out.println(newMap);
        System.out.println(map);
        System.out.println("------------");

        newMap.put(4,4);
        System.out.println(newMap1);
        System.out.println("------------");

    }
}
