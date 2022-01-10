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

//    public static void main(String[] args) {
//        int[] array = {1,8,2,3,9,2};
//
//        array = sort(array,0,5);
//
//        for (int a : array){
//            System.out.println(a);
//        }
//    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        System.out.println(sort.isAdditiveNumber("101"));
    }

    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        if (n<3){
            return false;
        }
        int firstStart = 0;

        for(int secondStart =1;secondStart<=n-2;secondStart++){
            for (int secondEnd = secondStart;secondEnd<=n-2;secondEnd++){
                if(validate(num,firstStart,secondStart,secondEnd)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean validate(String num,int firstStart,int secondStart,int secondEnd) {

        int maxIndex = num.length()-1;
        boolean loopFlag = true;
        while (loopFlag){
            if('0'==(num.charAt(secondStart))){
                return false;
            }
            int first =  Integer.valueOf(num.substring(firstStart,secondStart));
            int second =  Integer.valueOf(num.substring(secondStart,secondEnd+1));
            int thirdIntValue = first+second;
            String thirdStringValue = String.valueOf(thirdIntValue);
            int thirdStart =secondEnd +1;
            int thirdEnd =thirdStart+thirdStringValue.length()-1;
            if(thirdEnd > maxIndex){
                return false;
            }
            if(thirdStringValue.equals(num.substring(thirdStart,thirdEnd+1))){

                if(thirdEnd==maxIndex) {
                    return true;
                }else {
                    firstStart =secondStart;
                    secondStart = thirdStart;
                    secondEnd = thirdEnd;
                }
            }else{
                loopFlag =false;
            }
        }

        return false;
    }


}
