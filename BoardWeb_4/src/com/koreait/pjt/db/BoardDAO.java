package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	
	public static int insBoard(BoardVO param) {
		
		String sql =" INSERT INTO t_board4 (i_board, title, ctnt, i_user ) "
				+ " VALUES (seq_board4.nextval, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}	
		});	
	}
	
	public static BoardDomain selBoard(BoardVO param) {
		BoardDomain result = new BoardDomain();
		result.setI_board(param.getI_board());
		
		
		String sql = " SELECT B.nm, A.i_user "
				+ " , A.title, A.ctnt, A.hits, TO_CHAR(A.r_dt, 'YYYY/MM/DD HH24:MI') as r_dt"
				+ " , TO_CHAR(A.m_dt, 'YYYY/MM/DD HH24:MI') as m_dt "
				+ " , DECODE(C.i_user, null, 0, 1)as yn_like "
				+ " FROM t_board4 A"
				+ " INNER JOIN t_user B"
				+ " ON A.i_user = B.i_user"
				+ " LEFT JOIN t_board4_like C "
				+ " ON A.i_board = C.i_board "
				+ " AND C.i_user = ? "
				+ " WHERE A.i_board = ? ";
		
		int resultInt = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setTitle(rs.getNString("title"));
					result.setHits(rs.getInt("hits"));
					result.setCtnt(rs.getNString("ctnt"));
					result.setI_user(rs.getInt("i_user")); // 작성자 i_user
					result.setR_dt(rs.getNString("r_dt"));
					result.setM_dt(rs.getNString("m_dt"));	
					result.setNm(rs.getNString("nm"));
					result.setYn_like(rs.getInt("yn_like"));
					}
				return 0;		
				}
			});
		return result;
	}
	
	public static List<BoardVO> selBoardList(BoardDomain param) {
//		final - 상수, 주소값 변경 불가능
		final List<BoardVO> list = new ArrayList();
		
		String sql = " SELECT A.* FROM(SELECT ROWNUM as RNUM, A.* FROM("
				+ " SELECT A.I_BOARD, A.I_USER, B.NM, A.TITLE, A.HITS, "
				+ " TO_CHAR(A.R_DT,'YYYY/MM/DD HH24:MI') R_DT,"
				+ " TO_CHAR(A.M_DT,'YYYY/MM/DD HH24:MI') M_DT"
				+ " FROM T_BOARD4 A LEFT JOIN T_USER B ON A.I_USER = B.I_USER ORDER BY i_board DESC"
				+ " ) A WHERE ROWNUM <= ?) A WHERE A.rnum > ?" ;
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
		
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getEldx());
				ps.setInt(2, param.getSldx());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					

					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setNm(nm);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					vo.setM_dt(rs.getNString("m_dt"));
					
					list.add(vo);
				}
				return 0;
			}
		});
		return list;	
	}
	
	
	// 페이징 숫자 가져오기
	public static int selPagingCnt(final BoardDomain param) {
		
		String sql = " SELECT CEIL(COUNT(i_board) / ?) FROM t_board4 ";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getRecord_cnt());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		});
	}
	
	
	public static int udtBoard(BoardVO param) {
		String sql = " UPDATE t_board4 "
				+ " SET title = ? "
				+ " , ctnt = ? "
				+ " , m_dt = SYSDATE "
				+ " WHERE i_board = ?"
				+ "	AND i_user = ?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
			}
		});
		
		
	}
	
	public static int delBoard(final BoardVO param) {
		
		String sql =" DELETE"
				+ " FROM t_board4"
				+ " WHERE i_board = ? "
				+ " AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}
		});
	}
	
	public static int addHits(BoardVO param) {
		
		String sql = " UPDATE t_board4"
				+ " SET hits = ?"
				+ " WHERE i_board = ?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				//param.setHits(param.getHits()+1);
				ps.setInt(1, param.getHits());
				ps.setInt(2, param.getI_board());
			}
		});
	}
	
	public static int insBoardLike(BoardVO param) {
		
		String sql = " INSERT INTO t_board4_like(i_user, i_board)"
				+ " VALUES (?, ?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface( ) {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
		});
	}
	
	public static int delBoardLike(BoardVO param) {
		
		String sql = " DELETE "
				+ " FROM t_board4_like"
				+ " WHERE i_user = ?"
				+ "	AND i_board = ?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface( ) {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
		});
	}
}

