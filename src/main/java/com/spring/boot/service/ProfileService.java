package com.spring.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dto.ProfileDto;
import com.spring.boot.entity.Member;
import com.spring.boot.entity.Profile;
import com.spring.boot.member.repository.MemberRepository;
import com.spring.boot.member.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public ProfileDto.info getProfile(String id) {
		Member member = memberRepository.findById(id)
						.orElseThrow(()-> new IllegalStateException());
		Profile profile = profileRepository.findById(member.getUserNo())
						.orElseThrow(() -> new IllegalStateException());
		return new ProfileDto.info(profile, member);
	}

	@Transactional
	public ProfileDto.info update(Member member, ProfileDto.request dto) {
		Profile profile = profileRepository.findById(member.getUserNo())
				.orElseThrow(() -> new IllegalStateException());
		profile.update(dto.getName(), dto.getIntroduce());
		return new ProfileDto.info(profile, member);
	}	
	
}
