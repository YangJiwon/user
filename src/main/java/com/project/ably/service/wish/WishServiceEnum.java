package com.project.ably.service.wish;

import com.project.ably.common.CommonUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WishServiceEnum {
	DELETE(DeleteWishService.class.getSimpleName()),
	INSERT(CreateWishService.class.getSimpleName());

	private final String name;

	public String getName() {
		return CommonUtil.firstWordToLowerCase(name);
	}
}
