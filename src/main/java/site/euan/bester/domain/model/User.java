package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author EUAN
 * 用户表映射实体类
 */
@Data
@TableName("tb_user")
@ApiModel("用户表映射实体类")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @TableField("email")
    @ApiModelProperty("绑定邮箱")
    private String email;

    @TableField("password")
    @ApiModelProperty("密码")
    private String password;

    @TableField("is_admin")
    @ApiModelProperty("是否为管理员")
    private Boolean isAdmin;

    @TableField("is_online")
    @ApiModelProperty("是否在线")
    private Boolean isOnline;
}
