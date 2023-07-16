package com.project.ably.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="FOLDER")
public class FolderEntity {
	@Id
	@Column(name = "FOLDER_NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int folderNo;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FOLDER_NAME")
	private String folderName;

	@Column(name = "DEFAULT_YN")
	private String defaultYn;

	@Column(name = "REGISTRATION_DATE")
	private String registrationDate;

	@OneToMany(mappedBy = "folderEntity")
	private List<WishEntity> wishEntityList = new ArrayList<>();
}
