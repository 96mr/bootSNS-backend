package com.spring.boot.dto;

import java.util.Date;

import com.spring.boot.entity.FileInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FileInfoDto {
	@Getter
	public static class info{
		private int fno;
		private String saveName;
		private int size;
		private Date regdate;
		
		public info(FileInfo fileInfo) {
			this.fno = fileInfo.getFno();
			this.saveName = fileInfo.getSaveName();
			this.size = fileInfo.getSize();
			this.regdate = fileInfo.getRegdate();
		}
		
	}
	
	@NoArgsConstructor
	@Getter
	public static class request{
		private String orgName;
		private String saveName;
		private int size;
		private long memberId;
		
		@Builder
		public request(String orgName, String saveName, int size, long memberId) {
			this.orgName = orgName;
			this.saveName = saveName;
			this.size = size;
			this.memberId = memberId;
		}
		
		public FileInfo toEntity() {
			return FileInfo.builder()
					.orgName(orgName)
					.saveName(saveName)
					.size(size)
					.memberId(memberId)
					.build();
		}
	}
}
