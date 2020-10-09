package com.example.demo.function;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        System.out.println(testGet(() -> "123".length()));
    }

    public static Integer testGet(Supplier<Integer> supplier){
        return supplier.get();
    }
}
