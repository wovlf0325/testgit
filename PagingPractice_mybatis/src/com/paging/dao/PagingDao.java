package com.paging.dao;

import java.util.List;

import com.paging.dto.BoardDto;
import com.paging.dto.PagingDto;

public interface PagingDao {
	
	public List<BoardDto> selectList();
	public int totalpage(PagingDto dto);

}
