package com.panqd.rb;

import java.util.Locale;

/**
 * 表示了特定的地理、政治和文化地区 需要 Locale 来执行其任务的操作称为语言环境敏感的 操作
 * 它使用 Locale 为用户量身定制信息
 * @Locale
 *      Locale(String language)
 *      Locale(String language, String country)
 *      Locale(String language, String country, String variant)
 *      getCountry 可获取 ISO 国家代码
 *      getLanguage 则获取 ISO 语言代码
 *      getDisplayCountry 获取适合向用户显示的国家名
 *      getDisplayLanguage 获取适合向用户显示的语言名
 *      
 * @author KingDom
 */
public class LocaleTest {
    public static void main(String[] args) {
        Locale zhCn = new Locale("zh", "CN");
        System.out.println(zhCn.getCountry()); //CN
        System.out.println(zhCn.getLanguage()); //zh
        System.out.println(zhCn.getDisplayCountry()); //中国
        System.out.println(zhCn.getDisplayLanguage()); //中文
        System.out.println(zhCn.getDisplayLanguage(new Locale("en", "US"))); //Chinese
    }
}
