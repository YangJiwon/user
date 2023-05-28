package com.project.ably.service.folder;

import com.project.ably.model.vo.Folder;

public interface FolderManageable {
	default void managingFolder(Folder folder){
		validation(folder);
		updateFolder(folder);
	}

	void updateFolder(Folder folder);

	void validation(Folder folder);
}
