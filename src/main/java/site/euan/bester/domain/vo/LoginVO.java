package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author EUAN
 * 登陆信息返回类
 */
@Builder
@Data
@ApiModel(value = "登陆信息返回类")
public class LoginVO implements Serializable {
    @ApiModelProperty(value = "JWT令牌")
    private String token;
    @ApiModelProperty(value = "JWT令牌过期时间")
    private Long expireTime;
    @ApiModelProperty(value = "返回的用户信息")
    private UserInfoVO info;
}