package com.koreait.pjt.user;

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
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		UserVO param = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		
		if(param == null) {
			ViewResolver.forward("/user/login", request, response);
		} else {
			response.sendRedirect("/board/list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		
		int result = UserDAO.login(param);
		
	     if(result != 1) { // 에러 처리
	         String msg = null;
	         switch(result) {
	         case 2:
	            msg = "비밀번호를 확인해주세요";
	            break;
	         case 3:
	            msg = "존재하지 않는 아이디입니다";
	            break;
	         default:
	            msg = "에러가 발생했습니다"; // DB의 상태가 좋지 못할 때 발생
	         }
	         request.setAttribute("user_id", user_id);
	         request.setAttribute("msg", msg);
	         doGet(request, response);
	         return;
	      }
	      // 정상적인 작동을 할 때
	      HttpSession hs = request.getSession();
	      hs.setAttribute(Const.LOGIN_USER, param);
	      
	      

	      System.out.println("로그인 성공!");
	      response.sendRedirect("/board/list");
	}

}
