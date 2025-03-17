package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tb_plate")
@ApiModel("模块外部信息")
public class Plate {
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("模块外部标题")
    private String title;
    @ApiModelProperty("是否启用")
    private Boolean enable;
}