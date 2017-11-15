package cn.cu.test;

import org.junit.Assert;
import org.junit.Test;

import cn.uc.yiqibang.dao.TNewsMapper;
import cn.uc.yiqibang.dao.impl.NewsMapperImpl;
import cn.uc.yiqibang.utils.Result;

public class NewsTest {

	TNewsMapper newsDao=new NewsMapperImpl();
	@Test
	public void selectAll() {
		Result result = newsDao.selectAll();
		System.out.println(result.getRetData());
		Assert.assertNotNull(result.getRetData());
	}

}
