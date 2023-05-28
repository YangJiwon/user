package com.project.ably.controller.folder;

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

import com.project.ably.model.request.CreateFolderRequest;
import com.project.ably.model.response.FolderResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Validated
public interface FolderControllerApi {
	@Operation(
			summary = "찜서랍 생성",
			description = "찜서랍 생성",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema()))
			}
	)
	@PostMapping(value = "/folder")
	ResponseEntity<?> createFolder(@Valid @RequestBody CreateFolderRequest createFolderRequest,
								   @ApiIgnore @AuthenticationPrincipal UserDetails userDetails);

	@Operation(
			summary = "찜서랍 삭제",
			description = "찜서랍 삭제",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema()))
			}
	)
	@DeleteMapping(value = "/folder")
	ResponseEntity<?> deleteFolder(@Parameter(name = "folderNo", description = "삭제할 찜서랍 번호")
								   @Valid @Min(1) @RequestParam int folderNo,
								   @ApiIgnore @AuthenticationPrincipal UserDetails userDetails);

	@Operation(
			summary = "찜서랍 조회",
			description = "찜서랍 조회",
			responses = {
					@ApiResponse(description = "Success",
							responseCode = "200",
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FolderResponse.class)))
			}
	)
	@GetMapping(value = "/folder")
	ResponseEntity<?> selectFolder(@Parameter(name = "pageNo", description = "페이지 번호")
								   @Valid @Min(1) @RequestParam int pageNo,
								   @Parameter(name = "pageSize", description = "페이지 사이즈")
								   @Valid @Min(1) @RequestParam int pageSize,
								   @ApiIgnore @AuthenticationPrincipal UserDetails userDetails);
}
