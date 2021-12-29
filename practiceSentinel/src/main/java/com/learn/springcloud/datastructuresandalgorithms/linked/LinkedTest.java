package com.learn.springcloud.datastructuresandalgorithms.linked;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sdw
 * @version 1.0
 * @className LinkedTest
 * @date 2021/12/28 10:19:39
 * @description
 */

public class LinkedTest {

    /**
     * @Author sdw
     * @Description //翻转单链表
     * @Date  2021/12/28 2:17 下午
     * @Param  * @param node
     * @return com.learn.springcloud.datastructuresandalgorithms.linked.Node
     **/
    public static Node convertLink(Node node){
        if (null == node || null == node.getNext()){
            return node;
        }

        if(checkHasCircle(node)){
            return null;
        }
        Node head =null;
        Node tail =null;
        Node tmp = null;
        tmp = node.getNext();
        head = tmp;
        node.setNext(null);
        tail = node;

        while (null != tmp.getNext()){
            head = tmp.getNext();
            tmp.setNext(tail);
            tail =tmp;
            tmp = head;
        }

        head.setNext(tail);

        return head;
    }

    public static void main(String[] args) {
        Node node = initNode();
//        node.printAll();
        node = convertLink(node);
        node.printAll();

        boolean hasCircle = checkHasCircle(node);
        System.out.println("hasCircle:"+ hasCircle);
    }

    /*
    /**
     * @Author sdw
     * @Description //校验链表中是否有重复
     * @Date  2021/12/28 2:16 下午 
     * @Param  * @param node
     * @return boolean
     **/
    private static boolean checkHasCircle(Node node) {
        if(null==node){
            return false;
        }
        Set<Node> set = new HashSet<>();
        Iterator iterator =  node.getIterator();
        while (iterator.hasNext()){
            Node node1 = (Node) iterator.next();
            if(set.contains(node1)){
               return true;
            }
            set.add(node1);
        }
        return false;
    }

    private static Node initNode() {

        Node head = null;
        Node tmp =null;
        for (int i=0;i<5;i++){
            Node newNode = new Node(String.valueOf(i));
            if(i==0){
                head = newNode;
            }
            if(tmp!=null){
                tmp.setNext(newNode);
            }
            tmp=newNode;
        }
        //添加尾部重复
        Node node = new Node("4");
        node.setNext(tmp);
        tmp.setNext(node);
        return head;
    }
}
