package com.example.demo.concurrency.threadPool;

/**
 * @Author zhoushenghua on
 */
public class UUIDDemo {

    //    public static void main(String[] args) {
//        for(int i=0;i<2;i++){
//            String uuid = UUID.randomUUID().toString();
//            System.out.println(uuid);
//            System.out.println(uuid.replaceAll("-",""));
//        }
//    }
    public static UUIDDemo u1 = new UUIDDemo();

    static{
        System.out.println("staticBlock");
    }

    {
        System.out.println("block");
    }


    public static void main(String[] args) {
        UUIDDemo u2 = new UUIDDemo();
    }

}
