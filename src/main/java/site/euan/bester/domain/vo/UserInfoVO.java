package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author EUAN
 * 用户信息返回类
 */
@Builder
@Data
public class UserInfoVO implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    @ApiModelProperty(value = "粉丝")
    private Integer followers;
    @ApiModelProperty(value = "点赞")
    private Integer following;
    @ApiModelProperty(value = "帖子数量")
    private Integer posts;
}
