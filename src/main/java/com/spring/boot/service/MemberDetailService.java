package com.spring.boot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Member;
import com.spring.boot.entity.MemberDetails;
import com.spring.boot.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findById(username)
					.orElseThrow(()-> new UsernameNotFoundException(username));
		return new MemberDetails(member);
	}

}
