package com.taotao.test;

import java.util.List;

import javax.rmi.CORBA.Tie;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper{
	@Test
	public void testPageHelper(){
		ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		TbItem tbItem = new TbItem();
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(1, 10);
		List <TbItem>list = tbItemMapper.selectByExample(example);
		for(TbItem tItem : list){
			System.out.println(tItem.getTitle());
		}
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		Long total = pageInfo.getTotal();
		System.out.println("共有商品："+ total);
	}
}
