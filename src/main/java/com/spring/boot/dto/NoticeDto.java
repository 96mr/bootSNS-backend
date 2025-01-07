package com.spring.boot.dto;

import java.util.Date;

import com.spring.boot.entity.Member;
import com.spring.boot.entity.Notice;
import com.spring.boot.entity.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoticeDto {
	@Getter
	public static class response{
		private long noteNo;
		private long sender;
		private MemberDto.simple receiver; //알림 받는 사람
		private NotificationType notetype;
		private long bno;
		private String content;
		private String noteurl;
		private Date regdate;
		private Date chkdate;
		private String delChk;
		
		public response(Notice notice) {
			this.noteNo = notice.getNoteNo();
			this.sender = notice.getSender();
			this.receiver = new MemberDto.simple(notice.getReceiver());
			this.notetype = notice.getNotetype();
			this.bno = notice.getBno();
			this.content = notice.getContent();
			this.noteurl = notice.getNoteurl();
			this.regdate = notice.getRegdate();
			this.chkdate = notice.getChkdate();
			this.delChk = notice.getDelChk();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Builder
	public static class request{
		private long noteNo;
		private long sender;
		private Member receiver;
		private NotificationType notetype;
		private long bno;
		private String content;
		private String noteurl;
		private Date regdate;
		private Date chkdate;
		private String delChk;
		
		public Notice toEntity() {
			return Notice.builder()
					.noteNo(noteNo)
					.sender(sender)
					.receiver(receiver)
					.notetype(notetype)
					.content(content)
					.noteurl(noteurl)
					.regdate(regdate)
					.chkdate(chkdate)
					.build();
		}
	}
}
