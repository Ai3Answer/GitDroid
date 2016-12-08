package com.feicuiedu.gitdroid.login.model;

/**
 * Created by gqq on 2016/12/8.
 */

// 主要是为了存储Token
public class UserRepo {

    private static String accessToken;

    // 判断一下当前有没有Token
    public static boolean hasAccessToken(){
        return accessToken !=null;
    }

    // 当前是不是为空
    public static boolean isEmpty(){
        return accessToken==null;
    }

    // 清空信息
    public static void clear(){
        accessToken = null;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        UserRepo.accessToken = accessToken;
    }
}
