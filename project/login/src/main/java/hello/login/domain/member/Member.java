package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id;        // DB에 저장되어 관리되는 ID

    @NotBlank
    private String loginId; // 로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
