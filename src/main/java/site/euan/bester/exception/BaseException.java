package site.euan.bester.exception;

/**
 * @author EUAN
 * 业务异常处理
 */
public class BaseException extends RuntimeException{
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
