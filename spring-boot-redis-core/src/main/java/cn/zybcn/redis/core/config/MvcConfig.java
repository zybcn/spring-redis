package cn.zybcn.redis.core.config;

import cn.zybcn.redis.core.interceptor.LoginInterceptor;
import cn.zybcn.redis.core.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author 程序员小张
 * @Date 2022-09-08 23:36
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RouterConfig routerConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token刷新的拦截器
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
        // 登陆拦截器
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(routerConfig.getUrls()).order(1);

    }
}
