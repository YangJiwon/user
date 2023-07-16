package com.project.ably.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.project.ably.model.entity.WishEntity;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Integer> {
	List<WishEntity> findAllByFolderNo(int folderNo);

	@Query(
			value = "SELECT wish.productNo " +
					"FROM FolderEntity folder JOIN WishEntity wish ON folder.folderNo = wish.folderNo " +
					"WHERE folder.email = :email " +
					"AND wish.productNo = :productNo"
	)
	Integer findByEmailAndProductNo(@Param(value = "email")String email, @Param(value = "productNo")int productNo);
}
