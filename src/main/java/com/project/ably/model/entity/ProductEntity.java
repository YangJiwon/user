package com.project.ably.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PRODUCT")
public class ProductEntity {
	@Id
	@Column(name = "PRODUCT_NO")
	private Integer productNo;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column
	private String thumbnail;

	@Column
	private Double price;

	@Column(name = "REGISTRATION_DATE")
	private String registrationDate;
}
