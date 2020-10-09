package com.example.demo.function;

import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {
        testApply(1, input -> System.out.println(input == 1 ? "true" : "false")); //true
        testAndThen(1,
                input -> System.out.println(input > 1 ? "true" : "false"),
                input -> System.out.println(input == 1 ? "true" : "false")); //false true

    }

    public static void testApply(int value, Consumer<Integer> consumer){
        consumer.accept(value);
    }

    public static void testAndThen(int value, Consumer<Integer> consumer1, Consumer<Integer> consumer2){
        consumer1.andThen(consumer2).accept(value);
    }

}
