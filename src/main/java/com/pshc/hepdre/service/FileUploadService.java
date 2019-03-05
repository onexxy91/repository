package com.pshc.hepdre.service;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pshc.hepdre.dto.PostDto;
import com.pshc.hepdre.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class FileUploadService {
	@Autowired
	private AwsService awsService;
	
	public void upload(HttpServletRequest request, MultipartFile multiFile) {
		try {
			
			File convFile = new File(multiFile.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(multiFile.getBytes());
			fos.close();
			 
			// Category Name Setting 필요
			awsService.fileUpload(convFile, "HEAG", convFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
