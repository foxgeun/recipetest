package com.recipe.service;

import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;


import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	
//	파일 업로드
	public String uploadFile(String uploadPath, String originalFileName,
			byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID(); //중복되지 않은 이름을 만든다
		
		// 이미지1.jpg -> 이미지의 확장자 명을 구한다..
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		// 파일이름 생성 -> ERSFHG4FDGD454.jpg
		String savedFileName = uuid.toString() + extension; 
		
		// C:/shop/item/ERSFHG4FDGD454.jpg
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;
		
		//파일업로드
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileData);
		fos.close();
		
		return savedFileName;
	}
	
}
