package com.paging.biz;

import java.util.List;

import com.paging.dao.PagingDao;
import com.paging.dao.PagingDaoImpl;
import com.paging.dto.BoardDto;

public class PagingBizImpl implements PagingBiz {
	
	private PagingDao dao = new PagingDaoImpl();

	@Override
	public List<BoardDto> selectList() {
		return dao.selectList();
	}

	@Override
	public int totalPage(int rows) {
		// TODO Auto-generated method stub
		return 0;
	}

}
