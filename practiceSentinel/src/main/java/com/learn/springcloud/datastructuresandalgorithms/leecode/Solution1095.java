package com.learn.springcloud.datastructuresandalgorithms.leecode;

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution1095 {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int length = mountainArr.length();
        int high = binarySearchHigh(mountainArr,0,length-1);
        int targetIndex = binarySearchLeftTarget(mountainArr,0,high,target);
        if(targetIndex>-1){
            return targetIndex;
        }else{
            return  binarySearchRightTarget(mountainArr,high,length-1,target);
        }

    }

    public int binarySearchHigh(MountainArray array,int start,int end){
        int middleIndex = (end -start)/2 + start;
        int middleValue = array.get(middleIndex);
        if (middleIndex == start){
             if(array.get(start)>=array.get(end)){
                 return start;
             }else{
                 return end;
             }
        }
        int middleMoreValue = array.get(middleIndex+1);

        if(middleValue>middleMoreValue){ // 峰值在 middle左侧
            return binarySearchHigh(array,start,middleIndex);
        }else{// 峰值在 middle 右侧
            return binarySearchHigh(array,middleIndex,end);
        }

    }
    public int binarySearchLeftTarget(MountainArray array,int start,int end,int target){
        int middleIndex = (end -start)/2 +start;
        if (array.get(middleIndex) ==target ){
            while (middleIndex-1>=0&& array.get(middleIndex-1)==target){
                middleIndex =middleIndex-1;
            }
            return middleIndex;
        }

        if(end == start){
            return -1;
        }
        if (array.get(middleIndex)>target ){
            return binarySearchLeftTarget(array,start,middleIndex,target);
        }else{
            return binarySearchLeftTarget(array,middleIndex+1,end,target);
        }
    }


    public int binarySearchRightTarget(MountainArray array,int start,int end,int target){
        int middleIndex = (end -start)/2 +start;
        if (array.get(middleIndex) ==target ){
            while (middleIndex+1<array.length() && array.get(middleIndex+1)==target){
                middleIndex =middleIndex+1;
            }
            return middleIndex;
        }

        if(end == start){
            return -1;
        }
        if (array.get(middleIndex)>target ){
            return binarySearchRightTarget(array,middleIndex+1,end,target);
        }else{
            return binarySearchRightTarget(array,start,middleIndex,target);
        }
    }

    public static void main(String[] args) {
        int []a =new int[]{1,5,2};

        MountainArray array = new MountainClass(a);
        Solution1095 s = new Solution1095();
        System.out.println(s.findInMountainArray( 2,array));

    }

}
