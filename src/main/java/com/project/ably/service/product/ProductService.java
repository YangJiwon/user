package com.project.ably.service.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.project.ably.mapper.product.ProductQueryMapper;
import com.project.ably.model.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductQueryMapper queryMapper;
	private List<ProductResponse> allProductList = new ArrayList<>();

	@PostConstruct
	protected void init() {
		allProductList = queryMapper.selectProductList();
	}

	public List<ProductResponse> getAllProductList(){
		return allProductList;
	}
}
