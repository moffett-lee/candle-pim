package com.amber.insect.jvm;

/**
 * @ClassName OriginalCode
 * @Description 原码 反码 补码
 * @Author amber.liyuze
 * @Date 2021/11/23 20:46
 * @Version 1.0
 **/
public class OriginalCode {



    /** 打印整数的二进制表示
     * @Author amber.liyuze
     * @Description
     * @Date 2021/11/23 20:49
     * @Param [java.lang.String[]]
     * @Return void
     */
    public static void main(String[] args) {
        int a = -6;
        for (int i = 0; i < 32; i++) {
            int t = (a & 0x80000000 >>> i) >>> (31 - i);
            System.out.println(t);
        }
    }


}
