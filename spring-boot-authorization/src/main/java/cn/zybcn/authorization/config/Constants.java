package cn.zybcn.authorization.config;

/**
 * @Author 程序员小张
 * @Date 2022-09-11 20:22
 */
public class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 1;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";


    public static final Long LOGIN_USER_TTL = 36000L;

    public static final String LOGIN_USER_KEY = "login:token:";
}
