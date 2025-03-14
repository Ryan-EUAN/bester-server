package site.euan.bester.exception;

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