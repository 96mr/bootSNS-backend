package com.spring.boot.service;

import org.springframework.stereotype.Service;

import com.spring.boot.dto.MemberDto;
import com.spring.boot.entity.Member;
import com.spring.boot.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	public void join() {
		
	}
	
	public MemberDto.info info(Member member){
		Member m = memberRepository.findById(member.getUserNo())
				.orElseThrow(()->new IllegalStateException("회원 정보가 없습니다."));
		return new MemberDto.info(m);
	}
}
