package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("tb_plate")
@ApiModel("模块外部信息")
public class Plate {
    private Long id;
    @ApiModelProperty("模块外部标题")
    private String title;
    @ApiModelProperty("是否启用")
    private Boolean enable;
}