package com.learn.springcloud.datastructuresandalgorithms.leecode;

public class Solution1220 {

    int mod = 1000000007;
    /**
     * 给你一个整数n，请你帮忙统计一下我们可以按下述规则形成多少个长度为n的字符串：
     *
     * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
     * 每个元音'a'后面都只能跟着'e'
     * 每个元音'e'后面只能跟着'a'或者是'i'
     * 每个元音'i'后面不能 再跟着另一个'i'
     * 每个元音'o'后面只能跟着'i'或者是'u'
     * 每个元音'u'后面只能跟着'a'
     * 由于答案可能会很大，所以请你返回 模10^9 + 7之后的结果。
     *
     *
     *
     *
     * a前头可以是 e i u
     * e前头可以是 a i
     * i前头可以是 e o
     * o前头可以是 i
     * u前头可以是 i o
     *
     *
     *
     *
     * 使用二维数组 int array [5][n] 记录次数
     * 一层数组 代表以某元音字母结尾
     * 0 代表 a
     * 1 代表 e
     * 2 代表 i
     * 3 代表 o
     * 4 代表 u
     *
     * 二层数组
     * 代表下标长度的字符串总数
     *
     * array[0][n] = array[1][n-1] + array[2][n-1] + array[4][n-1]
     * array[1][n] = array[0][n-1] + array[2][n-1]
     * array[2][n] = array[1][n-1] + array[3][n-1]
     * array[3][n] = array[2][n-1]
     * array[4][n] = array[2][n-1] + array[3][n-1]
     *
     * 5个变量相加，就是最终得数
     *
     */
    public int countVowelPermutation(int n) {
        int array [][] = new int[5][n+1];
        array[0][1] =1;
        array[1][1] =1;
        array[2][1] =1;
        array[3][1] =1;
        array[4][1] =1;
        for (int i=2;i<n+1;i++){
            array[0][i] =  (array[1][i-1] +array[2][i-1] +array[4][i-1])%mod;
            array[1][i] =  (array[0][i-1] +array[2][i-1])%mod;
            array[2][i] = ( array[1][i-1] +array[3][i-1])%mod;
            array[3][i] =  (array[2][i-1])%mod;
            array[4][i] = (array[2][i-1] + array[3][i-1])%mod;
        }

        return  (array[0][n]+array[1][n]+array[2][n]+array[3][n]+array[4][n]) %mod ;

    }

    public static void main(String[] args) {
        System.out.println(new Solution1220().countVowelPermutation(5));
    }
}
