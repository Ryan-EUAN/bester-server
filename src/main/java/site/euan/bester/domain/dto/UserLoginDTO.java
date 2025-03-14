package site.euan.bester.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author EUAN
 * 用户登录DTO
 */
@Data
@ApiModel("用户登录DTO")
public class UserLoginDTO implements Serializable {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
