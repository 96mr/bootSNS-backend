package com.spring.boot.like.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Like;
import com.spring.boot.entity.LikeId;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
	public List<Like> findByBno(Board bno);
}
