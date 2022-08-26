package lyr.learn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        StreamDemo.filterTest();
    }

    static void test1() {
        //stream创建方式1
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        Stream<String> stream = strings.stream();
        //stream创建方式2
        Stream<String> stream1 = Stream.of("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
    }

    static void filterTest() {
        List<String> strings = Arrays.asList("Hollis", "", "hollis", "Hello", "HelloWorld", "Hollis");
        strings.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);
    }

    static void mapTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().map(i -> i * i).forEach(System.out::println);
    }

    static void limitTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().limit(4).forEach(System.out::println);
    }

    static void sortedTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().sorted().forEach(System.out::println);
    }

    static void distinctTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().distinct().forEach(System.out::println);
    }

    static void test2() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        strings.stream().filter(string -> string.length() <= 6).map(String::length).sorted().limit(3)
                .distinct().forEach(System.out::println);
    }

    static void countTest(){
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "HelloWorld", "Hollis");
        System.out.println(strings.stream().count());
    }

    static void collectTest(){
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis","Hollis666", "Hello", "HelloWorld", "Hollis");
        strings  = strings.stream().filter(string -> string.startsWith("Hollis")).collect(Collectors.toList());
        System.out.println(strings);
    }


}
