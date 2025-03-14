package site.euan.bester.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.exception.BaseException;

/**
 * @author EUAN
 * 全局异常处理器
 */
@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result<String> exceptionHandler(BaseException e) {
        log.error("异常信息：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
