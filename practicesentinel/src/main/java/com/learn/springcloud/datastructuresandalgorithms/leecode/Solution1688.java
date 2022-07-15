package com.learn.springcloud.datastructuresandalgorithms.leecode;

public class Solution1688 {
    public int numberOfMatches(int n) {
        int count =0;
        while(n>1){
            int i = n/2;
            int j = n%2;
            count = count + i;
            n=i+j;
        }
        return count;
    }

    public int numberOfMatches2(int n) {
       return n-1;
    }
}
