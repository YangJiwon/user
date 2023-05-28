package com.project.ably.service.wish;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.wish.WishCommandMapper;
import com.project.ably.model.vo.Wish;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DeleteWishService implements WishModifiable {
	private final WishCommandMapper commandMapper;
	private final SelectWishService selectWishService;

	@Override
	public void modifyWish(Wish wish) {
		List<Integer> wishNoList = wish.getWishNoList();
		if(1 != commandMapper.deleteWish(wishNoList)){
			throw new BusinessErrorCodeException(ErrorCode.DELETE_WISH);
		}

		if(1 != commandMapper.deleteFolderWish(wishNoList)){
			throw new BusinessErrorCodeException(ErrorCode.DELETE_FOLDER_WISH);
		}
	}

	@Override
	public void validation(Wish wish) {
		List<Integer> wishNoList = wish.getWishNoList();
		List<Integer> existNoList = selectWishService.checkExistWishNo(wish.getEmail(), wishNoList);
		if(existNoList.size() != wishNoList.size()){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_WISH_NO);
		}
	}
}
