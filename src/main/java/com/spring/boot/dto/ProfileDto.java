package com.spring.boot.dto;

import com.spring.boot.entity.Member;
import com.spring.boot.entity.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProfileDto {
	@Getter
	public static class info{
		private int userNo;
		private String name;
		private String introduce;
		private FileInfoDto.info image;
		private String id;
	
		public info(Profile profile, Member member) {
			this.userNo = profile.getUserNo();
			this.name = profile.getName();
			this.introduce = profile.getIntroduce();
			this.image = new FileInfoDto.info(profile.getImage());
			this.id = member.getId();
		}
	}
	
	@Getter
	public static class board{
		private String name;
		private FileInfoDto.info image;
	
		public board(Profile profile) {
			this.name = profile.getName();
			this.image = new FileInfoDto.info(profile.getImage());
		}
	}
	
	@Getter
	@NoArgsConstructor
	public static class request {
		private int userNo;
		private String name;
		private String introduce;
		private FileInfoDto.request image;
		
		public Profile toEntity() {
			return Profile.builder()
					.userNo(userNo)
					.name(name)
					.introduce(introduce)
					.image(image.toEntity())
					.build();
		}		
	}
}
