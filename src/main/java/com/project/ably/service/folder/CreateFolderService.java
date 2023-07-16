package com.project.ably.service.folder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.entity.FolderEntity;
import com.project.ably.model.vo.Folder;
import com.project.ably.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CreateFolderService implements FolderManageable{
	private final SelectFolderService selectFolderService;
	private final FolderRepository folderRepository;

	@Override
	public void updateFolder(Folder folder) {
		FolderEntity folderEntity = FolderEntity.builder()
				.email(folder.getEmail())
				.folderNo(folder.getFolderNo())
				.folderName(folder.getFolderName())
				.defaultYn(folder.getDefaultYn())
				.registrationDate(LocalDateTime.now().toString())
				.build();
		folderRepository.save(folderEntity);
	}

	@Override
	public void validation(Folder folder) {
		if(!ObjectUtils.isEmpty(selectFolderService.checkExistFolderByName(folder.getEmail(), folder.getFolderName()))){
			throw new BusinessErrorCodeException(ErrorCode.ALREADY_EXIST_FOLDER);
		}
	}
}
