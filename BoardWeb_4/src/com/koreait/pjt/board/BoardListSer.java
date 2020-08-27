package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

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


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int record_cnt = Const.RECORD_CNT;
		int page = MyUtils.getIntParameter(request, "page");
		page = page == 0 ? 1 : page;
		
		int eldx = page * record_cnt;
		int sldx = eldx - record_cnt;
		
		BoardDomain param = new BoardDomain();
		param.setRecord_cnt(record_cnt); //한 페이지당 10개
		param.setPage(page);
		param.setEldx(eldx);
		param.setSldx(sldx);
	     
	    request.setAttribute("list", BoardDAO.selBoardList(param));
	    request.setAttribute("page", page);
	    request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));

	    ViewResolver.forwardLoginChk("board/list", request, response);
		}
	}
