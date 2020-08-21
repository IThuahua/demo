package com.example.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class ThreeClassLoaderDemo {

    public static void main(String[] args) {
        //启动类加载器加载的文件
        URL[] btUrls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : btUrls) {
            System.out.println(url);
        }

        //扩展类加载器加载的文件
        URL[] etUrls = ((URLClassLoader) ClassLoader.getSystemClassLoader().getParent()).getURLs();
        for (URL url : etUrls) {
            System.out.println(url);
        }

        //扩展类加载器加载的文件

        URL[] appUrls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : appUrls) {
            System.out.println(url);
        }
    }


}
