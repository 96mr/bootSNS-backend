package com.spring.boot.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.dto.FileInfoDto;

@Component
public class FileUtils {
	@Value("${fileUpload.path}")
	private String path;
	
	public FileInfoDto.request parseFileInfo(MultipartFile file, long memberId){
		File target = new File(path);
		if(!target.exists()) {
			target.mkdirs();
		}
		String orgName = file.getOriginalFilename();
		String extension = orgName.substring(orgName.lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "") + extension;
		int size = (int) file.getSize();
			
		target = new File(path, saveName);
			
		try {
			file.transferTo(target);
				
			return FileInfoDto.request.builder()
					.orgName(orgName)
					.saveName(saveName)
					.size(size)
					.memberId(memberId)
					.build();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return FileInfoDto.request.builder().build();
	}
}
