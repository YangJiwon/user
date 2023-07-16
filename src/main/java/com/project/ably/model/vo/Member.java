package com.project.ably.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import com.project.ably.model.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member implements UserDetails {
	private String email;
    private String password;
	private String registrationDate;

	public boolean isExistMember(){
		return !ObjectUtils.isEmpty(this.email);
	}

	public Member(MemberEntity memberEntity){
		this.email = memberEntity.getEmail();
		this.password = memberEntity.getPassword();
		this.registrationDate = memberEntity.getRegistrationDate();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
