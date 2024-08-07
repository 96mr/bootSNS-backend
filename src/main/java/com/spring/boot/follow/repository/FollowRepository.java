package com.spring.boot.follow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Follow;
import com.spring.boot.entity.FollowId;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	public List<Follow> findByIdMid(long mid);
	public List<Follow> findByIdTid(long tid);
	public boolean existsByIdMidAndIdTid(long mid, long tid);
}
