package com.project.ably.controller.folder;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.project.ably.model.request.CreateFolderRequest;
import com.project.ably.model.response.FolderResponse;
import com.project.ably.model.vo.Folder;
import com.project.ably.service.folder.FolderService;
import com.project.ably.service.folder.FolderServiceEnum;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FolderController implements FolderControllerApi {
	private final FolderService folderService;

	@Override
	public ResponseEntity<?> createFolder(CreateFolderRequest createFolderRequest, UserDetails userDetails) {
		Folder folder = Folder.builder()
				.folderName(createFolderRequest.getFolderName())
				.email(userDetails.getUsername())
				.serviceName(FolderServiceEnum.INSERT.getName())
				.build();

		folderService.managingFolder(folder);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> deleteFolder(int folderNo, UserDetails userDetails) {
		Folder folder = Folder.builder()
				.folderNo(folderNo)
				.email(userDetails.getUsername())
				.serviceName(FolderServiceEnum.DELETE.getName())
				.build();

		folderService.managingFolder(folder);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> selectFolder(int pageNo, int pageSize, UserDetails userDetails) {
		List<FolderResponse> folderList = folderService.selectFolderList(pageNo, pageSize, userDetails.getUsername());
		return ResponseEntity.ok(folderList);
	}
}
