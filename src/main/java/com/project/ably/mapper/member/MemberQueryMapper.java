package com.project.ably.mapper.member;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.vo.Member;

@Mapper
public interface MemberQueryMapper {
	Member findMemberByEmail(String email);
}
