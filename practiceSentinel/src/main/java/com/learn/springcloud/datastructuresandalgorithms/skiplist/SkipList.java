package com.learn.springcloud.datastructuresandalgorithms.skiplist;

import java.util.Random;

public class SkipList {
    /**
     * 索引最大层数
     */
    int MAX_LEVEL = 16;
    /**
     * 当前节点索引层数生成所需随机数
     */
    Random random = new Random();
    /**
     * 当前节点索引层数生成所需随机数
     */
    float threshold =0.5f;

    public class Node {
        int data;
        private Node forwards[] = new Node[MAX_LEVEL];

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    Node head = new Node(-1);

    public void insert (int value){
        Node node = new Node(value);
        int level = getRandomLevel();
        Node [] needUpdateNodes = new Node[level];
        Node current = head;
        for (int i = level-1; i>=0;i--){
           while (current.forwards[i].data<value && current.forwards[i].forwards[i]!=null){
               current= current.forwards[i].forwards[i];
           }
            needUpdateNodes[i] = current;
        }
        for (int i = 0;i<needUpdateNodes.length;i++){
            Node tempNode = needUpdateNodes[i];
            node.forwards[i] =tempNode.forwards[i];
            tempNode.forwards[i] = node;
        }
    }

    public Node find (int value){
        Node currentNode =head;
        for (int i =MAX_LEVEL-1;i>=0;i-- ){

            while (currentNode.data<value && currentNode.forwards[i]!=null){
                currentNode = currentNode.forwards[i];
            }
        }
        while (currentNode.forwards[0]!= null && currentNode.forwards[0].data <=value ){
            currentNode =currentNode.forwards[0];

            if (currentNode.data == value){
                return currentNode;
            }
        }
        return null;
    }

    public boolean delete (int value){
        Node currentNode =head;
        for (int i =MAX_LEVEL-1;i>=0;i-- ){

            while (currentNode.data<value && currentNode.forwards[i]!=null){
                currentNode = currentNode.forwards[i];
            }
        }
        while (currentNode.forwards[0]!= null && currentNode.forwards[0].data <=value ){
            if (currentNode.forwards[0].data == value){
                for (int i = MAX_LEVEL-1;i>=0;i--){
                    if(currentNode.forwards[i].forwards[i]!= null && currentNode.forwards[i].forwards[i].data ==value){
                        currentNode.forwards[i] = currentNode.forwards[i].forwards[i];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getRandomLevel() {
        int level = 0;
        while (random.nextFloat()>threshold && level<MAX_LEVEL){
            level++;
        }
        return level;
    }
}
