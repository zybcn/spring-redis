package cn.zybcn.redis.core.utils;

import cn.zybcn.redis.core.dto.UserDTO;

/**
 * @Author 程序员小张
 * @Date 2022-09-08 23:59
 */
public class UserHolder {

    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user) {
        tl.set(user);
    }

    public static UserDTO getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }
}
