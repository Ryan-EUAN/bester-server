package site.euan.bester.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author EUAN
 * 用户注册DTO
 */
@Data
@ApiModel("用户注册DTO")
public class UserRegisterDTO implements Serializable {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "是否为管理员", required = true)
    private Boolean isAdmin;
}
