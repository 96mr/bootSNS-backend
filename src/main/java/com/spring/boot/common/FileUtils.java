package com.spring.boot.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.dto.FileInfoDto;

@Component
public class FileUtils {
	@Value("${fileUpload.path}")
	private String path;
	
	public List<FileInfoDto.request> parseFileInfo(MultipartFile[] files, int memberId){
		List<FileInfoDto.request> list = new ArrayList<>();
		
		File target = new File(path);
		if(!target.exists()) {
			target.mkdirs();
		}
		for(MultipartFile file : files) {
			String orgName = file.getOriginalFilename();
			String extension = orgName.substring(orgName.lastIndexOf("."));
			String saveName = UUID.randomUUID().toString().replace("-", "") + extension;
			int size = (int) file.getSize();
			
			target = new File(path, saveName);
			
			try {
				file.transferTo(target);
				
				FileInfoDto.request dto = FileInfoDto.request.builder()
						.orgName(orgName)
						.saveName(saveName)
						.size(size)
						.memberId(memberId)
						.build();
				
				list.add(dto);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
														
		}
		
		return list;
	}
}
