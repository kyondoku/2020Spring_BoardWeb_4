package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String strI_user = request.getParameter("i_user");
//		int i_user = MyUtils.parseStrToInt(strI_user);
//		
//		String user_id = request.getParameter("user_id");
//		String nm = request.getParameter("nm"); 
//		String email = request.getParameter("email");
//
//		UserVO uv = new UserVO();
//		uv.setUser_id(user_id);
//		uv.setNm(nm);
//		uv.setEmail(email);
//		
//		
//		
//		
		ViewResolver.forward("/board/profile", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
