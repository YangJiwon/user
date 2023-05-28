package com.project.ably.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ably.common.exception.ApiErrorResponse;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/security")
public class SecurityController {

	@RequestMapping("/accessDenied")
	public ResponseEntity<?> denied() {
		return ApiErrorResponse.toResponse(HttpStatus.FORBIDDEN, "권한이 없습니다");
	}

	@RequestMapping("/notAuthentication")
	public ResponseEntity<?> notAuthentication(HttpServletRequest request) {
		return ApiErrorResponse.toResponse(HttpStatus.UNAUTHORIZED, "로그인이 되지 않았습니다.");
	}
}
