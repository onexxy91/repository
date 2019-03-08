package com.pshc.hepdre.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pshc.hepdre.dto.PostDto;
import com.pshc.hepdre.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class FileUploadService {
	@Autowired
	private AwsService awsService;
	@Autowired
	private PostService postService;
	@Value("${file.path}")
	private String filePath;

	private FileOutputStream fos;

	public void upload(PostDto postDto, MultipartFile multiFile) {
		try {

			String fileUid = Integer.toString(postService.read().size()) + "_";

			File convFile = new File(fileUid + multiFile.getOriginalFilename());

			fos = new FileOutputStream(convFile);

			fos.write(multiFile.getBytes());

			fos.close();

			awsService.fileUpload(convFile, postDto.getCategory().getName(), convFile.getName());
			// set uid+fileName
			postDto.setName(convFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
