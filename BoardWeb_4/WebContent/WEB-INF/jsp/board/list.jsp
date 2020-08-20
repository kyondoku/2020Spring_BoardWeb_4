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
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	.list:hover { 
		background: #bdbdbd; 
		transition: 0.2s;
		color: black;
		font-weight: bold;
		margin: 0; padding: 0;
		cursor: pointer; 
	}
	
	html, body {
		font-family: 'Noto Sans KR', sans-serif;
		font-weight: 400;
		width: 100%;
		height: 100%;
		min-width: 600px;
	}
	
	#container { 
		display: flex;
		height: 80%;
		justify-content: center;
		align-items: center;	
	}
	
	.rsub {
		margin-left: 20px;
		height: 64%;

		text-align: right;
	}
	
	table {
		font-size: 13px;
		width: 500px;
		text-align: center;
		border-collapse: collapse;
		margin: 0;
	}
	
	th, td, tr {
		border: none;
	}
	
	.th {
		height: 40px;
	}
	
	.title {
		font-size: 30px;
		font-weight: 1000;
	}
	
	.button {
		background: white;
		width: 60px;
		height: 30px;
		font-size: 12px;
		border: 1px solid black;
	}
		
	td { border-collapse: collapse; }
</style>
</head>
<body>
	<div id="container">
		<div class="flex">
			<div class="lsub">
			<table>
				<tr class="th">
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${list}" var="item">
				<tr class="list" onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board}</td>
					<td>${item.title}</td>
					<td>${item.nm}</td>
					<td>${item.hits}</td>
					<td>${item.r_dt}</td>
				</tr>
				</c:forEach>
			</table><hr>
			<a href="regmod"><button class="button">글쓰기</button></a></div>
			</div>
			<div class="rsub">
				<p class="title">게시판<br>리스트</p>
				<p class="welcome"><span style="font-weight: bold">${loginUser.nm }</span>님<br>환영합니다</p>
			</div>
		</div>
	</div>
	<script>
		function moveToDetail(i_board) { 
			location.href = 'detail?i_board='+i_board	
		}
	</script>
</body>
</html>