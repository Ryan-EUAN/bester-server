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
@TableName("tb_plate_info")
@ApiModel("模块内部信息")
public class PlateInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("模块外部ID")
    private Long plateId;
    @ApiModelProperty("模块内部昵称")
    private String plateName;
    @ApiModelProperty("模块内部图标")
    private String plateIcon;
    @ApiModelProperty("模块内部跳转链接")
    private String platePath;
}
