package com.learn.springcloud.datastructuresandalgorithms.linked;

/**
 * @author sdw
 * @version 1.0
 * @className NodeIterator
 * @date 2021/12/28 10:48:44
 * @description
 */

public class NodeIterator implements Iterator<Node>{
    private Node head ;
    private Node cur;
    public NodeIterator(Node node) {
        this.head = node;
        this.cur =node;
    }

    @Override
    public boolean hasNext() {
        return cur.getNext()!=null;
    }

    @Override
    public Node next() {
        cur=cur.getNext();
        return cur;
    }

    @Override
    public Node getCurrentItem() {
        return cur;
    }
}
