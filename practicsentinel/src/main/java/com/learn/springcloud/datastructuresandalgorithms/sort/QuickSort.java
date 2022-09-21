package com.learn.springcloud.datastructuresandalgorithms.sort;

public class QuickSort {

    /**
     * f(n) = f(n/2) + f(n/2) +n,从小到大
     * @param array
     * @param start
     * @param stop
     * @return
     */
    public static int[] sort(int [] array,int start, int stop){

        if(start>=stop){
            return  array;
        }
        int low = start;
        int high = stop;
        int temp = array[low];
        while (high>low){
            while (high>low){
                if(array[high]<temp){
                    break;
                }
                high--;
            }
            swap(array,low,high);
            while (low<high){
                if (array[low]>=temp){
                    break;
                }
                low++;
            }
            swap(array,low,high);
        }
        array[low]=temp;

        sort(array,start,low);
        sort(array,low+1,stop);

        return array;
    }

    private static void swap(int[] array, int indexOfNeedMoveToLarge, int indexOfNeedMoveToSmall) {
        int tmp = array[indexOfNeedMoveToLarge];
        array[indexOfNeedMoveToLarge] = array[indexOfNeedMoveToSmall];
        array[indexOfNeedMoveToSmall] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {1,8,2,3,9,2};

        array = sort(array,0,5);

        for (int a : array){
            System.out.println(a);
        }
    }



}
