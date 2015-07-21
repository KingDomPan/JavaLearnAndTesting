package com.panqd.jvm.classloader;

import java.net.URL;

public class ClassLoaderTest {
    @SuppressWarnings("restriction")
    public static void main(String[] args) {
        // 特殊的加载器, 实际上不是ClassLoader的子类, 而是由JVM自身实现的
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            // 这些不需要在classpath指定, 因为在JVM启动的时候就已经加载了
            System.out.println(urls[i].toExternalForm());
        }

        // extension对systemLoader都是可见的
        System.out.println(System.getProperty("java.ext.dirs"));
        ClassLoader extensionClassloader = ClassLoader.getSystemClassLoader()
                .getParent();
        // extension classloader是system classloader的parent, 而bootstrap
        // classloader是extension classloader的parent, 但它不是一个实际的classloader,
        // 所以为null
        System.out.println("the parent of extension classloader : "
                + extensionClassloader.getParent());

        // System loader 应用加载器
        // java.class.path系统属性或者 CLASSPATH操作系统属性所指定的JAR类包和类路径
        // 总能通过静态方法ClassLoader.getSystemClassLoader()找到该类加载器
        // 如果没有特别指定, 则用户自定义的任何类加载器都将该类加载器作为它的父加载器
        System.out.println(System.getProperty("java.class.path"));

        // 加载机制
        // 1. 全盘委托
        // 1.1 当一个classloader加载一个Class的时候, 这个Class所依赖的和引用的所有
        // Class也由这个classloader负责载入, 除非是显式的使用另外一个classloader载入
        // 1.2 委托机制则是先让parent(父)类加载器 (而不是super, 它与parent
        // classloader类不是继承关系)寻找, 只有在parent找不到的时候才从自己的类路径中去寻找
        // 1.3 类加载还采用了cache机制, 也就是如果
        // cache中保存了这个Class就直接返回它, 如果没有才从文件中读取和转换成Class, 并存入cache,
        // 这就是为什么我们修改了Class但是必须重新启动JVM才能生效的原因'

        /**
         * 每个ClassLoader加载Class的过程是： 1.检测此Class是否载入过(即在cache中是否有此Class),
         * 如果有到8,如果没有到2 2.如果parent classloader不存在(没有parent, 那parent一定是bootstrap
         * classloader了), 到4 3.请求parent classloader载入, 如果成功到8, 不成功到5
         * 4.请求jvm从bootstrap classloader中载入, 如果成功到8
         * 5.寻找Class文件(从与此classloader相关的类路径中寻找). 如果找不到则到7. 6.从文件中载入Class, 到8.
         * 7.抛出ClassNotFoundException. 8.返回Class.
         */

        /**
         * 类的加载顺序. bootstrap --> extension --> system 处于安全性的考虑
         */
        System.out.println(System.class.getClassLoader()); // 返回null 由 bootstrap
                                                           // classLoader加载

        /**
         * JVM建立类加载器的结构 1.1 当你执行java命令的时候, JVM会先使用bootstrap
         * classloader载入并初始化一个Launcher
         */
        System.out.println("the Launcher's classloader is "
                + sun.misc.Launcher.getLauncher().getClass().getClassLoader());

        /**
         * 1.2 Launcher 会根据系统和命令设定初始化好class loader结构, JVM就用它来获得extension
         * classloader和system classloader, 并载入所有的需要载入的Class, 
         * 最后执行java命令指定的带有静态的main方法的Class
         */
    }
}
