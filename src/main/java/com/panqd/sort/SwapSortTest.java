package com.panqd.sort;

public class SwapSortTest {
    
    public static void main(String[] args) {
        SwapSort(SortBase.copyArray());
    }
    
    /**
     * 1. 冒泡排序
     * 2. 原理: 相邻元素进行比较与交换, 使值较小的元素逐渐从后面向前面移动
     * 3.1 从无序区左边开始, 依次对相邻的记录进行22比较, 不满足顺序要求的进行交换
     * 3.2 每趟结束等同于从无序列中选出最大的数(从大到小排列)或最小的数(从小到大排列)填在无序区的末尾
     * 3.3 每趟结束无序区范围-1, 直到无序区范围为1
     * @param numbers
     */
    public static void SwapSort(final int[] array) {
        long start = System.currentTimeMillis();
        int tmp;
        boolean flag = false; // 标注此趟是否有进行交换
        for (int i = 1; i < array.length; i++) { // 无序区元素
            flag = false;
            for(int j = 0; j < array.length - i; j++) {
                if(array[j] > array[j + 1]) {
                    tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    flag = true;
                }
            }
            if(!flag) { // 如果该趟没进行过交换则表明排序已完成  
                break;
            }
        }
        System.out.println("排序后________");
        SortBase.printArray(array);
        System.out.println("消耗时间 = " + (System.currentTimeMillis() - start));
    }
    
}
