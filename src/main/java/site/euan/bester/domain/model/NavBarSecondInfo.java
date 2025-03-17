package site.euan.bester.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二级导航栏
 * @author EUAN
 */
@TableName("tb_nav_bar_second_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavBarSecondInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long navId;
    private String navName;
    private String navPath;
    private Integer navSort;
    private Boolean navDisable;
}
