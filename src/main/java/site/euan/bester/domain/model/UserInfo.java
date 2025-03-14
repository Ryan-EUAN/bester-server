package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

@Data
@TableName("tb_user_info")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    private String name;
    private String avatar;
    private Integer followers;
    private Integer following;
    private Integer posts;
    private BigInteger goldCoin;
}
