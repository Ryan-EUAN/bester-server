package site.euan.bester.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "帖子实体类")
public class Post {
    @MongoId
    @ApiModelProperty(value = "帖子ID")
    private String id;
    @ApiModelProperty(value = "帖子标题")
    private String title;
    @ApiModelProperty(value = "帖子内容")
    private String content;
    @Field("send_id")
    @ApiModelProperty(value = "发帖人ID")
    private Long sendId;
    @ApiModelProperty(value = "帖子附带的图片")
    private List<String> images;
    @ApiModelProperty(value = "帖子转发数")
    private Integer reposts;
    @ApiModelProperty(value = "帖子点赞数")
    private List<Long> likes;
    @ApiModelProperty(value = "帖子评论")
    private List<BlogComment> comments;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "发帖时间")
    private LocalDateTime time;
    @ApiModelProperty(value = "发帖的板块ID")
    private Long plateId;
}