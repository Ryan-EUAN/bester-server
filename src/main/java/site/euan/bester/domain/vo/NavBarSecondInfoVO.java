package site.euan.bester.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class NavBarSecondInfoVO implements Serializable {
    private Long key;
    private String label;
    private String path;
}
