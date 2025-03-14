package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_blog_comment")
public class BlogComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("send_id")
    private Long sendId;
    @TableField("blog_id")
    private Long blogId;
    private String comment;
    @TableField("send_time")
    private LocalDateTime sendTime;
}
