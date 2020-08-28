package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//										detail에서의 key값("/board/toggleLike?i_board=${data.i_board}&yn_like=" 에서 ${data.i_board})
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "": searchText);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		String strYn_like = request.getParameter("yn_like");
		int yn_like = MyUtils.parseStrToInt(strYn_like);
		
		int i_user = MyUtils.getLoginUser(request).getI_user();
			
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(i_user);
		
		if(yn_like == 0) {
			BoardDAO.insBoardLike(param);
		} else {
			BoardDAO.delBoardLike(param);
		}
		response.sendRedirect("/board/detail?i_board=" + i_board + "&record_cnt=" + recordCnt + "&searchText=" + searchText);
	}
}
