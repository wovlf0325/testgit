package com.paging.dao;

public interface PagingDao {
	
	String TOTAL_PAGE_SQL = " SELECT COUNT(*) AS TOTALPAGE FROM BOARD ";
	
	public int totalPage();

}
