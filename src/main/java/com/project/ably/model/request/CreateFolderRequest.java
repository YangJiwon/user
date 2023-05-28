package com.project.ably.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "찜서랍 생성 요청 모델")
public class CreateFolderRequest {
    @NotBlank
    @Size(max = 20)
    @Schema(description = "찜서랍명", example = "기본서랍")
    private String folderName;
}
