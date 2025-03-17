package site.euan.bester.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "个人资料DTO")
public class ProfileDTO implements Serializable {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "生日")
    private String birthday;
    @ApiModelProperty(value = "出生地")
    private List<String> birthplace;
    @ApiModelProperty(value = "居住地")
    private List<String> residence;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "QQ")
    private String qq;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "个性签名")
    private String signature;
}
