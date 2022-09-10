package cn.zybcn.redis.core.exception;

/**
 * @Author 程序员小张
 * @Date 2022-09-10 12:20
 */
public class SystemException extends RuntimeException {


    public SystemException(String msg) {
        super(msg);
    }

    public SystemException() {
        super();
    }
}
