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
@Slf4j
public class FileUploadService {
	@Autowired
	private AwsService awsService;
	@Autowired
	private PostDto postDto;
	@Autowired
	private PostRepository postsRepasitory;

	public void upload(HttpServletRequest request, MultipartFile multiFile) {
		try {
			File convFile = new File(multiFile.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(multiFile.getBytes());

			fos.close();
	
			postDto.setActivated(request.getParameter("activated"));
			//postDto.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			postDto.setFilePath(request.getParameter("filePath"));
			postDto.setFileSize(request.getParameter("fileSize"));
			postDto.setDistinction(request.getParameter("distinction"));
			postDto.setName(request.getParameter("name"));
			postDto.setVer(request.getParameter("ver"));
			postDto.setContent(request.getParameter("content"));
			
			//컬럼변경되서 post에는 category가 없음 일단 하드코딩 
			awsService.fileUpload(convFile, "HEAG", convFile.getName());

			postsRepasitory.save(postDto.toEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
