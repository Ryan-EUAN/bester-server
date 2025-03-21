package site.euan.bester.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author EUAN
 * 登陆异常处理
 */
public class LoginException extends BaseException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}