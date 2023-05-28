package com.project.ably.mapper.folder;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.vo.Folder;

@Mapper
public interface FolderCommandMapper {
	int insertFolder(Folder folder);

	int insertMemberFolder(Folder folder);

	int deleteFolder(int folderNo);

	int deleteMemberFolder(int folderNo);
}
