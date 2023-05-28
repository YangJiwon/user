package com.project.ably.mapper.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.response.ProductResponse;

@Mapper
public interface ProductQueryMapper {
	List<ProductResponse> selectProductList();
}
