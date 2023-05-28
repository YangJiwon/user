package com.project.ably.service.folder;

import com.project.ably.common.CommonUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FolderServiceEnum {
	DELETE(DeleteFolderService.class.getSimpleName()),
	INSERT(CreateFolderService.class.getSimpleName());

	private final String name;

	public String getName() {
		return CommonUtil.firstWordToLowerCase(name);
	}
}
