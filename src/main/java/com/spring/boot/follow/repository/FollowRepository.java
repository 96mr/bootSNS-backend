package com.spring.boot.follow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Follow;
import com.spring.boot.entity.FollowId;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	public List<Follow> findByIdMid(int mid);
	public List<Follow> findByIdTid(int tid);
	public Optional<Follow> findByIdMidAndIdTid(int mid, int tid);
}
