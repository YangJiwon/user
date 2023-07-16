package com.project.ably.service.wish;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.vo.Wish;
import com.project.ably.repository.WishRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DeleteWishService implements WishModifiable {
	private final WishRepository wishRepository;
	private final SelectWishService selectWishService;

	@Override
	public void modifyWish(Wish wish) {
		List<Integer> wishNoList = wish.getWishNoList();
		wishRepository.deleteAllById(wishNoList);
	}

	@Override
	public void validation(Wish wish) {
		List<Integer> wishNoList = wish.getWishNoList();
		List<Integer> existNoList = selectWishService.checkExistWishNo(wishNoList);
		if(existNoList.size() != wishNoList.size()){
			throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_WISH_NO);
		}
	}
}
