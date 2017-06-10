package com.taotao.service;

import com.taotao.common.EUDataGridResult;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EUDataGridResult getItemList(int page,int rows);
	TaotaoResult createItem(TbItem tbItem, String desc) throws Exception;
}
