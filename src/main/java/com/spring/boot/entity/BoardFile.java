package com.spring.boot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SequenceGenerator(name="BF_SEQ", sequenceName="BF_NO_SEQ", allocationSize = 1)
@Entity
@Table(name="BOARD_FILE", schema="EX1")
public class BoardFile {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BF_SEQ")
	private long bfno;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bno")
	private Board bno;
	@OneToOne
	@JoinColumn(name="fno")
	private FileInfo fno;
	
	@Builder
	public BoardFile(long bfno, Board bno, FileInfo fno) {
		this.bfno = bfno;
		this.bno = bno;
		this.fno = fno;
	}
	
}
