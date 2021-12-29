package com.learn.springcloud.datastructuresandalgorithms.linked;

/**
 * @author sdw
 * @version 1.0
 * @className Node
 * @date 2021/12/28 10:20:05
 * @description
 */

public class Node {
    private Node next;
    private String value;

    private Iterator<Node> iterator;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(String value) {
        this.value = value;
        iterator = new NodeIterator(this);
    }
    public void printAll(){
        System.out.println(this.value);
        while (this.iterator.hasNext()){
            System.out.println(this.iterator.next().getValue());
        }
    }

    public Iterator<Node> getIterator() {
        return iterator;
    }
}
