package com.example.demo.function;

import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String args[]){
        if (testTest(1, val -> val == 1)) {
            System.out.println("1 == 1");
        }
        if (testAnd(2, val -> val < 5, val -> val >1)) {
            System.out.println("1<2<5");
        }
        if (testOr(3, val -> val < 5, val -> val > 5)){
            System.out.println("3<5 or 3>5");
        }
        if (testNegate(4, val -> val != 4)){
            System.out.println("4==4");
        }
        if (testIsEqual(3, Integer.valueOf(3))){
            System.out.println("int==Integer");
        }
    }

    public static boolean testTest(int value, Predicate<Integer> predicate){
        return predicate.test(value);
    }

    public static boolean testAnd(int value, Predicate<Integer> predicate1, Predicate<Integer> predicate2){
        return predicate1.and(predicate2).test(value);
    }

    public static boolean testOr(int value, Predicate<Integer> predicate1, Predicate<Integer> predicate2){
        return predicate1.or(predicate2).test(value);
    }

    public static boolean testNegate(int value, Predicate<Integer> predicate){
        return predicate.negate().test(value);
    }

    public static boolean testIsEqual(int value, int target){
        Predicate<Integer> predicate = Predicate.isEqual(target);
        return predicate.test(value);
    }
}
