package com.shl.demo.test;

import com.shl.demo.vo.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class SteamTest {
    public static void main(String[] args) throws Exception {
        List<Pool> list = new ArrayList<Pool>() {
            {
                add(new Pool("A", 1));
                add(new Pool("A", 2));
                add(new Pool("A", 3));
                add(new Pool("B", 4));
                add(new Pool("B", 5));
            }
        };
        // 求和
        int sum = list.stream().mapToInt(Pool::getB).sum();
        // 最大值
        OptionalInt max = list.stream().mapToInt(Pool::getB).max();
        // 最小值
        OptionalInt min = list.stream().mapToInt(Pool::getB).min();
        // 平均值
        OptionalDouble average = list.stream().mapToInt(Pool::getB).average();

        System.err.println(sum);
        System.err.println(max.getAsInt());
        System.err.println(min.getAsInt());
        System.err.println(average.getAsDouble());

    }


}
