package com.project.ably.service.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.CommonConstants;
import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.member.MemberCommandMapper;
import com.project.ably.mapper.member.MemberQueryMapper;
import com.project.ably.model.request.MemberRequest;
import com.project.ably.model.response.MemberResponse;
import com.project.ably.model.vo.Folder;
import com.project.ably.model.vo.Member;
import com.project.ably.service.folder.FolderService;
import com.project.ably.service.folder.FolderServiceEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberQueryMapper queryMapper;
	private final MemberCommandMapper commandMapper;
	private final FolderService folderService;

	@Transactional
	public void signUp(MemberRequest memberRequest){
		Member member = queryMapper.findMemberByEmail(memberRequest.getEmail());
		if(!ObjectUtils.isEmpty(member) && member.isExistMember()){
			throw new BusinessErrorCodeException(ErrorCode.DUPLICATE_EMAIL);
		}

		if(1 != commandMapper.insertMember(memberRequest)){
			throw new BusinessErrorCodeException(ErrorCode.INSERT_MEMBER);
		}

		// 회원가입시 기본서랍 생성
		Folder folder = Folder.builder()
				.folderName(CommonConstants.DEFAULT_FOLDER_NAME)
				.email(memberRequest.getEmail())
				.defaultYn("Y")
				.serviceName(FolderServiceEnum.INSERT.getName())
				.build();
		folderService.managingFolder(folder);
	}

	public MemberResponse login(MemberRequest memberRequest){
		Member member = queryMapper.findMemberByEmail(memberRequest.getEmail());
		if(ObjectUtils.isEmpty(member)){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_MEMBER);
		}

		if(!memberRequest.isMatchPassword(member.getPassword())){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_MEMBER);
		}

		return new MemberResponse(member);
	}

	public MemberResponse findMemberByEmail(String email){
		Member member = queryMapper.findMemberByEmail(email);
		return new MemberResponse(member);
	}
}
