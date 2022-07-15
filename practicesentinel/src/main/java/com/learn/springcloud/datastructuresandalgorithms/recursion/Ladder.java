package com.learn.springcloud.datastructuresandalgorithms.recursion;

import java.util.HashMap;

/**
 * @author sdw
 * @version 1.0
 * @className Ladder
 * @date 2021/12/29 10:31:00
 * @description 阶梯问题
 * 阶梯总共有n层，一次上1个或者2个台阶，总共有几种走法
 *
 * 仔细想一下第一步，不是1个台阶就是两个台阶。
 * 所以 n 个台阶的走法就等于先走 1 阶后，n-1 个台阶的走法 加上先走 2 阶后，n-2 个台阶的走法。用公式表示就是：
 * f(n) = f(n-1)+f(n-2)
 *
 * 终止条件：f(1) 的时候，一个台阶，只有一种走法，f(2)=2,两个台阶只有2种走法。
 */

public class Ladder {
    public static HashMap<Integer,Integer> redurceResult = new HashMap<>();

    static int depth =0;

    public static int getTypeCount(int ladders){
        depth++;

        if (depth>1000){
            return -1;
        }
        if (ladders<=0){
            return -1;
        }

        if(ladders == 1){
            return 1;
        }

        if (ladders ==2 ){
            return 2;
        }

        int reuslt =0;
        if(redurceResult.get(ladders)==null) {
            reuslt = getTypeCount(ladders - 1) + getTypeCount(ladders - 2);
            redurceResult.put(ladders, reuslt);
        }else{
            reuslt =  redurceResult.get(ladders);
        }
        return reuslt;
    }

    public static void main(String[] args) {
        System.out.println(getTypeCount(10));
    }
}
