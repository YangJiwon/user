package com.project.ably.controller.member;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.project.ably.common.security.JwtTokenProvider;
import com.project.ably.model.request.MemberRequest;
import com.project.ably.model.response.MemberResponse;
import com.project.ably.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberControllerApi {
	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public ResponseEntity<?> signUp(MemberRequest memberRequest){
		memberService.signUp(memberRequest);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> login(MemberRequest memberRequest){
		MemberResponse memberResponse = memberService.login(memberRequest);
		return ResponseEntity.ok(jwtTokenProvider.createToken(memberResponse.getEmail()));
	}

	@Override
	public ResponseEntity<?> getMember(UserDetails userDetails) {
		return ResponseEntity.ok(memberService.findMemberByEmail(userDetails.getUsername()));
	}
}
