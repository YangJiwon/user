package com.project.ably.mapper.member;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.request.MemberRequest;

@Mapper
public interface MemberCommandMapper {
	int insertMember(MemberRequest signUpRequest);
}
