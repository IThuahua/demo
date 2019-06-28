package com.example.demo.db.base.core;

/**
 * @Author zhoushenghua on
 */
public class DynameicDataSourceContextHolder {
    private static final ThreadLocal<String> currentDataSource = new ThreadLocal<>();

    public static void set(DataSourceEnum val){
        currentDataSource.set(val.name());
    }

    public static String get(){
        return currentDataSource.get();
    }

    public static void clear(){
        currentDataSource.remove();
    }




}
