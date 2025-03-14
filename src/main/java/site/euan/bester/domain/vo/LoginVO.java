package site.euan.bester.domain.vo;

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
public class LoginVO implements Serializable {
    @ApiModelProperty(value = "JWT令牌")
    private String token;

    @ApiModelProperty(value = "返回的用户信息")
    private UserInfoVO info;
}