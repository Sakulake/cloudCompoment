package com.learn.springcloud.behaviordesignpattern.iterator;

/**
 * @author sdw
 * @date 2021/12/15 14:16:30
 * @description
 */

public interface Iterator<E> {
    public boolean hasNext();
    public E getCurrentItem();
    public E next();
}
