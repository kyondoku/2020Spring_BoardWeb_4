package com.koreait.pjt.board;

import java.awt.image.FilteredImageSource;
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
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 화면 띄우는 용도(등록창/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		String StrI_board = request.getParameter("i_board");
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "": searchText);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		int page = MyUtils.getIntParameter(request, "page");
		page = page == 0 ? 1 : page;
		
		if(StrI_board == null) {
			ViewResolver.forwardLoginChk("board/regmod", request, response);
		} else {
			int i_board = MyUtils.parseStrToInt(StrI_board, 0);
			
			if(i_board == 0) {
				response.sendRedirect("/board/list");
				return;
			}
			
			BoardDomain param = new BoardDomain();
			param.setI_board(i_board);
			param.setRecord_cnt(recordCnt);
			param.setSearchType(searchType);
			param.setPage(page);
			param.setSearchText("%"+searchText+"%");
			
			BoardVO data = BoardDAO.selBoard(param);
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
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "": searchText);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		int page = MyUtils.getIntParameter(request, "page");
		page = page == 0 ? 1 : page;
		
		request.setAttribute("searchText", searchText);
		request.setAttribute("record_cnt", recordCnt);
		request.setAttribute("searchText", searchType);
		request.setAttribute("page", page);
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		int i_user = loginUser.getI_user();
		
		String filterCtnt1 = scriptFilter(ctnt);
		String filterCtnt2 = swearWordFilter(filterCtnt1);
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(filterCtnt2);
		param.setI_user(i_user);
		
		if(StrI_board == "") {
			int result = BoardDAO.insBoard(param);
			response.sendRedirect("/board/list");
		} else {
			int i_board = MyUtils.parseStrToInt(StrI_board, 0);			
			param.setI_board(i_board);
			int result = BoardDAO.udtBoard(param);
			response.sendRedirect(String.format("/board/detail?page=%d&record_cnt=%d&searchText=%s&searchType=%s&i_board=%d", page, recordCnt, searchText, searchType, i_board));
		}	
	}

	//욕 필터
	private String swearWordFilter(final String ctnt) {
		String[] filters = {"개새끼", "미친년", "ㄱㅐㅅㅐㄲㅣ"};
		String result = ctnt;
		for(int i=0; i<filters.length; i++) {
			result = result.replace(filters[i], "***");
		}
		return result;
	}
	
	
	
	//스크립트 필터
	private String scriptFilter(String ctnt) {
		String[] filters = {"<script>"};
		String[] filterResults = {"&lt;script&gt;","&lt;script&gt;"};
		
		String result = ctnt;
		for(int i=0; i<filters.length; i++) {
			result = result.replace(filters[i], filterResults[i]);
		}
		return result;
	}
	
}
