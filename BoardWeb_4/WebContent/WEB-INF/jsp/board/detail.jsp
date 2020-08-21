<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	* {
		padding: 0;
		margin: 0;
	}
	
	a:hover { 
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
		display: flex;
		height: 80%;
		justify-content: center;
		align-items: center;	
	}
	
	.content {
		width: 620px;
		height: 400px;
	}
	
	.btnbox, .btnlist {
		text-align: right;	
		margin-top: 10px;
		margin-bottom: 10px;
	}
	
	a {
		color: black;
		text-decoration: none;
		padding: 4px;
		border: 1px solid gray;
	}
	
	#delFrm {
		display: inline;
	}
	
	.detail {
		padding: 20px;
		margin-bottom: 10px;
		box-sizing: border-box;
	}
	
	.date {
		display: inline;
	}
	
	.title {
		font-size: 24px;
		font-weight: bold;
	}
	
	.hits {
		text-align: right;
	}
	
</style>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="btnbox">
				<!-- 밑에거 $표시 있는건 세션에 있는거임, 세션은 로그인 성공할때 받는당 -->
				<c:if test="${loginUser.i_user == data.i_user }">
					<div class="date">최종수정일 : ${data.r_dt == data.m_dt ? data.r_dt : data.m_dt}</div>
					<a href="/board/regmod?i_board=${data.i_board}">수정</a>
					<form id="delFrm" action="/board/del" method="post">
						<input type="hidden" name="i_board" value="${data.i_board}">
						<a href="#" onclick="submitDel()">삭제</a>
					</form>
				</c:if>
			</div><hr>
			<div class="detail">
				<div class="hits">hits ${data.hits}</div>
				<div><span class="title">${data.title}</span> | ${data.nm}</div><br>
				<div>${data.ctnt} </div>
			</div><hr>
			<div class="btnlist">
				<a href="/board/list">리스트</a>
			</div>
		</div>
	</div>
	<script>
		function submitDel() {
			if(confirm('삭제 하시겠습니까?')){
				delFrm.submit()
			}
		}
	</script>
</body>
</html>