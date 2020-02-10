package com.paging.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paging.dto.BoardDto;
import static com.paging.db.JDBCTemplate.*;

public class PagingDaoImpl implements PagingDao {

	@Override
	public List<BoardDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			rs = pstm.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getDate(5));
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

}
