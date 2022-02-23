package com.learn.springcloud.datastructuresandalgorithms.skiplist;

import java.util.Random;

public class SkipList2 {
    // 缩影最高层 16
    int maxlevel = 16;

    // 跳表头节点
    Node head = new Node(-1);



    // 节点
    public class Node{
        Random random = new Random();
        //值
        int value;
        //下一个节点的在每层索引
        Node[] next;

        int level = randomLevel();

        private int randomLevel() {
            int level = 0;
            while (random.nextFloat()>0.5 && level<maxlevel){
                level++;
            }
            return level;
        }

        public Node(int value) {
            this.value = value;
            if (value == -1){
                next =  new Node[maxlevel];
            }else{
                next =  new Node[level];
            }

        }
    }

    // 插入
    public void insert(int value){
        Node node = new Node(value);
        Node currNode = head;
        int level =node.level;
        Node[] needUpdate = new Node[level];
        for (int i=level-1;i>=0;i--){
            while (currNode.next[i]!=null && currNode.next[i].value<value){
                currNode = currNode.next[i];
            }
            needUpdate[i] = currNode;
        }
        for (int i = 0;i<level;i++){
            Node temp = needUpdate[i];
            node.next[i] =temp.next[i];
            temp.next[i] = node;
        }
    }


    public Node query(int value){
        Node currNode = head;
        for(int i = maxlevel-1;i>=0;i--){
            while (currNode.next[i]!=null && currNode.next[i].value<value){
                currNode=currNode.next[i];
            }

            if (currNode.next[0]!=null && currNode.next[0].value ==value){
                return currNode.next[0] ;
            }
        }
        return null;
    }


    public int delete(int value){
        int level =maxlevel;
        Node[] needUpdate = new Node[level];
        Node currNode = head;
        for (int i=level-1;i>=0;i--){
            while (currNode.next[i]!=null && currNode.next[1].value<value){
                currNode = currNode.next[i];
            }
            needUpdate[i] = currNode;
        }

        if (currNode.next[0]!=null && currNode.next[0].value==value){
            for (int i = 0;i<needUpdate.length;i++){
                if(needUpdate[i]!=null && needUpdate[i].next[i].value ==value){
                    needUpdate[i].next[i] = needUpdate[i].next[i].next[i];
                }
            }
            return 1;
        }
        return 0;
    }
    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.next;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].value : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].next)[i] != null);
            System.out.println();
            d = c;
        }
    }


    public static void main(String[] args) {
//        SkipList2 list = new SkipList2();
//        list.insert(1, 3);
//        list.insert(2, 3);
//        list.insert(3, 2);
//        list.insert(4, 4);
//        list.insert(5, 10);
//        list.insert(6, 4);
//        list.insert(8, 5);
//        list.insert(7, 4);
//        list.printAll_beautiful();
//        list.printAll();
        /**
         * 结果如下：
         * 									    null:15-------
         * 									    null:14-------
         * 									    null:13-------
         * 									    null:12-------
         * 									    null:11-------
         * 									    null:10-------
         * 										   5:9-------
         * 										   5:8-------
         * 										   5:7-------
         * 										   5:6-------
         * 										   5:5-------
         * 										   5:4-------					 8:4-------
         * 							     4:3-------5:3-------6:3-------7:3-------8:3-------
         * 1:2-------2:2-------		     4:2-------5:2-------6:2-------7:2-------8:2-------
         * 1:1-------2:1-------3:1-------4:1-------5:1-------6:1-------7:1-------8:1-------
         * 1:0-------2:0-------3:0-------4:0-------5:0-------6:0-------7:0-------8:0-------
         * { data: 1; levels: 3 } { data: 2; levels: 3 } { data: 3; levels: 2 } { data: 4; levels: 4 }
         * { data: 5; levels: 10 } { data: 6; levels: 4 } { data: 7; levels: 4 } { data: 8; levels: 5 }
         */
        // 优化后insert()

        SkipList2 list2 = new SkipList2();
        list2.insert(1);
        list2.insert(2);
        list2.insert(6);
        list2.insert(7);
        list2.insert(8);
        list2.insert(3);
        list2.insert(4);
        list2.insert(5);
        System.out.println();
        list2.printAll_beautiful();


    }

}
