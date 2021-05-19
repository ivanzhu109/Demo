package cn.ivanzhu.test.leetCode.test9;

import lombok.Data;

/**
 * @author ivanzhu
 * @time 2021/5/17 18:52
 * @Version 1.0
 */
@Data
public class LeetCodeTest9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(21120));
    }

    /**
     * 参考leetCode
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int mod = Math.floorMod(x, 10);
        if (mod == 0) {
            return false;
        }
        x = Math.floorDiv(x, 10);
        int n = mod;
        while (n <= x) {
            if (n == x) {
                return true;
            }
            int div = Math.floorDiv(x, 10);
            if (div > 0 && n == div) {
                return true;
            }
            n *= 10;
            n += Math.floorMod(x, 10);
            x = div;
        }
        return false;
    }


//    /**
//     * 自己编写代码
//     * @param x
//     * @return
//     */
//    public static boolean isPalindrome(int x) {
//        if (x < 0) {
//            return false;
//        }
//        if (x < 10) {
//            return true;
//        }
//        int max = Math.floorDiv(Integer.MAX_VALUE, 10);
//        int reverse = 0;
//        int div = x;
//        while (div > 0) {
//            int mod = Math.floorMod(div, 10);
//            div = Math.floorDiv(div, 10);
//            if (reverse > max) {
//                return false;
//            }
//            reverse *= 10;
//            reverse += mod;
//        }
//        return reverse == x;
//    }
}
