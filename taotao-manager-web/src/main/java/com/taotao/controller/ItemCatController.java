package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	@ResponseBody
	public List categoryList(@RequestParam(value="id",defaultValue="0") Long parentId) throws Exception{
		List catlist = new ArrayList();
		List<TbItemCat> list = itemCatService.getItemCatList(parentId);
		for(TbItemCat tbItemCat : list){
			Map<Object, Object> map = new HashMap<Object,Object>();
			map.put("id", tbItemCat.getId());
			map.put("text", tbItemCat.getName());
			map.put("state", tbItemCat.getIsParent()?"closed":"open");
			catlist.add(map);
		}
		
		return catlist;
	}
	
	
	
}
