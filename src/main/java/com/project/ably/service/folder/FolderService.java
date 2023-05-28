package com.project.ably.service.folder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ably.model.response.FolderResponse;
import com.project.ably.model.vo.Folder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderService {
	private final Map<String, FolderManageable> folderServiceMap;
	private final SelectFolderService selectFolderService;

	@Transactional
	public void managingFolder(Folder folder){
		FolderManageable folderManageable = folderServiceMap.get(folder.getServiceName());
		folderManageable.managingFolder(folder);
	}

	public List<FolderResponse> selectFolderList(int pageNo, int pageSize, String email){
		return selectFolderService.selectFolderList(pageNo, pageSize, email);
	}
}
