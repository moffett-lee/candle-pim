package com.rainze.shiro.demo;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-shiro
 * @data 2020/5/4 16:59
 */
public class ShiroMd5 {






    /**
     * 加盐 + 多次加密
     * 既然相同的密码 md5 一样，那么我们就让我们的原始密码再加一个随机数，然后再进行 md5 加密，这个随机数就是我们说的盐 (salt)，这样处理下来就能得到不同的 Md5 值，当然我们需要把这个随机数盐也保存进数据库中，以便我们进行验证。
     *
     * 另外我们可以通过多次加密的方法，即使黑客通过一定的技术手段拿到了我们的密码 md5 值，但它并不知道我们到底加密了多少次，所以这也使得破解工作变得艰难。
     *
     * 在 Shiro 框架中，对于这样的操作提供了简单的代码实现：
     * @Date: 2020/5/4 16:59
     */
    public static void main(String[] args) {

        String password = "liyuze";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();

        int times = 2;  // 加密次数：2

        String alogrithmName = "md5";   // 加密算法

        String encodePassword = new SimpleHash(alogrithmName, password, salt, times).toString();
        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,encodePassword);
    }
}
