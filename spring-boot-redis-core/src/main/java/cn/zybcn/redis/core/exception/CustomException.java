package cn.zybcn.redis.core.exception;

/**
 * @Author 程序员小张
 * @Date 2022-09-10 12:22
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException() {
        super();
    }
}
