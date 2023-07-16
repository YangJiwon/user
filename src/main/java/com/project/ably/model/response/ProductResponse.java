package com.project.ably.model.response;

import com.project.ably.model.entity.ProductEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "상품 조회 응답")
public class ProductResponse {
    @Schema(description = "상품 번호", example = "1")
    private int productNo;

    @Schema(description = "상품명", example = "product_1")
    private String productName;

    @Schema(description = "썸네일 이미지", example = "https://image.com/products/thumbnail/product_0.jpeg")
    private String thumbnail;

    @Schema(description = "상품 가격", example = "1000")
    private double price;

    public ProductResponse(ProductEntity productEntity){
        this.productNo = productEntity.getProductNo();
        this.productName = productEntity.getProductName();
        this.thumbnail = productEntity.getThumbnail();
        this.price = productEntity.getPrice();
    }
}
