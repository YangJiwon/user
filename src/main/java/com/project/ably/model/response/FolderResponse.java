package com.project.ably.model.response;

import com.project.ably.model.entity.FolderEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "찜서랍 조회 응답")
public class FolderResponse {
	@Schema(description = "찜서랍 번호", example = "1")
	private final int folderNo;

	@Schema(description = "찜서랍명", example = "기본서랍")
	private final String folderName;

	@Schema(description = "찜서랍 등록일", example = "2023-05-29 00:00:00")
	private final String registrationDate;

	public FolderResponse(FolderEntity folderEntity){
		this.folderNo = folderEntity.getFolderNo();
		this.folderName = folderEntity.getFolderName();
		this.registrationDate = folderEntity.getRegistrationDate();
	}
}
