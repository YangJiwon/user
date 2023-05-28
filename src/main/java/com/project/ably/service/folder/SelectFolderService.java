package com.project.ably.service.folder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.folder.FolderQueryMapper;
import com.project.ably.model.response.FolderResponse;
import com.project.ably.model.vo.Folder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelectFolderService {
	private final FolderQueryMapper queryMapper;

	public List<FolderResponse> selectFolderList(int pageNo, int pageSize, String email) {
		List<Folder> folderList = queryMapper.selectFolderList(pageNo, pageSize, email);
		if(CollectionUtils.isEmpty(folderList)){
			throw new BusinessErrorCodeException(ErrorCode.SELECT_MEMBER_FOLDER);
		}

		return folderList.stream()
				.map(FolderResponse::new)
				.collect(Collectors.toList());
	}

	public Integer checkExistFolderByName(String email, String folderName){
		return queryMapper.checkExistFolderByName(email, folderName);
	}

	public Integer checkExistFolderByNo(String email, int folderNo){
		return queryMapper.checkExistFolderByNo(email, folderNo);
	}

	public Integer checkDefaultFolder(String email, int folderNo){
		return queryMapper.checkDefaultFolder(email, folderNo);
	}

	public Integer selectDefaultFolderNo(String email){
		return queryMapper.selectDefaultFolderNo(email);
	}
}
