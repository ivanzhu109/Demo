package cn.ivanzhu.test.leetCode.test5;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ivanzhu
 * @time 2021/4/12 12:36 上午
 * @Version 1.0
 */
@Data
public class LeetCodeTest5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || "".equals(s) || s.length() <= 1) {
            return s;
        }
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        // 最长回文字符串坐标差
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> indexList = indexMap.computeIfAbsent((int) c, k -> new ArrayList<>());
            if (indexList.size() > 0) {
                Integer tmpStart;
                for (Integer startIndex : indexList) {
                    tmpStart = startIndex;
                    int tmpEnd = i;
                    if (tmpEnd - tmpStart <= end - start) {
                        break;
                    }
                    while (tmpEnd > tmpStart) {
                        if (tmpEnd - tmpStart <= 2) {
                            start = startIndex;
                            end = i;
                            break;
                        }
                        tmpEnd--;
                        tmpStart++;
                        if (s.charAt(tmpStart) != s.charAt(tmpEnd)) {
                            break;
                        }
                    }
                }
            }
            indexList.add(i);
        }
        return s.substring(start, end + 1);
    }
//    public static String longestPalindrome(String s) {
//        if (s == null || "".equals(s) || s.length() <= 1) {
//            return s;
//        }
//        int[][] info = new int[123][2];
//        for (int i = 0; i < 123; i++) {
//            info[i][0] = -1;
//            info[i][1] = 0;
//        }
//        // 最长回文字符串坐标差
//        int start = 0;
//        int end = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            int startIndex = info[c][0];
//            if (startIndex == -1) {
//                info[c][0] = i;
//            } else {
//                info[c][1] = i;
//                if (i - startIndex > end - start) {
//                    int tmpStart = startIndex;
//                    int tmpEnd = i;
//                    while (tmpEnd > tmpStart) {
//                        if (tmpEnd - tmpStart <= 2) {
//                            start = startIndex;
//                            end = i;
//                            break;
//                        }
//                        tmpEnd--;
//                        tmpStart++;
//                        if (s.charAt(tmpStart) != s.charAt(tmpEnd)) {
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        return s.substring(start, end + 1);
//    }
}
