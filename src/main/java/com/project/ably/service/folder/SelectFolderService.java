package com.project.ably.service.folder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.entity.FolderEntity;
import com.project.ably.model.response.FolderResponse;
import com.project.ably.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectFolderService {
	private final FolderRepository folderRepository;

	public List<FolderResponse> selectFolderList(int pageNo, int pageSize, String email) {
		//todo:: paging
		List<FolderEntity> folderList = folderRepository.findAllByEmail(email);
		if(CollectionUtils.isEmpty(folderList)){
			throw new BusinessErrorCodeException(ErrorCode.SELECT_MEMBER_FOLDER);
		}

		return folderList.stream()
				.map(FolderResponse::new)
				.collect(Collectors.toList());
	}

	public FolderEntity checkExistFolderByName(String email, String folderName){
		return folderRepository.findByEmailAndFolderName(email, folderName);
	}

	public FolderEntity checkExistFolderByNo(int folderNo){
		Optional<FolderEntity> findFolderEntity = folderRepository.findById(folderNo);
		if(findFolderEntity.isEmpty()){
			return null;
		}

		return findFolderEntity.get();
	}

	public Integer selectDefaultFolderNo(int folderNo, String email){
		FolderEntity folderEntity = folderRepository.findByEmailAndFolderNoAndDefaultYn(email, folderNo, "Y");
		if(ObjectUtils.isEmpty(folderEntity)){
			return 0;
		}

		return folderEntity.getFolderNo();
	}
}
