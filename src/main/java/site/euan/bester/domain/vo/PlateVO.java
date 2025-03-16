package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@ApiModel(value = "模块包装信息")
public class PlateVO {
    @ApiModelProperty(value = "模块标题")
    private String title;
    @ApiModelProperty(value = "模块内容")
    private List<PlateInfoVO> plateInfos;
}
