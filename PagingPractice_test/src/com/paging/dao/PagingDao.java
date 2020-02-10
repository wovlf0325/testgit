package com.paging.dao;

import java.util.List;

import com.paging.dto.BoardDto;
import com.paging.dto.PagingDto;

public interface PagingDao {
	
	String SELECT_LIST_SQL = " SELECT * FROM PAGEBOARD ORDER BY SEQ DESC ";
	
	public List<BoardDto> selectList(PagingDto dto);
	public int totalpage(PagingDto dto);
	
	public BoardDto selectOne(int seq);
	public int insert(BoardDto dto);
	public int update(BoardDto dto);
	public int delete(int seq);

}
