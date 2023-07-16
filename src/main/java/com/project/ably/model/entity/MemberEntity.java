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
@Table(name="MEMBER")
public class MemberEntity {
	@Id
	@Column
	private String email;

	@Column
	private String password;

	@Column(name = "REGISTRATION_DATE")
	private String registrationDate;
}
