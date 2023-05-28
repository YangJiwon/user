package com.project.ably.controller.like;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.project.ably.model.request.WishRequest;
import com.project.ably.model.vo.Wish;
import com.project.ably.service.wish.WishService;
import com.project.ably.service.wish.WishServiceEnum;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WishController implements WishControllerApi {
	private final WishService wishService;

	@Override
	public ResponseEntity<?> wish(WishRequest wishRequest, UserDetails userDetails) {
		Wish wish = Wish.builder()
				.folderNo(wishRequest.getFolderNo())
				.productNo(wishRequest.getProductNo())
				.email(userDetails.getUsername())
				.serviceName(WishServiceEnum.INSERT.getName())
				.build();

		wishService.modifyWish(wish);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> unWish(int wishNo, UserDetails userDetails) {
		Wish wish = Wish.builder()
				.wishNoList(List.of(wishNo))
				.email(userDetails.getUsername())
				.serviceName(WishServiceEnum.DELETE.getName())
				.build();

		wishService.modifyWish(wish);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> selectWish(int pageNo, int pageSize, UserDetails userDetails) {
		return ResponseEntity.ok(wishService.selectWishList(pageNo, pageSize, userDetails.getUsername()));
	}
}
