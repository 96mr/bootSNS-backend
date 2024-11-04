package com.spring.boot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SequenceGenerator(name="BOARD_SEQ", sequenceName="BOARD_NO_SEQ", allocationSize = 1)
@Entity
@Table(name="BOARD", schema="EX1")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOARD_SEQ")
	private Long bno;
	@Column
	@Size(max=300)
	private String content;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer_id")
	private Member writerId;
	@Temporal(TemporalType.DATE)
	private Date regdate;
	private int hit;
	@Column(name="del_chk")
	private String delChk;
	
	@OneToMany(mappedBy="bno")
	private List<BoardLike> likes = new ArrayList<>();
	@OneToMany(mappedBy="bno")
	private List<Reply> replies = new ArrayList<>();
	@OneToMany(mappedBy="bno", cascade = CascadeType.ALL)
	private List<BoardFile> files = new ArrayList<>();
	
	@Builder
	public Board(Long bno, String content, Member writerId, Date regdate, int hit, String delChk) {
		this.bno = bno;
		this.content = content;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.delChk = delChk;
	}
	
	public void addFile(BoardFile boardFile) {
		files.add(boardFile);
	}
	
}
