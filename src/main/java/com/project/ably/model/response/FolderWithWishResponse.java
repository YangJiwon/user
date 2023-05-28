package com.project.ably.model.response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.project.ably.model.vo.Wish;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Schema(description = "찜 조회 응답")
@EqualsAndHashCode
public class FolderWithWishResponse {
	@Schema(description = "찜서랍 번호", example = "1")
	private final int folderNo;

	@Schema(description = "찜서랍명", example = "기본서랍")
	private final String folderName;

	private final List<WishResponse> wishResponseList;

	public FolderWithWishResponse(int folderNo, String folderName, List<Wish> wishList, Map<Integer, ProductResponse> productMap){
		this.folderNo = folderNo;
		this.folderName = folderName;
		this.wishResponseList = wishList.stream()
				.map(v -> new WishResponse(v, productMap.get(v.getProductNo())))
				.collect(Collectors.toList());
	}
}
