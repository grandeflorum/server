package com.grandeflorum.common.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {

    //算法方式
    private static final String KEY_ALGORITHM = "AES";
    //算法/模式/填充
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    //字符串编码
    private static final String KEY_CHARSET = "UTF-8";
    //密钥字节大小
    private static final Integer PRIVATE_KEY_BIT_SIZE = 128;
    //默认密码
    public static final String DEFAULT_PASSWORD = "abcdefgabcdefg12";

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(KEY_CHARSET);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"));
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String encrypt(String content) {
        return encrypt(content, DEFAULT_PASSWORD);
    }

    /**
     * AES 解密操作
     *
     * @param content  待解密内容
     * @param password 解密密码
     * @return 返回Base64转码后的解密数据
     */
    public static String decrypt(String content, String password) {
        if (content != null && content.length() > 0) {
            try {
                Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"));
                byte[] result = cipher.doFinal(Base64.decodeBase64(content));
                return new String(result, KEY_CHARSET);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static String decrypt(String content) {
        return decrypt(content, DEFAULT_PASSWORD);
    }

    /**
     * 生成内部加密密钥
     *
     * @return 内部密钥
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        try {
            //防止Linux下生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(PRIVATE_KEY_BIT_SIZE, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String text = "abc123456789";
        System.out.println("text -> " + text);
        String encrypt = AESUtils.encrypt(text, "abcdefgabcdefg12");
        System.out.println("encrypt -> " + encrypt);
        System.out.println("decrypt -> " + AESUtils.decrypt(encrypt, "abcdefgabcdefg12"));
    }

}
