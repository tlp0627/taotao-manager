package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.ExceptionUtil;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
@RequestMapping("/content")
@Controller
public class ContentController {
@Autowired
private ContentService contentService;

@RequestMapping("/save")
@ResponseBody
public TaotaoResult insertContent(TbContent content){
	TaotaoResult result = contentService.insertContent(content);
	return result;
}

}
