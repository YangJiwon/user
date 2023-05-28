package com.project.ably.service.wish;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.wish.WishCommandMapper;
import com.project.ably.model.vo.Wish;
import com.project.ably.service.folder.SelectFolderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CreateWishService implements WishModifiable {
	private final WishCommandMapper commandMapper;
	private final SelectFolderService selectFolderService;
	private final SelectWishService selectWishService;

	@Override
	public void modifyWish(Wish wish) {
		if(1 != commandMapper.insertWish(wish)){
			throw new BusinessErrorCodeException(ErrorCode.INSERT_WISH);
		}

		if(1 != commandMapper.insertFolderWish(wish)){
			throw new BusinessErrorCodeException(ErrorCode.INSERT_FOLDER_WISH);
		}
	}

	@Override
	public Wish setDefaultFolder(Wish wish){
		if(wish.isNotEmptyFolder()){
			return wish;
		}

		int defaultFolderNo = selectFolderService.selectDefaultFolderNo(wish.getEmail());
		if(0 == defaultFolderNo){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_DEFAULT_FOLDER);
		}

		return wish.toBuilder()
				.folderNo(defaultFolderNo)
				.build();
	}

	@Override
	public void validation(Wish wish) {
		if(ObjectUtils.isEmpty(selectFolderService.checkExistFolderByNo(wish.getEmail(), wish.getFolderNo()))){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_FOLDER);
		}

		if(!ObjectUtils.isEmpty(selectWishService.checkExistProduct(wish.getEmail(), wish.getProductNo()))){
			throw new BusinessErrorCodeException(ErrorCode.ALREADY_EXIST_WISH);
		}
	}
}
