package cn.ivanzhu.test.leetCode.test4;

import lombok.Data;

/**
 * @author ivanzhu
 * @time 2021/4/10 9:23 上午
 * @Version 1.0
 */
@Data
public class LeetCodeTest4 {


    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            throw new IllegalArgumentException("计算参数错误");
        }
        int index1 = 0;
        int index2 = 0;
        int total = nums1.length + nums2.length;
        int left = total + 1 >> 1;
        int right = total + 2 >> 1;
        int[] result = new int[2];
        for (int i = 1; i <= total; i++) {
            int n;
            if (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] <= nums2[index2]) {
                    n = nums1[index1++];
                }else {
                    n = nums2[index2++];
                }
            } else if (index1 < nums1.length) {
                n = nums1[index1++];
            }else {
                n = nums2[index2++];
            }
            if (i == left) {
                result[0] = n;
            }
            if (i == right) {
                result[1] = n;
                break;
            }
        }
        return (double) (result[0] + result[1]) / 2;
    }
}
