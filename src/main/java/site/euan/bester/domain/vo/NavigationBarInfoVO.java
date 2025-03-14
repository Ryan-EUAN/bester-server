package site.euan.bester.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 页面导航栏信息返回类
 */
@Builder
@Data
public class NavigationBarInfoVO implements Serializable {
    private Long key;
    private String label;
    private String icon;
    private String path;
    private List<NavBarSecondInfoVO> children;
}
