package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@ApiModel(value = "置顶轮播图信息")
public class CarouselTopVO implements Serializable {
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;
    @ApiModelProperty(value = "图片标题")
    private String title;
    @ApiModelProperty(value = "图片描述")
    private String description;
    @ApiModelProperty(value = "图片跳转地址")
    private String link;
}
