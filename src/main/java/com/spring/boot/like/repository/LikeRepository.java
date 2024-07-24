package com.spring.boot.like.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Like;

public interface LikeRepository extends JpaRepository<Like, String> {
	public List<Like> findByBno(Board bno);
}
