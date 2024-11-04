package com.spring.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.board.repository.BoardRepository;
import com.spring.boot.common.FileUtils;
import com.spring.boot.dto.FileInfoDto;
import com.spring.boot.dto.ProfileDto;
import com.spring.boot.entity.FileInfo;
import com.spring.boot.entity.Member;
import com.spring.boot.entity.Profile;
import com.spring.boot.follow.repository.FollowRepository;
import com.spring.boot.member.repository.MemberRepository;
import com.spring.boot.member.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;
	private final MemberRepository memberRepository;
	private final FollowRepository followRepository;
	private final BoardRepository boardRepository;
	private final FileUtils fileUtils;
	
	@Transactional
	public ProfileDto.info getProfile(String id) {
		Member member = memberRepository.findById(id)
						.orElseThrow(()-> new IllegalStateException());
		Profile profile = profileRepository.findById(member.getUserNo())
						.orElseThrow(() -> new IllegalStateException());
		
		Long following = 0L, follower = 0L;
		List<Object[]> followList = followRepository.countFollowerAndFollowing(member.getUserNo());
		if (!followList.isEmpty()) {
            Object[] countArray = followList.get(0);
            following = (Long) countArray[0]; 
            follower = (Long) countArray[1];
		}
		
		Long boardCount = boardRepository.countByWriterId(member);
		return new ProfileDto.info(profile, following, follower, boardCount);
	}
	
	@Transactional
	public ProfileDto.myProfile getMyProfile(Member member){
		Profile profile = profileRepository.findById(member.getUserNo())
				.orElseThrow(() -> new IllegalStateException());
		return new ProfileDto.myProfile(profile);
	}

	@Transactional
	public ProfileDto.myProfile updateProfile(Member member, ProfileDto.request dto, MultipartFile image) {
		Profile profile = profileRepository.findById(member.getUserNo())
				.orElseThrow(() -> new IllegalStateException());
		profile.update(dto.getName(), dto.getIntroduce());
		
		if (image != null && !image.isEmpty()) {
	        FileInfoDto.request fileInfo = fileUtils.parseFileInfo(image, member.getUserNo());
	        FileInfo profileFile = profile.getImage();
	        profileFile.update(fileInfo.getOrgName(), fileInfo.getSaveName(), fileInfo.getSize());
	    }
		return new ProfileDto.myProfile(profile);
	}	
	
}
