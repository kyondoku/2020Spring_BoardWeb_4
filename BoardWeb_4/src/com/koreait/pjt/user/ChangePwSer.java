package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/changePw")
public class ChangePwSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		ViewResolver.forwardLoginChk("user/changePw", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String pw = request.getParameter("pw");
		
		String user_id = MyUtils.getLoginUser(request).getUser_id();
		String user_pw = pw;
		String encrypt_pw = MyUtils.encryptString(user_pw);
		int i_user = MyUtils.getLoginUser(request).getI_user();
		
		UserVO param = new UserVO();
		
		switch(type) {
		case "1": // 현재 비밀번호
			param.setUser_id(user_id);
			param.setUser_pw(encrypt_pw);
			int result = UserDAO.login(param);
			
			if(result == 1) {
				request.setAttribute("isAuth", true);
			} else {
				request.setAttribute("msg", "비밀번호를 확인해 주세요");
			}
			
			doGet(request, response);
			break;
			
		case "2":
			param.setUser_id(user_id);
			param.setUser_pw(encrypt_pw);
			param.setI_user(i_user);
			UserDAO.uptUser(param);
			
			response.sendRedirect("/profile?proc=1");
		}
	}

}
