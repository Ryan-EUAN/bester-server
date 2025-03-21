package site.euan.bester.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 帖子排行榜VO
 */
@Data
@Builder
public class TabsPostListVO implements Serializable {
    private String id;
    private String label;
    private LocalDateTime end;
}
