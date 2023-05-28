package com.project.ably.service.wish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.wish.WishQueryMapper;
import com.project.ably.model.response.FolderWithWishResponse;
import com.project.ably.model.response.ProductResponse;
import com.project.ably.model.vo.Wish;
import com.project.ably.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class SelectWishService {
	private final WishQueryMapper queryMapper;
	private final ProductService productService;

	public Integer checkExistProduct(String email, int productNo){
		return queryMapper.checkExistProduct(email, productNo);
	}

	public List<Integer> selectWishNoList(int folderNo){
		return queryMapper.selectWishNoList(folderNo);
	}

	public List<Integer> checkExistWishNo(String email, List<Integer> wishNoList){
		return queryMapper.checkExistWishNo(email, wishNoList);
	}

	public List<FolderWithWishResponse> selectWishList(int pageNo, int pageSize, String email){
		List<Wish> wishList = queryMapper.selectWishList(pageNo, pageSize, email);
		if(CollectionUtils.isEmpty(wishList)){
			throw new BusinessErrorCodeException(ErrorCode.SELECT_WISH);
		}

		Map<Integer, ProductResponse> productMap = getProductMap(wishList);
		Map<Integer, List<Wish>> wishMap = wishList.stream()
				.collect(Collectors.groupingBy(Wish::getFolderNo));

		return wishMap.keySet()
				.stream()
				.map(v -> {
					String folderName = wishMap.get(v).get(0).getFolderName();
					return new FolderWithWishResponse(v, folderName, wishMap.get(v), productMap);
				})
				.collect(Collectors.toList());
	}

	private Map<Integer, ProductResponse> getProductMap(List<Wish> wishList){
		List<Integer> productNoList = wishList.stream()
				.map(Wish::getProductNo)
				.collect(Collectors.toList());

		return productService.getAllProductList().stream()
				.filter(v -> productNoList.contains(v.getProductNo()))
				.collect(Collectors.toMap(ProductResponse::getProductNo, v -> v));
	}
}
