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
	
	.button:hover {
		background: #bdbdbd; 
		transition: 0.2s;
		color: black;
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
		height: 1200px;
		display: flex;
		justify-content: center;
	}
	.lsub {
		margin-top: 100px;
	}
	.rsub {
		margin-top: 100px;
		margin-left: 30px;
		text-align: left;
	}
	
	table {
		font-size: 13px;
		width: 600px;
		text-align: center;
		border-collapse: collapse;
		margin-bottom: 20px;
	}
	
	th, td, tr {
		border: none;
		font-size: 16px;
		height: 28px;
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
		width: 70px;
		height: 30px;
		font-size: 14px;
		border: 1px solid black;
		margin-left: 5px;
	}

	.emoji {
		text-align: center;
		font-size: 30px;
	}
	
	.welcome a {
		color: black;
		text-decoration: none;
	}
		
	.fontCenter {
		width: 200px;
		height: 50px;
		margin-left: 200px;
		text-align: center;
	}
	
	.pagingFont {
		float: left;
		margin: 3px;
		width: 20px;
		height: 20px;
		border: 1px solid black;
		font-size: 12px;
		line-height: 20px;
		text-align: center;
	}
	.pagingFont a {
		
		color: black;
		text-decoration: none;
	}
		
	
	.thisPage { 
		cursor: default;
		font-weight: bold;
		color: navy;
	}
	
	.selRecord {
		margin-right: 10px;
		margin-bottom: 5px;
		text-align: right;
	}
	
	td { border-collapse: collapse; }
	
	.selRecord select {
		height: 25px;
		border-radius: 0;
	}
	
	.searchCtr {
		text-align: center;
	}
	
	.pImg {
		width: 20px;
		height: 20px;
		border-radius: 50%;
		background-position: center;
	}
	
	.containerPImg {
		display: inline;
	}

	.iLike {
		font-size: 10px;
	}
	
	.iLikeVer {
		padding-bottom: 5px;
	}
	
	.highlight {
		font-weight: bold;
	}
</style>
</head>
<body>
	<div id="container">
		<div class="flex">
			<div class="lsub">
				<div class="selRecord">
					<form id="selFrm" action="/board/list" method="get">
						<input type="hidden" name="page" value="${page}">
						<input type="hidden" name="searchText" value="${param.searchText }">
						<select name="record_cnt" onchange="changeRecordCnt()">
							<c:forEach begin="10" end="50" step="10" var="item">
								<c:choose>
									<c:when test="${param.record_cnt == item}">
										<option value="${item }" selected>${item }개</option>
									</c:when>
									<c:otherwise>
										<option value="${item }">${item }개</option>							
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</form>
				</div>
				<table>
					<tr class="th">
						<th>No</th>
						<th> </th>
						<th style="width: 200px;">제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>작성일</th>
					</tr><hr>
					<c:forEach items="${list}" var="item">
					<tr class="list" onclick="moveToDetail(${item.i_board})">
						<td>${item.i_board}</td>
						<td class="iLikeVer">
							<c:choose>
								<c:when test="${item.yn_like == 1}">
									<span class="iLike"> ❤️ </span>
								</c:when>
								<c:otherwise>
									 
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							${item.title} (${item.cmt_cnt })
						</td>
						<td>
							<div class="containerPImg">
								<c:choose>
									<c:when test="${item.profile_img != null}">
										<img class="pImg" src="/img/user/${item.i_user}/${item.profile_img }">
									</c:when>
									<c:otherwise>
										<img class="pImg" src="/img/default_profile.jpg">
									</c:otherwise>
								</c:choose>
							</div>
						${item.nm}</td>
						<td>${item.hits}</td>
						<td>${item.like_cnt }</td>
						<td>${item.r_dt == item.m_dt ? item.r_dt : item.m_dt}</td>
					</tr>
					</c:forEach>
				</table><hr>
				<a href="/board/regmod?page=${item}&record_cnt=${param.record_cnt}&searchType=${searchType}&searchText=${param.searchText}"><button class="button">글쓰기</button></a></div>
				<div class="fontCenter">
					<c:forEach var="item" begin="1" end="${pagingCnt}">
						<div class="pagingFont">
						<c:choose>
							<c:when test="${item==page || (page == null && item == 1)}">
								<span class="thisPage">${item}</span>
							</c:when>
							<c:otherwise>
								<a id="page" href="/board/list?page=${item}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&searchType=${searchType}">${item}</a>
							</c:otherwise>
						</c:choose>
						</div>
					</c:forEach>	<!-- 까먹지 않게 조심 -->
				</div>
				<div class="searchCtr">
					<form action="/board/list">
						<select name="searchType">
							<option value="a" ${searchType == 'a' ? 'selected' : ''}>제목</option>
							<option value="b" ${searchType == 'b' ? 'selected' : ''}>내용</option>
							<option value="c" ${searchType == 'c' ? 'selected' : ''}>제목+내용</option>
						</select>
						<input type="search" name="searchText" value="${param.searchText }">
						<input type="submit" value="검색">
					</form>
				</div>
			</div>
			<div class="rsub">
				<p class="title">게시판<br>리스트</p>
				<div><p class="welcome"><a href="/profile?i_user=${loginUser.i_user }&searchType=${searchType}&searchText=${param.searchText}&page=${page}&record_cnt=${param.record_cnt}" style="font-weight: bold">${loginUser.nm }</a>님<br>환영합니다</p></div> 
				<div><button class="button" onclick="check()">로그아웃</button></div>
				<div class="emoji">🙋‍♂️</div>
			</div>
		</div>
	<script>
		function changeRecordCnt() {
			selFrm.submit()
		}
	
		function moveToDetail(i_board) { 
			location.href = '/board/detail?page=${page}&record_cnt=${param.record_cnt}&searchType=${searchType}&searchText=${param.searchText}&i_board=' + i_board
		} 
		
		function check(){
			if(confirm('로그아웃 하시겠습니까?')){
				location.href = '/logout'
			}
		}
	</script>
</body>
</html>