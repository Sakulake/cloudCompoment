package com.learn.springcloud.datastructuresandalgorithms.leecode;

import com.learn.springcloud.datastructuresandalgorithms.sort.QuickSort;

/**
 * @author sdw
 * @version 1.0
 * @className Solution306
 * @date 2022/01/11 09:18:57
 * @description累加数 是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 *
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 */

public class Solution306 {
    public static void main(String[] args) {
        Solution306 sort = new Solution306();
        System.out.println(sort.isAdditiveNumber("198019823962"));
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
            if('0'==(num.charAt(firstStart)) && firstStart != secondStart-1){
                return false;
            }
            if('0'==(num.charAt(secondStart)) && secondStart != secondEnd){
                return false;
            }
            long first =  Long.valueOf(num.substring(firstStart,secondStart));
            long second =  Long.valueOf(num.substring(secondStart,secondEnd+1));
            long thirdIntValue = first+second;
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
