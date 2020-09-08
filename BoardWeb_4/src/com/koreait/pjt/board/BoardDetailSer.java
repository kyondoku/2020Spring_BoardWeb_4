package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
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
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ViewResolver.forwardLoginChk("board/detail", request, response);
//      }
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "": searchText);
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user());

		BoardDomain data = BoardDAO.selBoard(param);
		
		if(!"".equals(searchText) && ("b".equals(searchType) || "c".equals(searchType))) {
				String ctnt = data.getCtnt();
				ctnt = ctnt.replace(searchText
						, "<span class=\"highlight\">" + searchText + "</span>");
				data.setCtnt(ctnt);
			}
		data.setSearchText("%"+searchText+"%");
		data.setRecord_cnt(recordCnt);
		data.setSearchType(searchType);

		
		// 좋아요 한사람 리스트
		
		List<BoardDomain> like = BoardDAO.selBoardLikeList(i_board);
		request.setAttribute("like", like);
		
		
		//		단독으로 조회수 올리기 방지! --- [start]
		ServletContext application = getServletContext();
//		integer도 int랑 똑같이 쓰면 되는데, integet는 null을 담을수 있다.
		Integer readI_user = (Integer)application.getAttribute("read_" + strI_board);
		if(readI_user == null || readI_user != loginUser.getI_user()) {
			data.setHits(data.getHits()+1);
			BoardDAO.addHits(data);
			application.setAttribute("read_" + strI_board, loginUser.getI_user());
//															마지막으로 읽은 사람의 pk값을 저장
		}
//		단독으로 조회수 올리기 방지! --- [end]
		
		if(i_board == 0) {
			response.sendRedirect("/board/list");
			return;
		}
		
		request.setAttribute("data", data);
		
		
		
		//코멘트 리스트뿌리기
		List<BoardCmtVO> list = BoardCmtDAO.selCmtList(i_board);
		request.setAttribute("list", list);
		
		ViewResolver.forward("board/detail", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
