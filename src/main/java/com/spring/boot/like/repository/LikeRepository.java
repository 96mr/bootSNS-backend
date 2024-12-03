package com.spring.boot.like.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.BoardLike;
import com.spring.boot.entity.LikeId;

public interface LikeRepository extends JpaRepository<BoardLike, LikeId> {
	public List<BoardLike> findByBno(Board bno);
	public boolean existsByBnoAndLikeId(long bno, long likeId);
}
