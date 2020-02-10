package com.paging.dao;

import java.util.List;

import com.paging.dto.BoardDto;

public interface PagingDao {
	
	String SELECT_LIST_SQL = "";
	
	public List<BoardDto> selectList();

}
