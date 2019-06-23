package com.springboot.learning.logincheck.util;


import com.springboot.learning.exception.UsDefException;
import org.apache.commons.codec.binary.Hex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密
 * auto songbl
 */

public class Encrypt {
    private static transient Log log =LogFactory.getLog(Encrypt.class);

    /**
     * 密码加密
     * 几种加密算法
     * MD5|SHA-1|SHA-256|SHA-512
     * @param text
     */
    public String encrypt(String text)throws UsDefException{
        if(StringUtils.isEmpty(text)){
            throw new UsDefException("加密密码不能为空!");
        }else{
            String result = null;
            String hashAlgorithm = null;
            String encoding = null;
            try {
                hashAlgorithm = "SHA-256";
                encoding = "UTF-8";
                MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
                digest.update(text.getBytes(encoding));
                byte[] bytes = digest.digest();
                result = new String(Hex.encodeHex(bytes));
                return result;
            } catch (NoSuchAlgorithmException var7) {
                log.error(var7.getMessage(), var7);
                throw new UsDefException("0", "Can't find hash algorithm " + hashAlgorithm, var7);
            } catch (UnsupportedEncodingException var8) {
                log.error(var8.getMessage(), var8);
                throw new UsDefException("0", "Can't find encoding for " + encoding, var8);
            } catch (Exception var9) {
                log.error(var9.getMessage(), var9);
                throw new UsDefException("0", "Hash加密出错！", var9);
            }
        }
    }


    public static void main(String args[])throws Exception{
        Encrypt encrypt = new Encrypt();
        String password = encrypt.encrypt("123456");
        System.out.println(password);
    }

}

//1,9/asia
