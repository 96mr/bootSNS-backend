package com.spring.boot.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Board;
import com.spring.boot.entity.Member;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {
	public List<Board> findByWriterIdOrderByRegdateDesc(Member writerId);
	public Optional<Board> findByBnoAndWriterId(int bno, Member writerId);
	@Query("SELECT b FROM Board b WHERE b.writerId.id = :member")
	public List<Board> findByMember(@Param("member") Member member);
}
