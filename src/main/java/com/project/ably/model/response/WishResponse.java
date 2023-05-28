package com.project.ably.model.response;

import com.project.ably.model.vo.Wish;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Schema(description = "찜 조회 응답")
@EqualsAndHashCode
public class WishResponse {
	@Schema(description = "찜하기 번호", example = "1")
	private final int wishNo;

	@Schema(description = "찜하기 일시", example = "2023-05-31 00:00:00")
	private final String registrationDate;

	private final ProductResponse product;

	public WishResponse(Wish wish, ProductResponse product){
		this.wishNo = wish.getWishNo();
		this.registrationDate = wish.getRegistrationDate();
		this.product = product;
	}
}
