package com.spring.boot.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.BoardFile;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Integer> {

}
