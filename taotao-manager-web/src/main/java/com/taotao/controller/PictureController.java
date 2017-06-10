package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.JsonUtils;
import com.taotao.common.PictureResult;
import com.taotao.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile uploadFile) throws Exception{
		PictureResult pictureResult = pictureService.uploadFile(uploadFile);
		String pictureString = JsonUtils.objectToJson(pictureResult);
		return pictureString;
	}
}
