package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@ApiModel(value = "实名认证返回结果VO")
public class VerifyVO implements Serializable {
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "手机号是否已认证")
    private Boolean phoneVerified;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "身份证号是否已认证")
    private Boolean identityVerified;
}
