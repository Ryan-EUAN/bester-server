package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user_info")
@ApiModel("用户信息")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("昵称")
    private String name;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("粉丝数")
    private Integer followers;
    @ApiModelProperty("关注")
    private Integer following;
    @ApiModelProperty("文章数")
    private Integer posts;
    @ApiModelProperty("金币")
    private BigInteger goldCoin;
    @ApiModelProperty("性别")
    private Integer gender;
    @ApiModelProperty("生日")
    private String birthday;
    @ApiModelProperty("出生地")
    private String birthplace;
    @ApiModelProperty("居住地")
    private String residence;
    @ApiModelProperty("个性签名")
    private String signature;
}
