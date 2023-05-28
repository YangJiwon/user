package com.project.ably.model.request;

import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "찜하기 요청 모델")
public class WishRequest {
    @Schema(description = "찜서랍 번호(설정 안할시 기본서랍에 들어감)", example = "1")
    private int folderNo;

    @Min(1)
    @Schema(description = "상품 번호", example = "1")
    private int productNo;

    public WishRequest() {}
}
