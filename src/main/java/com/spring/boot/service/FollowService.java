package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dto.FollowDto;
import com.spring.boot.entity.Follow;
import com.spring.boot.entity.Member;
import com.spring.boot.event.FollowAddedEvent;
import com.spring.boot.follow.repository.FollowRepository;
import com.spring.boot.member.repository.MemberRepository;

@Service
public class FollowService {
	private MemberRepository memberRepository;
	private FollowRepository followRepository;
	private ApplicationEventPublisher eventPublisher;
	
	public FollowService(MemberRepository memberRepository, FollowRepository followRepository, ApplicationEventPublisher eventPublisher){
		this.memberRepository = memberRepository;
		this.followRepository = followRepository;
		this.eventPublisher = eventPublisher;
	}
	
	@Transactional(readOnly = true)
	public List<FollowDto.response> followingList(String id){
		Member member = memberRepository.findById(id)
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));
		
		List<Follow> list = followRepository.findByIdMid(member.getUserNo());
		return list.stream().map(FollowDto.response::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<FollowDto.response> followerList(String id){
		Member member = memberRepository.findById(id)
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));
		
		List<Follow> list = followRepository.findByIdTid(member.getUserNo());
		return list.stream().map(FollowDto.response::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public boolean found(Member from, String target) {
		Member to = memberRepository.findById(target)
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));
		return followRepository.existsByIdMidAndIdTid(from.getUserNo(), to.getUserNo());
	}

	@Transactional
	public void insert(Member from, String target) {
		Member to = memberRepository.findById(target)
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));
		FollowDto.request followDto = new FollowDto.request();
		
		Follow follow = followRepository.save(followDto.toEntity(from, to));
		
		FollowAddedEvent event = new FollowAddedEvent(follow, from, to);
		eventPublisher.publishEvent(event);
	}

	public void delete(Member member, String target) {
		
	}
}
