package com.project.ably.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="WISH")
public class WishEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WISH_NO")
	private Integer wishNo;

	@Column(name = "FOLDER_NO", insertable = false, updatable = false)
	private Integer folderNo;

	@Column(name = "PRODUCT_NO")
	private Integer productNo;

	@Column(name = "REGISTRATION_DATE")
	private String registrationDate;

	@ManyToOne
	@JoinColumn(name="FOLDER_NO")
	private FolderEntity folderEntity;
}
