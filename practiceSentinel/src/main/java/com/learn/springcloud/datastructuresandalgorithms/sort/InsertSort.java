package com.learn.springcloud.datastructuresandalgorithms.sort;

/**
 * @author sdw
 * @version 1.0
 * @className InsertSort
 * @date 2021/12/29 15:22:47
 * @description
 */

public class InsertSort {
    //从小到大
    public int[] sort(int [] array){
        for (int i=1;i>=0;i--){
            int cur =array[i];
            int j;
            for(j=i-1;j>=0;--j){
                if(array[j]>cur){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1]=cur;
        }
        return array;
    }

    public static void main(String[] args) {
        int i =10;
        if(10>--i){
            System.out.println(1);
        }
    }

}
