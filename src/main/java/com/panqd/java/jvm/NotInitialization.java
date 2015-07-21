package com.panqd.java.jvm;

@SuppressWarnings("unused")
public class NotInitialization {
    public static void main(String[] args) {
        // 1. 被动引用例子, 对于静态字段, 只有直接定义这个字段的类才会被初始化
        // 因此通过其子类来引用父类中定义的静态字段, 置灰触发父类的初始化而不会
        // 触发子类的初始化
        System.out.println(SubClass.value); // 不会输出Subclass Init !!
        
        // 2. 被动引用例子2
        SuperClass[] sca = new SuperClass[10]; // 什么都不会输出
        
        // 3. 被动引用例子3, 这里直接引用了ConstClass的常量, 那么在编译阶段
        // 通过常量传播优化, 已经在这个值存放到了NotInitialization的常量池中
        // 以后对ConstClass.HELLOWORLD的引用会会转化为对NotInitialization自身常量池的引用
        // 也就是说NotInitialization的class文件中并没有ConstClass的符号引用
        System.out.println(ConstClass.HELLOWORLD); // 不会输出ConstClass Init!!
    }
}
