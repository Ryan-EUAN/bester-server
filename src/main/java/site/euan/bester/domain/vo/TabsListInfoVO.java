package site.euan.bester.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Tabs排行榜信息返回类
 * @author EUAN
 */
@Builder
@Data
public class TabsListInfoVO implements Serializable {
    private Long id;
    private String label;
    private String end;
}
