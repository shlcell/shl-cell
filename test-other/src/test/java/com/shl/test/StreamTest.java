package com.shl.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流操作
 */
public class StreamTest {

//---------------------------------------------------中间操作符-------------------------------------------------

    /**
     * filter 过滤集合中不符合条件的元素
     */
    @Test
    public void filter() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a");
        List<String> filterd = strings.stream().filter(str -> str.contains("a")).collect(Collectors.toList());
        System.out.println(filterd);
    }

    /**
     * distinct 集合中去重
     */
    @Test
    public void distinct() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a");
        List<String> distincted = strings.stream().distinct().collect(Collectors.toList());
        System.out.println(distincted);
    }

    /**
     * limit 获取流中前n个元素
     */
    @Test
    public void limit() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a");
        List<String> limited = strings.stream().limit(2).collect(Collectors.toList());
        System.out.println(limited);
    }

    /**
     * skip 跳过，获取流中除去前n个元素的其他所有元素
     */
    @Test
    public void skip() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d");
        List<String> skiped = strings.stream().skip(2).collect(Collectors.toList());
        System.out.println(skiped);
    }

    /**
     * map 对流中所有元素做统一处理，返回一个值
     */
    @Test
    public void map() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d");
        List<String> mapped = strings.stream().map(str -> str.concat("_shl")).collect(Collectors.toList());
//        List<String> mapped = strings.stream().map(str -> "_shl"+str).collect(Collectors.toList());
        System.out.println(mapped);
    }

    /**
     * flatMap 对流的扁平化处理,有序拼接，返回一个流，多个值
     */
    @Test
    public void flatMap() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d");
        List<Character> flatMap = strings.stream().flatMap(StreamTest::getCharacterByString).collect(Collectors.toList());
        System.out.println(flatMap);
    }

    /**
     * sorted 排序
     */
    @Test
    public void sorted() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        List<String> sorted = strings.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);
    }

    /**
     * 字符串转化为字符流
     *
     * @param str 字符串
     * @return 字符流
     */
    public static Stream<Character> getCharacterByString(String str) {
        List<Character> characterList = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            characterList.add(character);
        }
        return characterList.stream();
    }

//---------------------------------------------------终止操作符-------------------------------------------------

    /**
     * anyMatch 集合中是否有一个元素满足条件,boolean
     */
    @Test
    public void anyMatch() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        boolean aa = strings.stream().anyMatch(s -> s.contains("aa"));
        System.out.println(aa);
    }

    /**
     * allMatch 集合中元素是否都满足满足条件,boolean
     */
    @Test
    public void allMatch() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        boolean aa = strings.stream().allMatch(s -> s.contains("aa"));
        System.out.println(aa);
    }

    /**
     * noneMatch 集合中元素是否都不满足满足条件,boolean
     */
    @Test
    public void noneMatch() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        boolean aa = strings.stream().noneMatch(s -> s.contains("q"));
        System.out.println(aa);
    }

    /**
     * findAny 返回集合中任意元素，使用并行流返回结果可能不同，
     */
    @Test
    public void findAny() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        Optional<String> any = strings.stream().findAny();
        any.ifPresent(System.out::println);
    }

    /**
     * findFirst 返回集合中第一个元素
     */
    @Test
    public void findFirst() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        Optional<String> any = strings.stream().findFirst();
        any.ifPresent(System.out::println);
    }

    /**
     * forEach 遍历流
     */
    @Test
    public void forEach() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "");
        strings.stream().forEach(System.out::println);
    }

    /**
     * collect 将流转化成其他形式：list set map
     */
    @Test
    public void collect() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "", "d");
        Set<String> collect = strings.stream().collect(Collectors.toSet());
        System.out.println(collect);
    }

    /**
     * reduce 将流中元素反复结合起来得到一个结果
     */
    @Test
    public void reduce() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "", "d");
        Optional<String> reduce = strings.stream().reduce((acc, item) -> acc + item);
        reduce.ifPresent(System.out::println);
    }

    /**
     * count 返回集合中数据数量
     */
    @Test
    public void count() {
        List<String> strings = Arrays.asList("aa", "b", "c", "a", "d", "", "d");
        long count = strings.stream().count();
        System.out.println(count);
    }

}
