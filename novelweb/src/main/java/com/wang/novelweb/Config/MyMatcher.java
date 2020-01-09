package com.wang.novelweb.Config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 商品类目
 *
 * @author wlh
 * @date 2019-11-12
 */
public class MyMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = md5(String.valueOf(usernamePasswordToken.getPassword()));
        String mysql_password = md5((String) info.getCredentials());
        return this.equals(password, mysql_password);
    }


    //将传进来的密码进行加密的方法
    private static String md5(String password) {
        String hashAlgorithmName = "MD5";
        Object salt = ByteSource.Util.bytes("123456");
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, password, salt, hashIterations);
        return result.toString();
    }

}
