package cn.zybcn.redis.core.common;

import cn.zybcn.redis.core.enums.StatusCodeEnum;

/**
 * @Author 程序员小张
 * @Date 2022-09-11 18:53
 */
public class ResultUtils {


    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<T>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseBean<T> fail(T data) {
        return new ResponseBean<T>(StatusCodeEnum.FAIL.getCode(), StatusCodeEnum.FAIL.getMsg(), data);
    }

    public static <T> ResponseBean<T> result(Integer code, String msg, T data) {
        return new ResponseBean<T>(code, msg, data);
    }

    public static <T> ResponseBean<T> result(T data) {
        return new ResponseBean<T>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg(), data);
    }
}
