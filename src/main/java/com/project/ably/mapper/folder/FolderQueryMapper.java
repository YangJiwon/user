package com.project.ably.mapper.folder;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.ably.model.vo.Folder;

@Mapper
public interface FolderQueryMapper {
	Integer checkExistFolderByName(String email, String folderName);

	Integer checkExistFolderByNo(String email, int folderNo);

	Integer checkDefaultFolder(String email, int folderNo);

	List<Folder> selectFolderList(int pageNo, int pageSize, String email);

	Integer selectDefaultFolderNo(String email);
}
