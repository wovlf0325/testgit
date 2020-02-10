package com.paging.biz;

import java.util.List;

import com.paging.dto.BoardDto;
import com.paging.dto.PagingDto;

public interface PagingBiz {
	
	public List<BoardDto> selectList(PagingDto dto);
	
	public PagingDto paging(PagingDto dto);
	
	public BoardDto selectOne(int seq);
	public int insert(BoardDto dto);
	public int update(BoardDto dto);
	public int delete(int seq);

}
