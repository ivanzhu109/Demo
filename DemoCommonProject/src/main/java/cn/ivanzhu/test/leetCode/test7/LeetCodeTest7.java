package cn.ivanzhu.test.leetCode.test7;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ivanzhu
 * @time 2021/5/15 7:46 下午
 * @Version 1.0
 */
@Data
public class LeetCodeTest7 {

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(reverse(-123));
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        boolean negative = false;
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE) {
            return 0;
        }
        if (x < 0) {
            x = -x;
            negative = true;
        }
        if (x < 10) {
            return negative ? -x : x;
        }
        int result = 0;
        int max = Math.floorDiv(Integer.MAX_VALUE, 10);
        while (x > 0) {
            if (result > max) {
                result = 0;
                break;
            }
            result *= 10;
            int mod = Math.floorMod(x, 10);
            x = Math.floorDiv(x, 10);
            result += mod;
        }
        return negative ? -result : result;
    }

//    public static int reverse(int x) {
//        boolean negative = false;
//        if (x < 0) {
//            negative = true;
//            x = -x;
//        }
//        String s = String.valueOf(x);
//        char[] charArray = s.toCharArray();
//        int len = charArray.length;
//        char[] intArr = new char[len];
//        for (int i = len - 1; i >= 0; i--) {
//            intArr[i] = charArray[len - i - 1];
//        }
//        String s2 = new String(intArr);
//
//        try {
//            return negative ? -Integer.parseInt(s2) : Integer.parseInt(s2);
//        } catch (NumberFormatException e) {
//            return 0;
//        }
//    }

}
