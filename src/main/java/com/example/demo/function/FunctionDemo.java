package com.example.demo.function;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo {

    public static void main(String[] args) {
        System.out.println(testApply(1, val -> val + 1)); //返回2（1+1 = 2）
        System.out.println(testCompose(2, val -> val * 3, val -> val + 2)); //返回12（先执行 2+2 = 4，再执行 4*3 = 12）
        System.out.println(testAndThen(2, val -> val * 3, val -> val + 2)); //返回8（先执行 2*3 = 6，再执行 6+2 = 8）
        System.out.println(testIdentity(3)); //返回3  直接调用function.indentity返回的Function对象，返回自己本身
        System.out.println(testIdentity().get(1).toString());//返回 id:1,name:1
    }

    public static Integer testApply(int value, Function<Integer, Integer> function){
        return function.apply(value);
    }

    public static Integer testCompose(int value, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        return function1.compose(function2).apply(value);
    }

    public static Integer testAndThen(int value, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        return function1.andThen(function2).apply(value);
    }

    public static Integer testIdentity(int value){
        Function<Integer, Integer> function = Function.identity();
        return function.apply(value);
    }

    public static Map<Integer, User> testIdentity(){
        List<User> list = new ArrayList<>();
        User u1 = new User(1, "1");
        User u2 = new User(2, "2");
        list.add(u1);
        list.add(u2);
        return list.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    @Data
    @AllArgsConstructor
    static class User{
        Integer id;
        String name;
        @Override
        public String toString(){
            return "id:" + id + ",name:" + name;
        }
    }
}
