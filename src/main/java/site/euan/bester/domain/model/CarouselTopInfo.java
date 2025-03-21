package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_carouse_top_info")
@ApiModel("置顶轮播图信息")
public class CarouselTopInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("图片链接")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty("文章地址")
    private String path;

    @ApiModelProperty("描述/图片水印")
    private String description;

    @ApiModelProperty("图片标题")
    private String title;
}
