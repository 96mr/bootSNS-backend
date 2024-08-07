package com.spring.boot.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	public Optional<Member> findById(String id);
	public Optional<Member> findById(long userNo);
	public boolean existsById(String id);
}
