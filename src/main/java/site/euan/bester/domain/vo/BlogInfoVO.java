package site.euan.bester.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BlogInfoVO implements Serializable {
    private String id;
    //作者
    private UserInfoVO author;
    //博客内容
    private String content;
    //发送时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime time;
    //点赞
    private List<Long> likes;
    //评论
    private List<BlogComment> comments;
    //转发
    private Integer reposts;
    //图片
    private List<String> images;
}
