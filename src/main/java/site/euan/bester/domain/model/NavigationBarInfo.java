package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导航栏信息Model
 *
 * @author EUAN
 * @version 0.0.1
 */
@TableName("tb_navigation_bar_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavigationBarInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String navName;
    private String navPath;
    private Integer navSort;
    private Boolean navDisable;
    private String navIcon;
}
