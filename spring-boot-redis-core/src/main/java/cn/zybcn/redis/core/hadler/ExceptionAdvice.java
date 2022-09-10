package cn.zybcn.redis.core.hadler;

import cn.zybcn.redis.core.common.ResponseBean;
import cn.zybcn.redis.core.exception.CustomException;
import cn.zybcn.redis.core.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 程序员小张
 * @Date 2022-09-10 12:17
 */
@RestControllerAdvice
public class ExceptionAdvice {


    /**
     * 捕捉自定义异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResponseBean handle(CustomException e) {
        return new ResponseBean(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
    }


    /**
     * 捕捉系统异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException.class)
    public ResponseBean handle(SystemException e) {
        return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
    }


    /**
     * 捕捉404异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseBean handle(NoHandlerFoundException e) {
        return new ResponseBean(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
    }


    /**
     * 捕捉其他所有异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
        return new ResponseBean(this.getStatus(request).value(), ex.toString() + ": " + ex.getMessage(), null);
    }


    /**
     * 获取状态码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
