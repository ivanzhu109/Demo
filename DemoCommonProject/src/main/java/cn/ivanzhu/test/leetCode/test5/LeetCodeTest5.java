package cn.ivanzhu.test.leetCode.test5;

import lombok.Data;

/**
 * @author ivanzhu
 * @time 2021/4/12 12:36 上午
 * @Version 1.0
 */
@Data
public class LeetCodeTest5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccc"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || "".equals(s) || s.length() <= 1) {
            return s;
        }
        int[][] info = new int[123][2];
        for (int i = 0; i < 123; i++) {
            info[i][0] = -1;
            info[i][1] = -1;
        }
        int max = 0;
        char maxChar = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int startIndex = info[c][0];
            if (startIndex == -1) {
                info[c][0] = i;
            } else {
                int endIndex = info[c][1];
                if (endIndex != -1
                        && i + 1 < s.length()
                        && s.charAt(i + 1) != c) {
                    info[c][0] = endIndex;
                    startIndex = endIndex;
                }
                info[c][1] = i;
                int sub = i - startIndex;
                if (sub > max) {
                    max = sub;
                    maxChar = c;
                }
            }
        }
        if (max > 0) {
            return s.substring(info[maxChar][0], info[maxChar][1] + 1);
        }
        return String.valueOf(s.charAt(0));
    }
}
