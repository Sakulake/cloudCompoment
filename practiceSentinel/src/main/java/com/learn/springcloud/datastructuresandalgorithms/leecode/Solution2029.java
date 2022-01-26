package com.learn.springcloud.datastructuresandalgorithms.leecode;

public class Solution2029 {
    /**
     * Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
     *
     * Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones中移除任一石子。
     *
     * 如果玩家移除石子后，导致 所有已移除石子 的价值总和 可以被 3 整除，那么该玩家就 输掉游戏 。
     * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
     * 假设两位玩家均采用最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
     *
     * 将 数组内数据分为三类
     * 第一类  除3余1
     * 第二类  除3余2
     * 第三类  除3余0
     *
     * 如果 第三类数 的数量为偶数，且其他类数量不为0，这不需要考虑 三类数的操作， 相当于换手
     *
     * Alice 先手1     1 1 2 1 2 1 2 1 2 1 2 ... 抛出2个一类数后，  如果 一类数量 = 二类数量  Alice输    一类数量>二类数量 Alice 输  一类数量<二类数量  Alice赢
     *
     * Alice 先手2     2 2 1 2 1 2 1 2 1 2 1 ...  抛出2个二类数后， 如果 一类数量 = 二类数量  Alice输    一类数量>二类数量 Alice 赢  一类数量<二类数量  Alice输
     *
     *
     *
     *
     * 如果 除 第三类数 其他类数量且为0 ，Alise 输
     *
     *
     *
     * 如果 第三类数 的数量为奇数，且其他类数量且不为0
     *
     * Alice 先手1  1 3 1
     *                      2 1 2 1 2 1 2 1 2    抛出2个1类数据后   如果 一类数量 = 二类数量  Alice赢   一类数量>二类数量 Alice 赢  一类数量<二类数量  Alice输
     *
     *  Alice 先手2  2 3 2
     *                      1 1 2 1 2 1 2 1 2 1 2
     *
     *
     */
    public boolean stoneGameIX(int[] stones) {
        if(stones.length == 0){
            return false;
        }

        int firstCount = 0;
        int secondCount = 0;
        int thirdCount = 0;

        for(int i =0;i<stones.length;i++){
            int j = stones[i]%3;
            if(j==0){
                thirdCount++;
            }else if (j==1){
                firstCount++;
            }else {
                secondCount++;
            }
        }

        if(firstCount ==0 && secondCount==0 ){
            return false;
        }

        if(thirdCount%2 ==0){
            if(firstCount-2<secondCount  || secondCount -2 < firstCount){
                return true;
            }else{
                return false;
            }
        }else{
            if(firstCount-2<secondCount  || secondCount -2< firstCount ){
                return false;
            }else{
                return true;
            }
        }

    }

}
