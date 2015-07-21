package com.panqd.sort;

public class InsertSortTest {
    
    public static void main(String[] args) {
        InsertSort(SortBase.copyArray());
        System.out.println();
        InsertSort2(SortBase.copyArray());
        System.out.println();
        InsertSort3(SortBase.copyArray());
    }
    
    /**
     * 1. 直接选择排序(有序区, 无序区)
     * 2. 原理: 分为有序区和无序区, 从无序区选择一个元素插入到正确的有序区中
     * 3. 关键: 如果选择正确的有序区位置
     * 4. 该排序算法还是利用到了交换排序
     * @param numbers
     */
    public static void InsertSort(final int[] array) {
        long start = System.currentTimeMillis();
        int tmp;
        for (int i = 1; i < array.length; i++) { // 无序区元素
            for (int j = 0; j < i; j++) { // 有序区元素
                // 从无序区中取出第i个元素, 与有序区中的元素进行比较选择插入的位置进行交换
                if (array[j] > array[i]) { 
                    tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
        System.out.println("排序后________");
        SortBase.printArray(array);
        System.out.println("消耗时间 = " + (System.currentTimeMillis() - start));
    }
    
    public static void InsertSort2(final int[] array) {
        long start = System.currentTimeMillis();
        int temp;
        int index;
        for (int i = 1; i < array.length; i++) { // 从无序区中取出第i个数来准备插入到有序区中
            if (array[i] < array[i - 1]) { // 先将取出的数与有序区最后一个数进行比较, 如果小于, 则开始移动插入. 
                temp = array[i]; // 先将要插入的数赋值到一个临时变量中, 否则元素后移会被覆盖
                index = i;
                while (index >= 1 && temp < array[index - 1]) { // 边移动边观察是否已插入到正确位置, 即要插入的数是否已大于前一个数
                    array[index] = array[index - 1]; // 其它元素后移
                    index--; // 继续向前查找正确位置, 直到移动到数组首位. 
                }
                array[index] = temp; // 将取出的数插入到正确的位置. 
            }
        }
        System.out.println("排序后________");
        SortBase.printArray(array);
        System.out.println("消耗时间 = " + (System.currentTimeMillis() - start));
    }
    
    /**
     * 二分法排序
     * @param array
     */
    public static void InsertSort3(final int[] array) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            int left = 0;
            int right = i - 1;
            int mid = 0;
            int temp = array[i];
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                array[j + 1] = array[j];
            }
            if (left != i) {
                array[left] = temp;
            }
        }
        System.out.println("排序后________");
        SortBase.printArray(array);
        System.out.println("消耗时间 = " + (System.currentTimeMillis() - start));
    }
    
}
