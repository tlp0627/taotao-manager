package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.EUTreeNode;
import com.taotao.common.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria =example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> treeList = new ArrayList<EUTreeNode>();
		for(TbContentCategory contentCategory : list){
			EUTreeNode treeNode =new EUTreeNode();
			treeNode.setId(contentCategory.getId());
			treeNode.setState(contentCategory.getIsParent()?"closed":"open");
			treeNode.setText(contentCategory.getName());
			treeList.add(treeNode);
		}
		return treeList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(contentCategory);
		TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCategory.getIsParent()){
			parentCategory.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		return TaotaoResult.ok(contentCategory);
	}

}
