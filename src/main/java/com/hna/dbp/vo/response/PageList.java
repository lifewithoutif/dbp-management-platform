package com.hna.dbp.vo.response;

import java.util.List;

public class PageList<T> {

	//表示第几页，从0开始,页码数
	private int pageIndex;
	//表示每页大小
	private int pageSize=10;
	//数据库中总的记录条数
	private int count;
	//这次请求的数据结果
	private List<T> dataList;
	//总页数
	private int pageCount;
	//每页起始数
	private int startIndex;
	
	public PageList(){
		
	}
	public PageList(int pageIndex,int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	
	//重新计算起始分页
	public void setStartIndex(int startIndex) {
		if (count <= 0) {
			startIndex = 0;
		} else if (startIndex >= count) {
			startIndex = startIndex-10;
		} else if (startIndex < 0) {
			startIndex = 0;
		} 
		this.startIndex = startIndex;
		if(pageSize==0){
			pageSize=10;
		}
		this.pageIndex=startIndex/pageSize+1;
	}
	
	
	
	private void initPageCount() {
		pageCount = count / pageSize;
		if (count % pageSize != 0) {
			pageCount++;
		}
		if(pageCount == 0){
			pageCount = 1;
		}
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		initPageCount();
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
}
