package com.project.ably.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "로그인 응답")
public class LoginResponse {
	@Schema(description = "jwt 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZW5uZXM5MUBkYXVtLm5ldCIsImlhdCI6MTY4NTE1NTMxMCwiZXhwIjoxNjg1MTU3MTE")
	private String token;
}
