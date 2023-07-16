package com.project.ably.service.wish;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.entity.FolderEntity;
import com.project.ably.model.entity.WishEntity;
import com.project.ably.model.vo.Wish;
import com.project.ably.repository.WishRepository;
import com.project.ably.service.folder.SelectFolderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CreateWishService implements WishModifiable {
	private final WishRepository wishRepository;
	private final SelectFolderService selectFolderService;
	private final SelectWishService selectWishService;

	@Override
	public void modifyWish(Wish wish) {
		FolderEntity folderEntity = FolderEntity.builder()
				.folderNo(wish.getFolderNo())
				.build();

		WishEntity wishEntity = WishEntity.builder()
				.productNo(wish.getProductNo())
				.folderNo(wish.getFolderNo())
				.registrationDate(LocalDateTime.now().toString())
				.folderEntity(folderEntity)
				.build();

		wishRepository.save(wishEntity);
	}

	@Override
	public Wish setDefaultFolder(Wish wish){
		if(wish.isNotEmptyFolder()){
			return wish;
		}

		int defaultFolderNo = selectFolderService.selectDefaultFolderNo(wish.getFolderNo(), wish.getEmail());
		if(0 == defaultFolderNo){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_DEFAULT_FOLDER);
		}

		return wish.toBuilder()
				.folderNo(defaultFolderNo)
				.build();
	}

	@Override
	public void validation(Wish wish) {
		if(ObjectUtils.isEmpty(selectFolderService.checkExistFolderByNo(wish.getFolderNo()))){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_FOLDER);
		}

		if(!ObjectUtils.isEmpty(selectWishService.checkExistProduct(wish.getEmail(), wish.getProductNo()))){
			throw new BusinessErrorCodeException(ErrorCode.ALREADY_EXIST_WISH);
		}

		//TODO:: 상품 존재 여부 확인
	}
}
