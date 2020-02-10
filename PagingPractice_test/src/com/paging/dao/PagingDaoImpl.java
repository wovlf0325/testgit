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
	public List<BoardDto> selectList(PagingDto dto) {
		List<BoardDto> list = null;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectList", dto);
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
		int res = 0;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.selectOne(namespace + "totalpage", dto);
		} catch (Exception e) {
			System.out.println("[error] : totalpage");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public BoardDto selectOne(int seq) {
		BoardDto dto = null;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace+"insert", dto);
		} catch (Exception e) {
			System.out.println("[error] : selectOne");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int insert(BoardDto dto) {
		int res = 0;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace+"insert", dto);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[error] : insert");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return res;
	}

	@Override
	public int update(BoardDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
