package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 导航栏信息Model
 *
 * @author EUAN
 * @version 0.0.1
 */
@TableName("tb_navigation_bar_info")
@Data
public class NavigationBarInfo {
    private Long id;
    private String navName;
    private String navPath;
    private Integer navSort;
    private Boolean navDisable;
    private String navIcon;
}
