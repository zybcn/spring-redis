package cn.zybcn.authorization.utils;

import cn.zybcn.authorization.dto.UserDTO;

/**
 * @Author 程序员小张
 * @Date 2022-09-11 21:07
 */
public class UserHolder {

    private static final ThreadLocal<UserDTO> userInfo = new ThreadLocal<>();

    public static void saveUser(UserDTO user) {
        userInfo.set(user);
    }

    public static UserDTO getUser() {
        return userInfo.get();
    }

    public static void removeUser() {
        userInfo.remove();
    }
}
