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
        System.out.println(longestPalindrome("babad"));
    }

    /**
     * 动态规划代码
     */
    public static String longestPalindrome(String s) {
        if (s == null || "".equals(s) || s.length() <= 1) {
            return s;
        }

        int start = 0;
        int end = 0;
        int[][] isPalindromeArray = new int[s.length()][s.length()];
        int[][] indexArray = new int[123][s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = indexArray[c][0];
            indexArray[c][0] = ++count;
            indexArray[c][count] = i;
            for (int j = 1; j <= count; j++) {
                int startIndex = indexArray[c][j];
                if (startIndex >= i) {
                    break;
                }
                int length = i - startIndex;
                if (s.charAt(startIndex) == s.charAt(i)) {
                    if (length > 2 && isPalindromeArray[startIndex + 1][i - 1] != 1) {
                        continue;
                    }
                    isPalindromeArray[startIndex][i] = 1;
                    if (length > end - start) {
                        start = startIndex;
                        end = i;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 自己写的代码
     */
//    public static String longestPalindrome(String s) {
//        if (s == null || "".equals(s) || s.length() <= 1) {
//            return s;
//        }
//        Map<Integer, List<Integer>> indexMap = new HashMap<>();
//        // 最长回文字符串坐标差
//        int start = 0;
//        int end = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            List<Integer> indexList = indexMap.computeIfAbsent((int) c, k -> new ArrayList<>());
//            if (indexList.size() > 0) {
//                Integer tmpStart;
//                for (Integer startIndex : indexList) {
//                    tmpStart = startIndex;
//                    int tmpEnd = i;
//                    if (tmpEnd - tmpStart <= end - start) {
//                        break;
//                    }
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
//            indexList.add(i);
//        }
//        return s.substring(start, end + 1);
//    }
}
