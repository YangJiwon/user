package com.project.ably.controller.member;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.ably.model.request.MemberRequest;
import com.project.ably.model.response.LoginResponse;
import com.project.ably.model.response.MemberResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

public interface MemberControllerApi {
	@Operation(
			summary = "회원가입",
			description = "회원가입",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema()))
			}
	)
	@PostMapping(value = "/member/sign-up")
	ResponseEntity<?> signUp(@Valid @RequestBody MemberRequest memberRequest);

	@Operation(
			summary = "로그인",
			description = "로그인",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginResponse.class)))
			}
	)
	@PostMapping(value = "/member/login")
	ResponseEntity<?> login(@Valid @RequestBody MemberRequest memberRequest);

	@Operation(
			summary = "회원정보 조회",
			description = "회원정보 조회",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MemberResponse.class)))
			}
	)
	@GetMapping(value = "/member")
	ResponseEntity<?> getMember(@ApiIgnore @AuthenticationPrincipal UserDetails userDetails);
}
