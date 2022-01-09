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
    public static int[] sort(int [] array){
        for (int i=1;i<array.length;i++){
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
        int[] array = {1,8,2,3,9,2};

        array = sort(array);

        for (int a : array){
            System.out.println(a);
        }
    }

}
