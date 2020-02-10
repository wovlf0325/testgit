package com.paging.biz;

import java.util.List;

import com.paging.dto.BoardDto;

public interface PagingBiz {
	
	public List<BoardDto> selectList();
	
	public int totalPage(int rows);

}
