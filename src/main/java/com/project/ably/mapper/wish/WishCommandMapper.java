package com.project.ably.mapper.wish;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.vo.Wish;

@Mapper
public interface WishCommandMapper {
	int insertWish(Wish wish);

	int insertFolderWish(Wish wish);

	int deleteWish(List<Integer> wishNoList);

	int deleteFolderWish(List<Integer> wishNoList);
}
