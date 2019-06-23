package com.springboot.learning.logincheck.servicce;


import com.springboot.learning.exception.UsDefException;
import com.springboot.learning.logincheck.util.Encrypt;
import org.springframework.util.StringUtils;

public class IUserTest {

    public String getPasswordEncript(String password) throws UsDefException {
        if (!StringUtils.isEmpty(password)) {
            if (password.startsWith("en-")) {
                return password;
            } else {
                Encrypt encrypt = new Encrypt();
                String text = encrypt.encrypt(password);
                text = "en-" + text;
                return text;
            }
        } else {
            return null;
        }
    }

    public static void main(String args[])throws Exception{
        IUserTest userTest = new IUserTest();
        String password = userTest.getPasswordEncript("1q2w!Q@W");
        System.out.println(password);
    }

}
