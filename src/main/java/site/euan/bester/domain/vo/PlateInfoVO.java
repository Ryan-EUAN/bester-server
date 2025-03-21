package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "模块信息")
public class PlateInfoVO {
    @ApiModelProperty(value = "板块id")
    private Long id;
    @ApiModelProperty(value = "板块名称")
    private String name;
    @ApiModelProperty(value = "板块图标")
    private String icon;
    @ApiModelProperty(value = "板块数量")
    private Integer count;
    @ApiModelProperty(value = "主题数量")
    private Integer topics;
    @ApiModelProperty(value = "帖子数量")
    private Long posts;
    @ApiModelProperty(value = "最新发帖时间")
    private String lastPost;
    @ApiModelProperty(value = "模块跳转路径")
    private String path;
}
