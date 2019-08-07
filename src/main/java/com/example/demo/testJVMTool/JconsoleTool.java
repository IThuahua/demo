package com.example.demo.testJVMTool;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhoushenghua on
 */
public class
JconsoleTool {

    /**
     *  设置虚拟机参数：
     *  -Xms100M -Xms100m -XX:+UseSerialGC -XX:+PrintGCDetails
     */
    static class OOMObject {
        //每个对象64kb,16个对象为1M
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        Thread.sleep(2000); //先运行程序，在执行监控
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        //打印垃圾回收器
        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : list) {
            System.out.println(bean.getName());
        }
        fillHeap(1000);
        while(true){
            //让其一直运行着
        }

    }

}
