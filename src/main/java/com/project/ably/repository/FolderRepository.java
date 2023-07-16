package com.project.ably.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ably.model.entity.FolderEntity;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {
	FolderEntity findByEmailAndFolderNoAndDefaultYn(String email, int folderNo, String defaultYn); //todo:: 이름 이거밖에 안되나

	FolderEntity findByEmailAndFolderName(String email, String folderName);

	List<FolderEntity> findAllByEmail(String email);
}
