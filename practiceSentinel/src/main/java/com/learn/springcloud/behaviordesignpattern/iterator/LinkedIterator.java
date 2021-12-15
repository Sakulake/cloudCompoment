package com.learn.springcloud.behaviordesignpattern.iterator;

import java.util.LinkedList;

/**
 * @author sdw
 * @version 1.0
 * @className LinkedIterator
 * @date 2021/12/15 14:19:04
 * @description
 */

public class LinkedIterator implements Iterator{

    private LinkedList link = new LinkedList();

    private int cursor ;

    public LinkedIterator(LinkedList link) {
        this.link = link;
        cursor = 0;
    }

    @Override
    public boolean hasNext() {
        if(cursor>=link.size()){
            return false;
        }
       return true;
    }

    @Override
    public Object getCurrentItem() {
        return link.get(cursor);
    }

    @Override
    public Object next() {
        ++cursor;
        return link.get(cursor);
    }
}
