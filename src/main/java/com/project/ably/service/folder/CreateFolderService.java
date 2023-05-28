package com.project.ably.service.folder;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.folder.FolderCommandMapper;
import com.project.ably.model.vo.Folder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CreateFolderService implements FolderManageable{
	private final SelectFolderService selectFolderService;
	private final FolderCommandMapper commandMapper;

	@Override
	public void updateFolder(Folder folder) {
		if(1 != commandMapper.insertFolder(folder)){
			throw new BusinessErrorCodeException(ErrorCode.INSERT_FOLDER);
		}

		if(1 != commandMapper.insertMemberFolder(folder)){
			throw new BusinessErrorCodeException(ErrorCode.INSERT_MEMBER_FOLDER);
		}
	}

	@Override
	public void validation(Folder folder) {
		if(!ObjectUtils.isEmpty(selectFolderService.checkExistFolderByName(folder.getEmail(), folder.getFolderName()))){
			throw new BusinessErrorCodeException(ErrorCode.ALREADY_EXIST_FOLDER);
		}
	}
}
