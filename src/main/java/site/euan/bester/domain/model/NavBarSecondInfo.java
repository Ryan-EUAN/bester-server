package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 二级导航栏
 * @author EUAN
 */
@TableName("tb_nav_bar_second_info")
@Data
public class NavBarSecondInfo {
    private Long id;
    private Long navId;
    private String navName;
    private String navPath;
    private Integer navSort;
    private Boolean navDisable;
}
