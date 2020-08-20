package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 화면 띄우는 용도(등록창/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String StrI_board = request.getParameter("i_board");
		
		if(StrI_board == null) {
			ViewResolver.forwardLoginChk("board/regmod", request, response);
		} else {
			int i_board = MyUtils.parseStrToInt(StrI_board, 0);
			
			if(i_board == 0) {
				response.sendRedirect("/board/list");
				return;
			}
			
			BoardVO param = new BoardVO();
			param.setI_board(i_board);
			
			BoardVO data = BoardDAO.selBoard(i_board);
			request.setAttribute("data", data);
			
			ViewResolver.forwardLoginChk("board/regmod", request, response);
		}
		
	}

	// 처리 용도(DB에 등록/수정) 실시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//등록
		String StrI_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		int i_user = loginUser.getI_user();
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(i_user);
		
		if(StrI_board == "") {
			int result = BoardDAO.insBoard(param);
			response.sendRedirect("/board/list");
		} else {
			int i_board = MyUtils.parseStrToInt(StrI_board, 0);			
			param.setI_board(i_board);
			int result = BoardDAO.udtBoard(param);
			response.sendRedirect("/board/detail?i_board=" + i_board);
		}
		
		
		//수정
	
	}

}
