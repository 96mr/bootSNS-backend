package com.spring.boot.dto;

import javax.validation.constraints.NotNull;

import com.spring.boot.entity.Profile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProfileDto {
	@Getter
	public static class info{
		private long userNo;
		private String name;
		private String introduce;
		private FileInfoDto.info image;
		private String id;
		private long following;
		private long follower;
		private long boardCount;
	
		public info(Profile profile, long following, long follower, long boardCount) {
			this.userNo = profile.getUserNo();
			this.name = profile.getName();
			this.introduce = profile.getIntroduce();
			this.image = new FileInfoDto.info(profile.getImage());
			this.id = profile.getMember().getId();
			this.following = following;
			this.follower = follower;
			this.boardCount = boardCount;
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
	public static class myProfile{
		private long userNo;
		private String name;
		private String introduce;
		private FileInfoDto.info image;
		private String id;
	
		public myProfile(Profile profile) {
			this.userNo = profile.getUserNo();
			this.name = profile.getName();
			this.introduce = profile.getIntroduce();
			this.image = new FileInfoDto.info(profile.getImage());
			this.id = profile.getMember().getId();
		}

		//mock 테스트위해 추가
		public myProfile(ProfileDto.request dto) {
	        this.userNo = dto.getUserNo();
	        this.name = dto.getName();
	        this.introduce = dto.getIntroduce();
	    }
	}
	
	@Getter
	@NoArgsConstructor
	public static class request {
		private long userNo;
		@NotNull
		private String name;
		@NotNull
		private String introduce;
		
		@Builder
		public request(long userNo, String name, String introduce) {
			this.userNo = userNo;
			this.name = name;
			this.introduce = introduce;
		}
		
		public Profile toEntity() {
			return Profile.builder()
					.userNo(userNo)
					.name(name)
					.introduce(introduce)
					.build();
		}		
	}
}
