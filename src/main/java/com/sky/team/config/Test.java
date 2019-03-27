package com.sky.team.config;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Test {
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "111111";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("user");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj);
    }
}
