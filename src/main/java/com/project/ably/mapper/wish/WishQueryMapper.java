package com.project.ably.mapper.wish;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.vo.Wish;

@Mapper
public interface WishQueryMapper {
	Integer checkExistProduct(String email, int productNo);

	List<Integer> selectWishNoList(int folderNo);

	List<Integer> checkExistWishNo(String email, List<Integer> wishNoList);

	List<Wish> selectWishList(int pageNo, int pageSize, String email);
}
