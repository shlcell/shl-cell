package com.shl.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShlTest {
    public static void main(String[] args) {
        List<Object> listName1 = new ArrayList<>();
        List<Object> listName2 = new ArrayList<>();

        listName1.add(1);
        listName1.add(2);
        listName1.add(3);

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
    }
}
