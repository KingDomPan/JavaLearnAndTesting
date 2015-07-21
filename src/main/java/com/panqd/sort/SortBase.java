package com.panqd.sort;

public abstract class SortBase {
    
    public final static int[] array = { 6, 3, 2, 7, 1, 4, 5 };
    
    public static int[] copyArray() {
        System.out.println("排序前________");
        printArray(array);
        int len = SortBase.array.length;
        int[] array = new int[len];
        System.arraycopy(SortBase.array, 0, array, 0, len);
        return array;
    }
    
    public static void printArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
