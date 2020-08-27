package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	// 댓글 (삭제)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_cmt = MyUtils.getIntParameter(request, "i_cmt");
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		BoardCmtVO cv = new BoardCmtVO();
		cv.setI_cmt(i_cmt);
		cv.setI_user(loginUser.getI_user());
//		cv.setI_user(i_user);
		
		BoardCmtDAO.delCmt(cv);
		response.sendRedirect("/board/detail?i_board="+i_board);
		
	}

	// 댓글 (등록/수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		int i_cmt = MyUtils.parseStrToInt(strI_cmt);
		
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		int i_user = MyUtils.getLoginUser(request).getI_user();
		String cmt = request.getParameter("cmt");
		
		BoardCmtVO cv = new BoardCmtVO();
		cv.setCmt(cmt);
		cv.setI_board(i_board);
		cv.setI_user(i_user);
		cv.setI_cmt(i_cmt);
		
		
		switch(i_cmt) {
		case 0: //등록
			BoardCmtDAO.insCmt(cv);
			
			break;
		default: // 수정 (수정 일자 변경)
			BoardCmtDAO.updCmt(cv);
			break;
		}
		
		response.sendRedirect("/board/detail?i_board="+i_board);
		
		
		
	}

}
