package cn.zybcn.authorization.controller;

import cn.zybcn.authorization.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 程序员小张
 * @Date 2022-09-11 20:46
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenManager tokenManager;

    /**
     * 登陆
     */
    @GetMapping("/longin")
    public String login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");
        //查询数据库 获取userid
        String userid = "";
        //生成一个token，保存用户登录状态
        return tokenManager.createToken(userid);

    }


    /**
     * 注销登陆
     */
    @DeleteMapping("/logout")
    public Boolean logout(@RequestParam String token) {
        return tokenManager.deleteToken(token);
    }

}
