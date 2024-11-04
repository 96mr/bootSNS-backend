package com.spring.boot.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Member;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
	public Optional<Board> findByBnoAndWriterId(long bno, Member writerId);
	public Long countByWriterId(Member writerId);
}
