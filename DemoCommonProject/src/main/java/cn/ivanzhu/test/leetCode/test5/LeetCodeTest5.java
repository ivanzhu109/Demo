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

    // Manacher算法
    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        // 字符串预处理，保证字符串为奇数，方便处理
        int sLen = 2 * s.length() + 1;
        String newStr = addSign(s, '#');
        int[] p = new int[sLen];
        int maxRight = 0;
        int center = 0;
        int maxLen = 1;
        int begin = 0;
        char[] charArray = newStr.toCharArray();
        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                p[i] = Math.min(p[mirror], maxRight - i);
            }
            int left = i - p[i] - 1;
            int right = i + p[i] + 1;
            while (left >= 0 && right < sLen) {
                if (charArray[left] != charArray[right]) {
                    break;
                }
                left--;
                right++;
                p[i]++;
            }
            right --;
            left ++;
            if (right > maxRight) {
                maxRight = right;
                center = i;
            }
            int len = p[i] + 1;
            if (len > maxLen) {
                maxLen = len;
                begin = left;
            }
        }
        return removeSign(charArray, begin, maxLen, '#');
    }

    private static String removeSign(char[] chars, int begin, int len, char removeChar) {
        int newCharLen = len - 1;
        char[] newChars = new char[newCharLen];
        int i = chars[begin] == removeChar ? ++ begin : begin;
        for (int j = 0; j < newCharLen; j++) {
            newChars[j] = chars[i];
            i = i + 2;
        }
        return new String(newChars);
    }

    private static String addSign(String s, Character c) {
        char[] charArray = s.toCharArray();
        char[] newCharArray = new char[2 * charArray.length + 1];
        newCharArray[0] = c;
        for (int i = 0; i < charArray.length; i++) {
            char cc = charArray[i];
            newCharArray[2 * i + 1] = cc;
            newCharArray[2 * (i + 1)] = c;
        }
        return new String(newCharArray);
    }


    /**
     * 动态规划代码
     */
//    public static String longestPalindrome(String s) {
//        if (s == null || "".equals(s) || s.length() <= 1) {
//            return s;
//        }
//
//        int start = 0;
//        int end = 0;
//        int[][] isPalindromeArray = new int[s.length()][s.length()];
//        int[][] indexArray = new int[123][s.length() + 1];
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            int count = indexArray[c][0];
//            indexArray[c][0] = ++count;
//            indexArray[c][count] = i;
//            for (int j = 1; j <= count; j++) {
//                int startIndex = indexArray[c][j];
//                if (startIndex >= i) {
//                    break;
//                }
//                int length = i - startIndex;
//                if (s.charAt(startIndex) == s.charAt(i)) {
//                    if (length > 2 && isPalindromeArray[startIndex + 1][i - 1] != 1) {
//                        continue;
//                    }
//                    isPalindromeArray[startIndex][i] = 1;
//                    if (length > end - start) {
//                        start = startIndex;
//                        end = i;
//                    }
//                }
//            }
//        }
//        return s.substring(start, end + 1);
//    }

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
