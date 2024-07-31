package com.spring.boot.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.board.repository.BoardFileRepository;
import com.spring.boot.board.repository.BoardRepository;
import com.spring.boot.board.repository.FileInfoRepository;
import com.spring.boot.common.FileUtils;
import com.spring.boot.dto.BoardDto;
import com.spring.boot.dto.BoardFileDto;
import com.spring.boot.dto.FileInfoDto;
import com.spring.boot.entity.Board;
import com.spring.boot.entity.FileInfo;
import com.spring.boot.entity.Member;
import com.spring.boot.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	private final FileInfoRepository fileInfoRepository;
	private final BoardFileRepository boardFileRepository;
	private final FileUtils fileUtils;
	
	@Transactional(readOnly = true)
	public Slice<BoardDto.info> timeline(Member member, Pageable pageable){
		return boardRepository.timeline(member.getUserNo(), pageable);
	}
	
	@Transactional(readOnly= true)
	public BoardDto.info selectBoard(String id, long bno) {
		Member writer = memberRepository.findById(id)
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));
		
		Board board = boardRepository.findByBnoAndWriterId(bno, writer)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));
		return new BoardDto.info(board); 
	}
	
	@Transactional
	public void save(BoardDto.create dto, MultipartFile[] files, Member writer) {
		Board bno = boardRepository.save(dto.toEntity(writer));

		for(MultipartFile file : files) {
			FileInfoDto.request fileDto = fileUtils.parseFileInfo(file, writer.getUserNo());
			FileInfo fno = fileInfoRepository.save(fileDto.toEntity());
			
			BoardFileDto.request boardFile = new BoardFileDto.request();
			boardFileRepository.save(boardFile.toEntity(bno, fno));
		}
	}
}
