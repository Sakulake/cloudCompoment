package com.learn.springcloud.datastructuresandalgorithms.linked;

/**
 * @author sdw
 * @version 1.0
 * @className LinkedTest
 * @date 2021/12/28 10:19:39
 * @description
 */

public class LinkedTest {
    public static Node convertLink(Node node){
        if (null == node || null == node.getNext()){
            return node;
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
        node.printAll();
        node = convertLink(node);
        node.printAll();
    }

    private static Node initNode() {
        Node node =null;
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
        return head;
    }
}
