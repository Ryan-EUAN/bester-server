package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@ApiModel(value = "个人资料返回数据类型")
public class ProfileVO implements Serializable {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "生日")
    private String birthday;
    @ApiModelProperty(value = "出生地")
    private String[] birthplace;
    @ApiModelProperty(value = "居住地")
    private String[] residence;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "QQ")
    private String qq;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "个性签名")
    private String signature;
}
