package cn.ivanzhu.test.leetCode.test3;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ivanzhu
 * @time 2021/4/8 22:34
 * @Version 1.0
 */
@Data
public class LeetCodeTest3 {

    public static void main(String[] args) {
//        System.out.println((int) ' ');
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

//    public static int lengthOfLongestSubstring(String s) {
//        if (s == null || "".equals(s)) {
//            return 0;
//        }
//        int max = 0;
//        int count = 0;
//        int leftPoint = 0;
//        Map<Integer, Integer> map = new HashMap<>(s.length());
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            Integer index = map.get((int) c);
//            map.put((int) c, i);
//            if (index == null || index < leftPoint) {
//                count++;
//            }else {
//                count = i - index;
//                leftPoint = index + 1;
//            }
//            if (count > max) {
//                max = count;
//            }
//        }
//        return max;
//    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int max = 0;
        int count = 0;
        int leftPoint = 0;
        int[] table = new int[256];
        Arrays.fill(table, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = table[c];
            table[c] = i;
            if (index == -1 || index < leftPoint) {
                count++;
            }else {
                count = i - index;
                leftPoint = index + 1;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
