package com.taotao.service.impl;

import java.awt.image.DataBufferUShort;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		TbItemParamItem tbItemParamItem =new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParam);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}

}
