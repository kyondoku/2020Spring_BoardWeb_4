package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//프로필 화면(나의 프로필 이미지, 이미지 변경 가능한 화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int i_user = MyUtils.getIntParameter(request, "i_user");
		
		UserVO uv = new UserVO();
		uv.setUser_id(request.getParameter("user_id"));
		uv.setEmail(request.getParameter("email"));
		uv.setNm(request.getParameter("nm"));
		uv.setProfile_img(request.getParameter("profile_img"));
		uv.setR_dt(request.getParameter("r_dt"));
		uv.setI_user(i_user);
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "": searchText);
		
		int page = MyUtils.getIntParameter(request, "page");
		page = page == 0 ? 1 : page;
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		BoardDomain param = new BoardDomain();
		param.setI_user(MyUtils.getLoginUser(request).getI_user());
		param.setRecord_cnt(recordCnt);
		param.setSearchText("%"+searchText+"%");
		param.setSearchType(searchType);
		int pagingCnt = BoardDAO.selPagingCnt(param);
		
		if(page > pagingCnt) {
			page = pagingCnt;
		}
		request.setAttribute("page", page);
		
		request.setAttribute("item", UserDAO.selUser(i_user));
		request.setAttribute("list", BoardDAO.proSelBoardList(param));
		
		ViewResolver.forward("user/profile", request, response);
		
	}

	//이미지 변경 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		// 절대경로값/img/user/5
		String savePath = getServletContext().getRealPath("img") + "/user/" + loginUser.getI_user(); // 저장경로

		
		// 폴더 생성
		File directory = new File(savePath);
//			존재하면 true, 없으면 false
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		int maxFileSize = 10_485_760; // 1024 * 1024 * 10 (10mb) // 최대 파일 사이즈 크기
		String fileNm = "";
		String saveFileNm = "";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, savePath
					, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			System.out.println("mr : " +mr);
			
			Enumeration files = mr.getFileNames();
			
			if(files.hasMoreElements()) {
				String key = (String)files.nextElement();
				fileNm = mr.getFilesystemName(key);
				String ext = fileNm.substring(fileNm.lastIndexOf("."));
				saveFileNm = UUID.randomUUID() + ext;
				System.out.println("key: " + key);
				System.out.println("fileNm: " + fileNm);
				System.out.println("saveFileNm : " + saveFileNm);
				File oldFile = new File(savePath + "/" + fileNm);
				File newFile = new File(savePath + "/" + saveFileNm);
				oldFile.renameTo(newFile);
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(saveFileNm != null) { //DB에 프로필 파일명 저장
			UserVO param = new UserVO();
			param.setProfile_img(saveFileNm);
			param.setI_user(loginUser.getI_user());
			
			UserDAO.uptUser(param);
		}
		
		response.sendRedirect("/profile?i_user=" + loginUser.getI_user());
		
//		ViewResolver.forward("profile?i_user=" + i_user, request, response);
	}

}
