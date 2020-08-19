<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.koreait.pjt.vo.BoardVO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	List<BoardVO> list = (List)request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	.list:hover { 
	background: darkgray; 
	margin: 0; padding: 0;
	cursor: pointer; 
	}
	
	td { border-collapse: collapse; }
</style>
</head>
<body>
	<div id="container">
		<div>
			<p>${loginUser.nm }님 환영합니다.</p>
			<p>게시판<br>리스트</p></div>
			<a href="regmod"><button>글쓰기</button></a></div>
			<table>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${list}" var="item">
				<tr class="list">
					<td>${item.i_board}</td>
					<td>${item.title}</td>
					<td>${item.hits}</td>
					<td>${item.i_user}</td>
					<td>${item.r_dt}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>