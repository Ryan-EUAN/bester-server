package site.euan.bester.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author EUAN
 * 帖子发表DTO
 */
@Data
@ApiModel(value = "帖子发布DTO")
public class SendPostInfoDTO {
    @ApiModelProperty(value = "帖子标题")
    private String title;
    @ApiModelProperty(value = "帖子内容")
    private String content;
    @ApiModelProperty(value = "帖子图片")
    private List<String> images;
    @ApiModelProperty(value = "是否立即发送")
    private Boolean timing;
    @ApiModelProperty(value = "发送时间")
    private Integer publishTime;
    @ApiModelProperty(value = "发送的模块ID")
    private Long plateId;
}
