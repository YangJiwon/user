package com.project.ably.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "멤버 요청 모델")
public class MemberRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    @Schema(description = "이메일", example = "rennes91@daum.net")
    private final String email;

    @NotBlank
    @Schema(description = "비밀번호", example = "qwer1234")
    private final String password;

    @Schema(description = "암호화 비밀번호", hidden = true)
    private final String encryptPassword;

    public MemberRequest(String email, String password){
        this.email = email;
        this.password = password;
        this.encryptPassword = new BCryptPasswordEncoder().encode(password);
    }

    public boolean isMatchPassword(String password){
        return new BCryptPasswordEncoder().matches(this.password, password);
    }
}
