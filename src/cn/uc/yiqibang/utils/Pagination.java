package cn.uc.yiqibang.utils;

import java.util.List;

public class Pagination {
	//定义每页显示的最多条数
	//开始查询的数据索引
	private int page;//页数
	private int pageCount;//每一页的最大数量
	private List<Object> objs;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<Object> getObjs() {
		return objs;
	}
	public void setObjs(List<Object> objs) {
		this.objs = objs;
	}
	
	
	
	
}
