package cn.ivanzhu.test.leetCode.test8;

import lombok.Data;

/**
 * @author ivanzhu
 * @time 2021/5/16 8:00 下午
 * @Version 1.0
 */
@Data
public class LeetCodeTest8 {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
//        System.out.println(myAtoi("-+0429"));
//        System.out.println(myAtoi("-2147483647"));
    }

    public static int myAtoi(String s) {
        if ("".equals(s) || s == null) {
            return 0;
        }
        int result = 0;
        int max = Math.floorDiv(Integer.MAX_VALUE, 10);
        int len = s.length();
        char[] charArr = s.toCharArray();
        boolean start = true;
        boolean negative = false;
        for (int i = 0; i < len; i++) {
            char c = charArr[i];
            if (c == ' ' && start) {
                continue;
            }
            if (c == '+') {
                if (start) {
                    start = false;
                    continue;
                }
                break;
            }
            if (c == '-') {
                if (start) {
                    negative = true;
                    start = false;
                    continue;
                }
                break;
            }
            if (c < 48 || c > 57) {
                break;
            }
            start = false;
            if (result > max) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            int n = c - 48;
            if (result == max) {
                if (negative && n >= 8) {
                    return Integer.MIN_VALUE;
                }
                if (!negative && n >= 7) {
                    return Integer.MAX_VALUE;
                }
            }
            result *= 10;
            result += n;
        }
        return negative ? -result : result;
    }
}
