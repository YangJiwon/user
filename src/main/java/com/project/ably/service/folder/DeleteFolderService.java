package com.project.ably.service.folder;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.folder.FolderCommandMapper;
import com.project.ably.model.vo.Folder;
import com.project.ably.model.vo.Wish;
import com.project.ably.service.wish.WishService;
import com.project.ably.service.wish.WishServiceEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DeleteFolderService implements FolderManageable{
	private final SelectFolderService selectFolderService;
	private final FolderCommandMapper commandMapper;
	private final WishService wishService;

	@Override
	public void updateFolder(Folder folder) {
		int folderNo = folder.getFolderNo();
		if(1 != commandMapper.deleteFolder(folderNo)){
			throw new BusinessErrorCodeException(ErrorCode.DELETE_FOLDER);
		}

		if(1 != commandMapper.deleteMemberFolder(folderNo)){
			throw new BusinessErrorCodeException(ErrorCode.DELETE_MEMBER_FOLDER);
		}

		// 찜서랍 하위의 찜 삭제
		Wish wish = Wish.builder()
				.wishNoList(wishService.selectWishNoList(folderNo))
				.email(folder.getEmail())
				.serviceName(WishServiceEnum.DELETE.getName())
				.build();

		wishService.modifyWish(wish);
	}

	@Override
	public void validation(Folder folder) {
		String email = folder.getEmail();
		int folderNo = folder.getFolderNo();

		if(ObjectUtils.isEmpty(selectFolderService.checkExistFolderByNo(email, folderNo))){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_FOLDER);
		}

		if(!ObjectUtils.isEmpty(selectFolderService.checkDefaultFolder(email, folderNo))){
			throw new BusinessErrorCodeException(ErrorCode.NOT_REMOVE_DEFAULT_FOLDER);
		}
	}
}
