package com.project.ably.service.member;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ably.common.CommonConstants;
import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.entity.MemberEntity;
import com.project.ably.model.request.MemberRequest;
import com.project.ably.model.response.MemberResponse;
import com.project.ably.model.vo.Folder;
import com.project.ably.repository.MemberRepository;
import com.project.ably.service.folder.FolderService;
import com.project.ably.service.folder.FolderServiceEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final FolderService folderService;
	private final MemberRepository memberRepository;

	@Transactional
	public void signUp(MemberRequest memberRequest){
		Optional<MemberEntity> findMember = memberRepository.findById(memberRequest.getEmail());
		if(findMember.isPresent()){
			throw new BusinessErrorCodeException(ErrorCode.DUPLICATE_EMAIL);
		}

		MemberEntity memberEntity = new MemberEntity(memberRequest.getEmail(), memberRequest.getEncryptPassword(), LocalDateTime.now().toString());
		memberRepository.save(memberEntity);

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
		MemberEntity memberEntity = findById(memberRequest.getEmail());
		if(!memberRequest.isMatchPassword(memberEntity.getPassword())){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_MEMBER);
		}

		return new MemberResponse(memberEntity);
	}

	public MemberEntity findById(String email){
		Optional<MemberEntity> findMember = memberRepository.findById(email);
		if(findMember.isEmpty()){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_MEMBER);
		}

		return findMember.get();
	}

	public MemberResponse findMemberByEmail(String email){
		return new MemberResponse(findById(email));
	}
}
