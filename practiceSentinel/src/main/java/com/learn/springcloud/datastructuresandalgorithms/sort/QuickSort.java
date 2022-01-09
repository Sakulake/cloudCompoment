package com.learn.springcloud.datastructuresandalgorithms.sort;

public class QuickSort {

    /**
     * f(n) = f(n/2) + f(n/2) ,从小到大
     * @param array
     * @param start
     * @param stop
     * @return
     */
    public static int[] sort(int [] array,int start, int stop){
        int compareValue = array[stop];
        int indexOfNeedMoveToLarge =-1 ;
        int indexOfNeedMoveToSmall =-1;
        for (int i = 0;i<stop-1;i++){
            if (array[i]>compareValue){
                if(indexOfNeedMoveToLarge<0){
                    indexOfNeedMoveToLarge =i;
                }
                if ( indexOfNeedMoveToSmall >0){
                    swap(array,indexOfNeedMoveToLarge,indexOfNeedMoveToSmall);
                }
            }else{
                if(indexOfNeedMoveToSmall<0){
                    indexOfNeedMoveToSmall =i;
                }
                if ( indexOfNeedMoveToLarge >0){
                    swap(array,indexOfNeedMoveToLarge,indexOfNeedMoveToSmall);
                }
            }

        }

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
