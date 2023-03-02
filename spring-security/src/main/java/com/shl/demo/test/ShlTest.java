package com.shl.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShlTest {
    Runnable r1 = () -> {
        System.out.println("fdsfds");
    };

    public static void main(String[] args) {
        List<Object> listName1 = new ArrayList<>();
        List<Object> listName2 = new ArrayList<>();
        new ShlTest().r1.run();
        listName1.add(1);
        listName1.add(2);
        listName1.add(3);
        System.out.println(Math.sqrt(9));
        listName2.add("x");
        listName2.add("y");
        listName2.add("z");

        List<Object> arrayList;
        arrayList = Stream.concat(listName1.stream(), listName2.stream()).collect(Collectors.toList());
        System.out.println(arrayList);
        listName2.addAll(listName1);
        System.out.println(listName2.size());
        System.out.println(listName2.get(0));
        System.out.println(listName2);
        System.out.println("-------------------------------");
        int x = 1;
        for (int i = 0; i < 5; i++) {
            x = x + i;
        }
        System.out.println(x);
    }
}