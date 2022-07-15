package com.learn.springcloud.datastructuresandalgorithms.leecode;

public class MountainClass implements MountainArray {

      private int [] array;

      public MountainClass(int [] array) {
           this.array =array;
      }

      @Override
      public int get(int index) {
            return array[index];
      }

      @Override
      public int length() {
            return array.length;
      }
}
