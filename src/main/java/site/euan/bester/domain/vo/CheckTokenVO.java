package site.euan.bester.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@ApiModel(value = "验证token是否过期的返回类")
public class CheckTokenVO implements Serializable {
    @ApiModelProperty(value = "token是否过期")
    private Boolean valid;
    @ApiModelProperty(value = "token过期时间")
    private Long expireTime;
}
