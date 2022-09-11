package cn.zybcn.authorization.manager.impl;

import cn.zybcn.authorization.config.Constants;
import cn.zybcn.authorization.dto.UserDTO;
import cn.zybcn.authorization.manager.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 程序员小张
 * @Date 2022-09-11 20:28
 */
@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<String, String> redis;

    @Autowired
    public void setRedis(RedisTemplate<String, String> redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public String createToken(String userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        //存储到redis并设置过期时间
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userId", userId);
        redis.opsForHash().putAll(token, userMap);
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return token;
    }

    @Override
    public String getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        return param[1];
    }

    @Override
    public boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        String userId = redis.boundValueOps(token).get();
        return !StringUtils.isEmpty(userId);
    }

    @Override
    public Boolean deleteToken(String userId) {
        return redis.delete(userId);
    }

}
