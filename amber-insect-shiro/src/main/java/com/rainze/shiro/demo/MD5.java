package com.rainze.shiro.demo;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-shiro
 * @data 2020/5/4 16:53
 */
public class MD5 {
    /**
     *
     *  我们在数据库中保存的密码都是明文的，一旦数据库数据泄露，那就会造成不可估算的损失，所以我们通常都会使用非对称加密，简单理解也就是不可逆的加密，而 md5 加密算法就是符合这样的一种算法。
     *
     *  如下面的 liyuze 用 Md5 加密后，得到的字符串：e10adc3949ba59abbe56e057f20f883e，就无法通过计算还原回 liyuze，我们把这个加密的字符串保存在数据库中，等下次用户登录时我们把密码通过同样的算法加密后再从数据库
     *  中取出这个字符串进行比较，就能够知道密码是否正确了，这样既保留了密码验证的功能又大大增加了安全性，但是问题是：虽然无法直接通过计算反推回密码，但是我们仍然可以通过计算一些简单的密码加密后的 Md5 值进行比较，推算出原来的密码
     *
     *  比如我的密码是 liyuze，你的密码也是，通过 md5 加密之后的字符串一致，所以你也就能知道我的密码了，如果我们把常用的一些密码都做 md5
     *  加密得到一本字典，那么就可以得到相当一部分的人密码，这也就相当于 “破解” 了一样，所以其实也没有我们想象中的那么“安全”。
     *
     */

    public static void main(String[] args) {

        String password = "liyuze";
        String s = new Md5Hash(password).toString();
        System.out.println(s);

    }


}
