package com.example.demo.leetCode.array;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。  请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * @Author zhoushenghua on
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {6,7,8,9};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int l1 = nums1.length,l2 = nums2.length;
        if(l1 == 0 || l2 == 0){
            if(l1 == 0){
                return getMedianNum(nums2);
            }else{
                return getMedianNum(nums1);
            }
        }

        int[] combinatArr = new int[l1+l2];
        if(nums1[l1-1] < nums2[0]){
            combinatArr = combinat(combinatArr, nums1, nums2);
        }else if(nums2[l2-1] < nums1[0]){
            combinatArr = combinat(combinatArr, nums2, nums1);
        }else{
            int indexOfCombinat = 0;
            int index1 = 0;
            int index2 = 0;
            while(true){
                if(index1 > nums1.length-1){
                    for(int i = nums1.length + index2; i <nums1.length+nums2.length; i++){
                        combinatArr[i] = nums2[index2];
                        index2++;
                    }
                    break;
                }
                if(index2 > nums2.length-1){
                    for(int i = nums2.length + index1; i < (nums1.length+nums2.length); i++){
                        combinatArr[i] = nums1[index1];
                        index1++;
                    }
                    break;
                }
                if(nums1[index1] < nums2[index2]){
                    combinatArr[indexOfCombinat] = nums1[index1];
                    index1++;
                }else{
                    combinatArr[indexOfCombinat] = nums2[index2];
                    index2++;
                }
                indexOfCombinat++;
            }
        }
        return getMedianNum(combinatArr);

    }

    public static int[] combinat(int[] combinatArr, int[] nums1, int[] nums2){
        System.arraycopy(nums1,0, combinatArr,0,nums1.length);
        System.arraycopy(nums2,0, combinatArr, nums1.length, nums2.length);
        return combinatArr;
    }

    public static double getMedianNum(int[] combinatArr){
        if(combinatArr.length%2 == 1){
            return combinatArr[(combinatArr.length + 1)/2 - 1];
        }else{
            return ((double)combinatArr[combinatArr.length/2 - 1] + combinatArr[combinatArr.length/2])/2;
        }
    }

}
