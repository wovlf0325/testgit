package com.paging.biz;

import java.util.List;

import com.paging.dao.PagingDao;
import com.paging.dao.PagingDaoImpl;
import com.paging.dto.BoardDto;
import com.paging.dto.PagingDto;

public class PagingBizImpl implements PagingBiz {
	
	private PagingDao dao = new PagingDaoImpl();

	@Override
	public List<BoardDto> selectList(PagingDto dto) {
		int page = dto.getPage();
		int rows = dto.getRows();
		
		dto.setTo(rows * (page - 1) + 1);
		dto.setFrom(rows * page);
		
		return dao.selectList(dto);
	}

	@Override
	public PagingDto paging(PagingDto dto) {
		dto.setTotalpage((int)Math.ceil((double)dao.totalpage(dto)/dto.getRows()));
		
		return dto;
	}

	@Override
	public BoardDto selectOne(int seq) {
		// TODO Auto-generated method stub
		return dao.selectOne(seq);
	}

	@Override
	public int insert(BoardDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(BoardDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return dao.delete(seq);
	}

}
