package site.euan.bester.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @param <T>
 * @author EUAN
 * 封装返回类
 */
@Data
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "浏览器返回编码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回对象")
    private T data;

    public static <T> Result<T> success() {
        return success(200, null);
    }

    public static <T> Result<T> success(T data) {
        return success(200, data);
    }

    public static <T> Result<T> error(String message) {
        return error(400, message);
    }

    public static <T> Result<T> success(Integer code, T data) {
        Result<T> result = new Result<>();
        result.code = code;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }
}