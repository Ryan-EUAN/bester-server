package site.euan.bester.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @MongoId
    private String id;
    private String connect;
    @Field("send_id")
    private Long sendId;
    private List<String> urls;
    private Integer reposts;
    private List<Long> likes;
    private List<BlogComment> comments;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime time;
}