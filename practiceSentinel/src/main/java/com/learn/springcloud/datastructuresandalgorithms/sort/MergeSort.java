package com.learn.springcloud.datastructuresandalgorithms.sort;

public class MergeSort {
    /**
     * 归并 分而治之的思想， 递归实现  从小到大
     * f(n) = merge（f(n/2) ，f(n/2))
     * @param array
     * @return
     */
    public static int[] sort(int [] array,int start, int stop){

        if (stop==start){
            return new int[]{array[start]};
        }
        int middle = (stop+start)/2 ;
        return merge(sort(array,start,middle),sort(array,middle+1,stop));


    }


    public static int[] merge(int [] prev ,int post []){
        int[] array = new int[prev.length+post.length];
        int arrayIndex= 0;
        int prevStart = 0;
        int postStart = 0;

        int prevStop  = prev.length-1;
        int postStop  = post.length-1;
        do {
            int prevTmpValue = prev[prevStart];
            int postTmpValue = post[postStart];
            if (prevTmpValue<=postTmpValue){
                array[arrayIndex] = prevTmpValue;
                prevStart++;
            }else{
                array[arrayIndex] = postTmpValue;
                postStart++;
            }
            arrayIndex++;
        }while (prevStart<=prevStop && postStart<=postStop );

        if(prevStart>prevStop){
            for(;postStart<=postStop;postStart++){
                array[arrayIndex] = post[postStart];
                arrayIndex++;
            }
        }else {
            for (; prevStart <= prevStop; prevStart++) {
                array[arrayIndex] = prev[prevStart];
                arrayIndex++;
            }
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = {1,8,2,3,9,2};

        array = sort(array,0,5);

        for (int a : array){
            System.out.println(a);
        }
    }

}
