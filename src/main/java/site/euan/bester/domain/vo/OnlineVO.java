package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "获取在线信息返回类")
public class OnlineVO {
    @ApiModelProperty(value = "名字")
    private String label;
    @ApiModelProperty(value = "数量")
    private Integer value;
}
