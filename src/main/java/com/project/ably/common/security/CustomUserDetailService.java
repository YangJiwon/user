package com.project.ably.common.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.model.entity.MemberEntity;
import com.project.ably.model.vo.Member;
import com.project.ably.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MemberEntity> findMemberEntity = memberRepository.findById(email);
        if(findMemberEntity.isEmpty()){
            throw new BusinessErrorCodeException(ErrorCode.NOT_EXIST_MEMBER);
        }

        MemberEntity memberEntity = findMemberEntity.get();
        return new Member(memberEntity);
    }
}
