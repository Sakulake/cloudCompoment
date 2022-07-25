package com.learn.springcloud.datastructuresandalgorithms.linked;

/**
 * @author sdw
 * @date 2021/12/28 10:46:40
 * @description
 */

public interface Iterator<E> {
    public boolean hasNext();
    public E next();
    public E getCurrentItem();
}
