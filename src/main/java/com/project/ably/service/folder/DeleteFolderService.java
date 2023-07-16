package com.project.ably.service.folder;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.vo.Folder;
import com.project.ably.model.vo.Wish;
import com.project.ably.repository.FolderRepository;
import com.project.ably.service.wish.WishService;
import com.project.ably.service.wish.WishServiceEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DeleteFolderService implements FolderManageable{
	private final SelectFolderService selectFolderService;
	private final WishService wishService;
	private final FolderRepository folderRepository;

	@Override
	public void updateFolder(Folder folder) {
		int folderNo = folder.getFolderNo();
		folderRepository.deleteById(folderNo);

		List<Integer> wishNoList = wishService.selectWishNoList(folderNo);
		if(CollectionUtils.isEmpty(wishNoList)){
			return;
		}

		// 찜서랍 하위의 찜 삭제
		Wish wish = Wish.builder()
				.wishNoList(wishNoList)
				.email(folder.getEmail())
				.serviceName(WishServiceEnum.DELETE.getName())
				.build();

		wishService.modifyWish(wish);
	}

	@Override
	public void validation(Folder folder) {
		String email = folder.getEmail();
		int folderNo = folder.getFolderNo();

		if(ObjectUtils.isEmpty(selectFolderService.checkExistFolderByNo(folderNo))){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_FOLDER);
		}

		if(0 != selectFolderService.selectDefaultFolderNo(folderNo, email)){
			throw new BusinessErrorCodeException(ErrorCode.NOT_REMOVE_DEFAULT_FOLDER);
		}
	}
}
