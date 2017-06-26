package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EUDataGridResult;
import com.taotao.common.IDUtils;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long ItemId) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(ItemId);
		List <TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			TbItem tbItem = list.get(0);
			return tbItem;
		}
		return null;
	}


	@Override
	public EUDataGridResult  getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List <TbItem> list = itemMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}


	@Override
	public TaotaoResult createItem(TbItem tbItem,String desc,String itemParam) throws Exception {
		Long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		tbItem.setStatus((byte)1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		itemMapper.insert(tbItem);
		
		TaotaoResult result = insertItemDesc(itemId,desc);
		if(result.getStatus()!=200){			
			throw new Exception();
		}
		result = insertItemParamItem(itemId, itemParam);
		if(result.getStatus()!=200){			
			throw new Exception();
		}
		return TaotaoResult.ok();
	}


	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		TbItemParamItem tbItemParamItem =new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParam);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}


	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}




	
}
