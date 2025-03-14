package site.euan.bester.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author EUAN
 * 博客发表DTO
 */
@Data
public class SendBlogInfoDTO {
    private String connect;
    private List<String> urls;
}
