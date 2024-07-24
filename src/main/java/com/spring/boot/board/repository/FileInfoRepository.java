package com.spring.boot.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {

}
