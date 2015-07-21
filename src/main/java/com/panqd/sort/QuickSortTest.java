package com.panqd.sort;

public class QuickSortTest {
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] array = SortBase.copyArray();
        QuickSort(array, 0, array.length - 1);
        SortBase.printArray(array);
        System.out.println("消耗时间 = " + (System.currentTimeMillis() - start));
    }
    
    /**
     * 1. 快速排序
     * 2. 原理: 通过一趟排序将要排序的数据分割成独立的两部分,
     * 其中一部分的所有数据都比另外一部分的所有数据都要小
     * 然后再按此方法对这两部分数据分别进行快速排序
     * 整个排序过程可以递归进行, 以此达到整个数据变成有序序列
     * @param array
     */
    public static void QuickSort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            QuickSort(n, left, dp - 1);
            QuickSort(n, dp + 1, right);
        }
    }

    public static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }
    
}
