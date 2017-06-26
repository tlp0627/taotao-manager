package com.taotao.service;

import java.util.List;

import com.taotao.common.EUTreeNode;
import com.taotao.common.TaotaoResult;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);
	TaotaoResult insertContentCategory(long parentId, String name);
}
