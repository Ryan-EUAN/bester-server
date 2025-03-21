package site.euan.bester.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import site.euan.bester.domain.model.BlogComment;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author EUAN
 * 博客信息返回类
 */
@Builder
@Data
@ApiModel(value = "博客信息返回类")
public class PostInfoVO implements Serializable {
    private String id;
    @ApiModelProperty(value = "作者信息")
    private UserInfoVO author;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime time;
    @ApiModelProperty(value = "点赞")
    private List<Long> likes;
    @ApiModelProperty(value = "评论")
    private List<BlogComment> comments;
    @ApiModelProperty(value = "转发")
    private Integer reposts;
    @ApiModelProperty(value = "图片")
    private List<String> images;
}
