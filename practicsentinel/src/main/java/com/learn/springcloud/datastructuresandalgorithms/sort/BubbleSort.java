package com.learn.springcloud.datastructuresandalgorithms.sort;

/**
 * @author sdw
 * @version 1.0
 * @className BubbleSort
 * @date 2021/12/29 14:59:03
 * @description
 */

public class BubbleSort {
    public int[] sort(int [] array){

        for (int i=0;i<array.length;i++){
            //如果冒泡一次，没有交换证明已经排序完成，不需要再冒泡；
            boolean hasSwap = false;

            // j<array.length-i-1;冒泡一次后，就有一个数字按要求排列到最后，所以-i
            for(int j =0;j<array.length-i-1;j++){
                if (array[j]<array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    hasSwap =true;
                }
            }
            if(!hasSwap){
                break;
            }
        }
        return array;
    }
}
