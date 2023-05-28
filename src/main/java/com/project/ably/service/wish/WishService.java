package com.project.ably.service.wish;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ably.model.response.FolderWithWishResponse;
import com.project.ably.model.vo.Wish;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishService {
	private final Map<String, WishModifiable> wishServiceMap;
	private final SelectWishService selectWishService;

	@Transactional
	public void modifyWish(Wish wish){
		WishModifiable wishModifiable = wishServiceMap.get(wish.getServiceName());
		wishModifiable.saveWish(wish);
	}

	public List<Integer> selectWishNoList(int folderNo){
		return selectWishService.selectWishNoList(folderNo);
	}

	public List<FolderWithWishResponse> selectWishList(int pageNo, int pageSize, String email){
		return selectWishService.selectWishList(pageNo, pageSize, email);
	}
}
