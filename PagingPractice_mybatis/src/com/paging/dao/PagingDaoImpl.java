package com.paging.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.paging.dto.BoardDto;
import com.paging.dto.PagingDto;

public class PagingDaoImpl extends SqlMapConfig implements PagingDao {
	
	private String namespace = "mypage.";

	@Override
	public List<BoardDto> selectList() {
		List<BoardDto> list = null;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectList");
		} catch (Exception e) {
			System.out.println("[error] : selectList");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	@Override
	public int totalpage(PagingDto dto) {
		
		return 0;
	}

}
