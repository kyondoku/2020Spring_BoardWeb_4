<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	
	* {
		padding: 0;
		margin: 0;
	}
	
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

	.container {
		
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	.userinfo {

		display: flex;
		height: 40%;
		flex-direction: row;
		justify-content: center;
		align-items: center;	
		
	}
	
	.profileImg {
		padding-top: 20px;
		width: 220px;
		height: 220px;
	}
	
	table {
		font-size: 13px;
		width: 700px;
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
	
	.pic {
		margin-top: 20px;
		margin-right: 10px;
		
	}
	
	.info {
		border: 1px solid black;
		width: 350px;
		height: 248px;	
	}
	
	.info h2 {
		margin: 20px;
	}
	
	.info div {
		margin-left: 20px;
		margin-bottom: 14px;
	}
	
	.uldFile {
		width: 170px;
		border: none;
	}

	.infoBtn {
		margin-left: 200px;
		height: 40px;
	}

	.infoBtn a {
		text-decoration: none;
		color: black;
		width: 40px;
		height: 30px;
		border: 1px solid black;
		display: inline;
		text-align: right;
		margin-bottom: 5px;
		padding: 5px;
	}	
	
	.containerPImg {
		display: inline;
	}
	
	.pImg {
		width: 20px;
		height: 20px;
		border-radius: 50%;
		background-position: center;
	}
	
	.myList {
		
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
	
	.myListTitle {
		margin-bottom: 10px;
		margin-left: 10px;
		font-size: 24px;
		font-weight: 900;
	}
	
</style>
</head>
<body>
	<div class="container">
		<div class="userinfo">
			<div class="pic">
				<div>
					<c:choose>
						<c:when test="${item.profile_img != null}">
							<img class="profileImg" src="/img/user/${loginUser.i_user}/${item.profile_img }">
						</c:when>
						<c:otherwise>
							<img class="profileImg" src="/img/default_profile.jpg">
						</c:otherwise>
					</c:choose>
				</div>
				<div>
					<form action="/profile" method="post" enctype="multipart/form-data">
						<div>
							<label><input class="uldFile" type="file" name="profile_img" accept="image/*"></label>
							<input type="hidden" value="${item.i_user }">
							<input class="uldBtn" type="submit" value="업로드">
						</div>
					</form>
				</div>
			</div>
			<div>
				<div class="infoBtn">
					<a class="button" href="/changePw">비밀번호 변경</a>
					<a class="button" href="/board/list">뒤로</a>
				</div>
				<div class="info">
					<div>
						<h2><span class="name">${item.nm } </span>님의 프로필</h2>
						<div>ID : ${item.user_id }</div>
						<div>이름 : ${item.nm }</div>
						<div>이메일 : ${item.email }</div>
						<div>가입일시 : ${item.r_dt }</div>
					</div>
				</div>
			</div>	
		</div>
		<div class="myList">
			<table>
				<tr class="th">
					<th>No</th>
					<th style="width: 170px;">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>작성일</th>
				</tr>
				<p class="myListTitle">최근 작성한 게시물</p><hr>
				<c:forEach items="${list}" var="item">
				<tr class="list" onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board}</td>
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
			</table>
		</div>
	</div>
	<script>
		function moveToDetail(i_board) { 
			location.href = '/board/detail?page=${page}&record_cnt=${param.record_cnt}&searchType=${param.searchType}&searchText=${param.searchText}&i_board=' + i_board
		} 
		
		var proc = '${param.proc}'
		switch(proc) {
		case '1':
			alert('비밀번호를 변경하였습니다.')
			break
		}
	</script>
</body>
</html>