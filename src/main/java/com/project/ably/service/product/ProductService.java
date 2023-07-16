package com.project.ably.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.project.ably.model.entity.ProductEntity;
import com.project.ably.model.response.ProductResponse;
import com.project.ably.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private List<ProductResponse> allProductList = new ArrayList<>();

	@PostConstruct
	protected void init() {
		List<ProductEntity> productList = productRepository.findAll();
		allProductList = productList.stream()
				.map(ProductResponse::new)
				.collect(Collectors.toList());
	}

	public List<ProductResponse> getAllProductList(){
		return allProductList;
	}
}
