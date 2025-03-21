package site.euan.bester.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 令牌异常处理器
 * @author EUAN
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenException extends BaseException{
    public TokenException() {
        super();
    }

    public TokenException(String message) {
        super(message);
    }
}
