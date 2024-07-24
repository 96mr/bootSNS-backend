package com.spring.boot.dto;

import java.util.Date;

import com.spring.boot.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {
	@Getter
	public static class info{
		private int userNo;
		private String id;
		private Date regdate;
		private String birth;
		private String phone;
		private String email;
		private String privacy;
		
		public info(Member member) {
			this.userNo = member.getUserNo();
			this.id = member.getId();
			this.regdate = member.getRegdate();
			this.birth = member.getBirth();
			this.phone = member.getPhone();
			this.email = member.getEmail();
			this.privacy = member.getPrivacy();
		}
	}
	
	@NoArgsConstructor
	@Getter
	public static class request{
		private int userNo;
		private String id;
		private String password;
		private Date regdate;
		private String birth;
		private String phone;
		private String email;
		private String privacy;
		
		public Member toEntity() {
			return Member.builder()
					.userNo(userNo)
					.id(id)
					.password(password)
					.regdate(regdate)
					.birth(birth)
					.phone(phone)
					.email(email)
					.privacy(privacy)
					.build();
		}
	}
	
	@Getter
	public static class simple{
		private int userNo;
		private String id;
		private ProfileDto.board profile;
		
		public simple(Member member) {
			this.userNo = member.getUserNo();
			this.id = member.getId();
			this.profile = new ProfileDto.board(member.getProfile());
		}
	}
}
