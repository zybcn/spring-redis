package cn.zybcn.redis.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author 程序员小张
 * @Date 2022-09-09 22:52
 */
@Configuration
@ConfigurationProperties("router")
public class RouterConfig {

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
