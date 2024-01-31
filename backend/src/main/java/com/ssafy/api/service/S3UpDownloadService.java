package com.ssafy.api.service;

import com.ssafy.api.request.AnimalReq;
import com.ssafy.db.entity.Animal;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 *	파일 업/다운로드 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface S3UpDownloadService {

	public String saveImage(@RequestParam MultipartFile multipartFile, String fileName, int postId)throws IOException;
	public Map<String, Object> getFile(String s3FileName) throws IOException;

	public String saveProfile(@RequestParam MultipartFile multipartFile,String userId) throws IOException;
	public String savePetProfile(@RequestParam MultipartFile multipartFile, AnimalReq animalReq) throws IOException;
	public String deleteFile(String filePath);
	public String saveLicenseFile(@RequestParam MultipartFile multipartFile,String userId) throws IOException;

}



