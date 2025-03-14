package site.euan.bester.domain.dto;

import lombok.Data;

@Data
public class UserEmailLoginDTO {
    private String email;
    private String code;
}
