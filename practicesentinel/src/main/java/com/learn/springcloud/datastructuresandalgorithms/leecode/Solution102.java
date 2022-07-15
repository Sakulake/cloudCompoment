package com.learn.springcloud.datastructuresandalgorithms.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();


        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()){
            List<Integer> currList = new ArrayList<>();
            int size  = nodeQueue.size();
            for (int i = 0;i<size;i++){
                TreeNode node = nodeQueue.poll();
                if(node==null){
                    continue;
                }
                currList.add(new Integer(node.val));
                if(node.left!= null){
                    nodeQueue.offer(node.left);
                }
                if(node.right!= null){
                    nodeQueue.offer(node.right);
                }
            }
            if (currList.size() == 0){
                break;
            }
            list.add(currList);
        }
        return list;
    }
}
