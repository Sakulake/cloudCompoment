package com.learn.springcloud.datastructuresandalgorithms.sort;

public class SelectionSort {
    //从小到大
    public static int[] sort(int [] array) {
        for (int i = 0;i<array.length;i++){
            int minIndex = i;
            int min =array[i];;
            for (int j =i;j<array.length-1;j++){
                if(min<array[j+1]){

                }else{
                    minIndex = j+1;
                    min=array[j+1];
                }
            }
            int tmp = array[i];
            array[i] =min;
            array[minIndex] = tmp;
        }
        return  array;

    }
    public static void main(String[] args) {
        int[] array = {1,8,2,3,9,2};

        array = sort(array);

        for (int a : array){
            System.out.println(a);
        }
    }
}
