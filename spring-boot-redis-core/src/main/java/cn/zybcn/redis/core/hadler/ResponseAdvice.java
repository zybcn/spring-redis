package cn.zybcn.redis.core.hadler;

import cn.zybcn.redis.core.common.ResponseBean;
import cn.zybcn.redis.core.config.RouterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author 程序员小张
 * @Date 2022-09-10 12:51
 */
@ControllerAdvice(annotations = RestController.class)
public class ResponseAdvice implements ResponseBodyAdvice<Object> {


    @Autowired
    private RouterConfig routerConfig;

    @Resource
    private HttpServletRequest request;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //判断是否需要进行拦截

        List<String> noInterceptUrls = routerConfig.getNoInterceptUrl();
        if (CollectionUtils.isEmpty(noInterceptUrls)) {
            return true;
        } else {
            //获取请求的路径
            String path = request.getRequestURI();
            AntPathMatcher pathMatcher = new AntPathMatcher();
            for (String api : noInterceptUrls) {
                if (pathMatcher.match(api, path)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof ResponseBean) {
            return body;
        } else {
            return new ResponseBean(HttpStatus.OK.value(), "操作成功", body);
        }
    }
}
