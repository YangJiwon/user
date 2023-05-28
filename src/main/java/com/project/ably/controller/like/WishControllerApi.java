package com.project.ably.controller.like;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ably.model.request.WishRequest;
import com.project.ably.model.response.WishResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Validated
public interface WishControllerApi {
	@Operation(
			summary = "찜하기",
			description = "찜하기",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema()))
			}
	)
	@PostMapping(value = "/wish")
	ResponseEntity<?> wish(@Valid @RequestBody WishRequest wishRequest,
						   @ApiIgnore @AuthenticationPrincipal UserDetails userDetails);

	@Operation(
			summary = "찜해제",
			description = "찜해제",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema()))
			}
	)
	@DeleteMapping(value = "/wish")
	ResponseEntity<?> unWish(@Parameter(name = "wishNo", description = "찜해제 할 번호")
							 @Valid @Min(1) @RequestParam int wishNo,
							 @ApiIgnore @AuthenticationPrincipal UserDetails userDetails);

	@Operation(
			summary = "찜서랍 안의 찜 목록 조회",
			description = "찜서랍 안의 찜 목록 조회",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WishResponse.class)))
			}
	)
	@GetMapping(value = "/wish")
	ResponseEntity<?> selectWish(@Parameter(name = "pageNo", description = "페이지 번호")
								 @Valid @Min(1) @RequestParam int pageNo,
								 @Parameter(name = "pageSize", description = "페이지 사이즈")
								 @Valid @Min(1) @RequestParam int pageSize,
								 @ApiIgnore @AuthenticationPrincipal UserDetails userDetails);
}
