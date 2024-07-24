package com.spring.boot.service;

import java.util.List;

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
	
	@Transactional
	public Slice<BoardDto.info> timeline(Member member, Pageable pageable){
		return boardRepository.timeline(member.getUserNo(), pageable);
	}
	
	@Transactional
	public BoardDto.info selectBoard(String id, int bno) {
		Member writer = memberRepository.findById(id)
				.orElseThrow(()-> new IllegalStateException("존재하지 않는 게시글입니다."));
		
		Board board = boardRepository.findByBnoAndWriterId(bno, writer)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));
		return new BoardDto.info(board); 
	}
	
	@Transactional
	public void save(BoardDto.create dto, MultipartFile[] files, Member writer) {
		Board bno = boardRepository.save(dto.toEntity(writer));
		System.out.println("bno : " + bno.getBno());
		List<FileInfoDto.request> fileDto = fileUtils.parseFileInfo(files, writer.getUserNo());
		for(FileInfoDto.request req : fileDto) {
			FileInfo fno = fileInfoRepository.save(req.toEntity());
			System.out.println("fno : "+ fno.getFno());
			BoardFileDto.request boardFile = new BoardFileDto.request();
			boardFileRepository.save(boardFile.toEntity(bno, fno));
		}
	}
}
