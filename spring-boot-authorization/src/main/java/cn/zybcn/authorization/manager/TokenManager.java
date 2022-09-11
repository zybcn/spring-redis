package cn.zybcn.authorization.manager;

/**
 * @Author 程序员小张
 * @Date 2022-09-11 20:24
 * @Desc 对token进行操作的接口
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     *
     * @param userId 指定用户的id
     * @return 生成的token
     */
    String createToken(String userId);

    /**
     * 检查token是否有效
     *
     * @param  token
     * @return 是否有效
     */
    boolean checkToken(String token);

    /**
     * 从字符串中解析token
     *
     * @param authentication 加密后的字符串
     * @return
     */
    String getToken(String authentication);

    /**
     * 清除token
     *
     * @param token 登录用户的token
     */
    Boolean deleteToken(String token);

}
