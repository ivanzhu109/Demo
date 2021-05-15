package cn.ivanzhu.test.leetCode.test6;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ivanzhu
 * @time 2021/5/13 11:47
 * @Version 1.0
 */
@Data
public class LeetCodeTest6 {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("ABCDE", 4));
    }

    public static String convert(String s, int numRows) {
        int len = s.length();
        if (len <= numRows || numRows < 2) {
            return s;
        }
        // 每行开始前字符的个数
        int[] lenArr = new int[numRows];
        // 计算z形包含字符的数量
        int halfCharNum = Math.max((numRows << 1) - 2, 1);
        int div = Math.floorDiv(len, halfCharNum);
        int mod = Math.floorMod(len, halfCharNum);
        int more = mod < numRows ? 0 : 1;
        mod = Math.floorMod(mod, numRows);
        int totalRowLen = 0;
        for (int i = 0; i < numRows; i++) {
            lenArr[i] = totalRowLen;
            int rowLen = (i > 0 ? div << 1  : div) + more;
            if (more == 1) {
                if (mod > 0 && i == numRows - mod - 1) {
                    rowLen++;
                    mod--;
                }
            }else {
                rowLen += mod-- > 0 ? 1 : 0;
            }
            totalRowLen = totalRowLen + rowLen;
        }
        char[] charArr = s.toCharArray();
        char[] newCharArr = new char[len];
        int[] rowNumArr = new int[numRows];
        int m = 0;
        int halfZ = 1;
        for (int i = 0; i < len; i++) {
            if (halfZ > numRows) {
                m = halfCharNum - halfZ + 1;
            }
            if (halfZ > halfCharNum) {
                halfZ = 1;
                m = 0;
            }
            newCharArr[rowNumArr[m]++ + lenArr[m]] = charArr[i];
            halfZ++;
            m++;
        }
        return new String(newCharArr);
    }


    /**
     * 第一种解法
     */
//    public static String convert(String s, int numRows) {
//        int len = s.length();
//        if (len <= numRows || numRows < 2) {
//            return s;
//        }
//        // 计算z形包含字符的数量
//        int halfCharNum = Math.max((numRows << 1) - 2, 1);
//        int halfZLen = Math.max(numRows - 1, 1);
//        int halfZNum = Math.floorDiv(len, halfCharNum);
//        int allZLen = halfZLen * halfZNum;
//        int mod;
//        if ((mod = Math.floorMod(len, halfCharNum)) > 0) {
//            allZLen ++;
//            for (int i = 0; i < mod - numRows; i++) {
//                allZLen++;
//            }
//        }
//        // 双层数组存储z形信息
//        char[][] doubleCharArray = new char[numRows][allZLen];
//        char[] charArray = s.toCharArray();
//        int m = 0;
//        int n = 0;
//        int halfZ = 1;
//        for (int i = 0; i < len; i++) {
//            if (halfZ > numRows) {
//                n++;
//                m = halfCharNum - halfZ + 1;
//            }
//            if (halfZ > halfCharNum) {
//                halfZ = 1;
//                m = 0;
//            }
//            doubleCharArray[m][n] = charArray[i];
//            halfZ++;
//            m++;
//        }
//        char[] newCharArray = new char[len];
//        int index = 0;
//        char nil = (char) 0;
//        for (int i = 0; i < numRows; i++) {
//            char[] iChars = doubleCharArray[i];
//            for (char iChar : iChars) {
//                if (iChar == nil) {
//                    continue;
//                }
//                newCharArray[index++] = iChar;
//            }
//        }
//        return new String(newCharArray);
//    }
}
