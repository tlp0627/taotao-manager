package com.taotao.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.FtpUtil;
import com.taotao.common.IDUtils;
import com.taotao.common.PictureResult;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;
	@Value("${FTP_SERVER_IP}")
	private String FTP_SERVER_IP;
	@Value("${FTP_SERVER_PORT}")
	private Integer FTP_SERVER_PORT;
	@Value("${FTP_SERVER_USERNAME}")
	private String FTP_SERVER_USERNAME;
	@Value("${FTP_SERVER_PASSWORD}")
	private String FTP_SERVER_PASSWORD;


	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) {
		String path = savePicture(uploadFile);
		PictureResult result = new PictureResult(0, IMAGE_BASE_URL +path);
		return result;
	}


	private String savePicture(MultipartFile uploadFile) {
		String result = null;
		try {
			
			if(uploadFile.isEmpty())
				return null;
			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date())+"/"+
					new SimpleDateFormat("MM").format(new Date())+"/" +
					new SimpleDateFormat("dd").format(new Date());
			String originalFilename  =uploadFile.getOriginalFilename();
			String newFileName = IDUtils.genImageName()+originalFilename.substring(originalFilename.lastIndexOf("."));
			FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD, FILI_UPLOAD_PATH, filePath,newFileName,uploadFile.getInputStream());
			result = filePath +"/" + newFileName;
		} catch (Exception e) {
			
		}
		return result;
	}

}
