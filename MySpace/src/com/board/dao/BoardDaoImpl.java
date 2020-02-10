package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.ReplyDto;
import com.board.dto.BoardDto;
import static com.board.db.JDBCTemplate.*;

public class BoardDaoImpl implements BoardDao {

	@Override
	public List<ReplyDto> selectList(int to, int from) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			pstm.setInt(1, to);
			pstm.setInt(2, from);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				ReplyDto dto = new ReplyDto(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getDate(5),
											rs.getInt(6),
											rs.getInt(7),
											rs.getInt(8),
											rs.getString(9));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return list;
	}

	@Override
	public BoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BoardDto dto = null;
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new BoardDto(rs.getInt(1),
								   rs.getString(2),
								   rs.getString(3),
								   rs.getString(4),
								   rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}

	@Override
	public int insert(BoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	@Override
	public int update(BoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		}
		
		return res;
	}

}
