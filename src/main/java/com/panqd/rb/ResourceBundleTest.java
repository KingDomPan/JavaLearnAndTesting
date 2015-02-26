package com.panqd.rb;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 读取资源属性文件
 * 根据.properties文件的名称信息
 * 匹配当前系统的国别语言信息
 * @author KingDom
 */
public class ResourceBundleTest {
    public static void main(String[] args) {
        ResourceBundle.clearCache();
        String baseName = "rb";
        
        Locale locale1 = new Locale("zh", "CN");
        ResourceBundle resb1 = ResourceBundle.getBundle(baseName, locale1);
        System.out.println(resb1.getString("aaa"));

        ResourceBundle resb2 = ResourceBundle.getBundle(baseName,
                Locale.getDefault());// 获取系统默认的资源区域
        System.out.println(resb2.getString("aaa"));

        Locale locale3 = new Locale("en", "US");
        ResourceBundle resb3 = ResourceBundle.getBundle(baseName, locale3);
        System.out.println(resb3.getString("aaa"));
        
        Locale locale4 = new Locale("qq", "QQ");// 找不到对应的资源, 先找默认区域, 再找基本区域
        ResourceBundle resb4 = ResourceBundle.getBundle(baseName, locale4);
        System.out.println(resb4.getString("aaa"));
    }
}
