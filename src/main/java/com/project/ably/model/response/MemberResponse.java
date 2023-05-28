package com.project.ably.model.response;

import com.project.ably.model.vo.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Schema(description = "멤버 조회 응답")
@EqualsAndHashCode
public class MemberResponse {
    @Schema(description = "이메일", example = "rennes91@daum.net")
    private final String email;

    @Schema(description = "회원 가입일", example = "2023-05-29 00:00:00")
    private final String registrationDate;

    public MemberResponse(Member member){
        this.email = member.getEmail();
        this.registrationDate = member.getRegistrationDate();
    }
}
