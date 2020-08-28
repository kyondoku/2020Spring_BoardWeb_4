<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="com.koreait.pjt.vo.BoardCmtVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	* {
		padding: 0;
		margin: 0;
	}
	
	.btn:hover { 
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
	
	.btn {
		background: white;
		color: black;
		text-decoration: none;
		margin-top: 10px;
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
	
	.pointerCursor {
		cursor: pointer;
		text-align: right;
		color: red;
		margin-left: 560px;
		width: 10px;
	}
	
	.profile {
		text-decoration: none;
		color: black;
	}
	
	.cmtbox {
		margin-top: 30px;
	}
	
	.cmt {
		width: 600px;
		height: 100px;
	}
	
	.inscmtbox {
		display: flex;
		justify-content: space-between;
		
		width: 620px;
	}
	
	.linscmtbox {
		margin-top: 30px;
		margin-bottom: 30px;
		line-height: 60px;
		text-align: center;
		width: 150px;
		height: 60px;
	}
	
	.rinscmtbox {
		display: flex;
		margin-top: 30px;
		width: 460px;
	}
	
	.rinscmtbox2 {
		margin-top: 10px;
		margin-left: 20px;
	}
	
	
	.inscmt {
		padding: 10px;
		width: 310px;
		height: 40px;
	}
	
	.form {
		display: hidden;
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
					<a class="btn" href="/board/regmod?page=${item}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&i_board=${data.i_board}">수정</a>
					<form id="delFrm" action="/board/del" method="post">
						<input type="hidden" name="i_board" value="${data.i_board}">
						<a class="btn" href="#" onclick="submitDel()">삭제</a>
					</form>
				</c:if>
				<a class="btn" href="/board/list?page=${param.page }&record_cnt=${param.record_cnt}&searchText=${param.searchText}">리스트</a>
			</div><hr>
			<div class="detail">
				<span class="pointerCursor" onclick="toggleLike(${data.yn_like})">
					<c:if test="${data.yn_like == 0}">
						<span class="material-icons">favorite_border</span>
					</c:if>
					<c:if test="${data.yn_like == 1}">
						<span class="material-icons">favorite</span>
					</c:if>
				</span>
				<div class="hits">hits ${data.hits}</div>
				<div><span class="title">${data.title}</span> | 
					<a class="profile" href="/board/profile?i_user=${data.i_user}">${data.nm}</a></div><br>
				<div>${data.ctnt} </div>
			</div><hr>
			<div>
				<form id="cmtFrm" action="/board/cmt" method="post">
					<input type="hidden" name="i_cmt" value="0">
					<input type="hidden" name="i_board" value="${data.i_board}">
					<div class="inscmtbox">
						<div class="linscmtbox">
							${loginUser.getNm()}
						</div>
						<div class="rinscmtbox">
							<div class="rinscmtbox1">
								<input class="inscmt" type="text" name="cmt" value="${item.cmt}" placeholder="댓글을 입력하세요" autofocus><br>
							</div>
							<div class="rinscmtbox2">
								<input type="submit" class="btn" value="등록">
								<input type="button" class="btn" value="취소" onclick="clkCmtCancel()">
							</div>
						</div>
					</div><hr>
				</form>
			</div>
			<div class="cmtbox">
				<c:forEach items="${list}" var="item">
					<div class="cmt">
						<p>${item.nm} | <span>${item.r_dt == item.m_dt ? item.r_dt : item.m_dt}</span>
							<br>${item.cmt}	
						</p>
						<c:if test="${loginUser.i_user == item.i_user }">
							<form action="/board/cmt" method="post">
								<input type="hidden" name="i_cmt" value="${item.i_cmt}">					
							</form>
							<button class="btn" onclick="clkCmtMod(${item.i_cmt},'${item.cmt}')">수정</button>
							<button class="btn" onclick="clkCmtDel(${item.i_cmt})">삭제</button>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script>
		function clkCmtCancel() {
			cmtFrm.i_cmt.value = 0
			cmtFrm.cmt.value = ''
			cmtSubmit.value = '전송'
		}
	
		function clkCmtMod(i_cmt, cmt) {
			console.log('i_cmt: ' + i_cmt)
			
			cmtFrm.i_cmt.value = i_cmt
			cmtFrm.cmt.value = cmt
			
			cmtSubmit.value = '수정'
		}
	
		function submitDel() {
			if(confirm('삭제 하시겠습니까?')){
				delFrm.submit()
			}
		}
		
		function clkCmtDel(i_cmt) {
			if(confirm('삭제 하시겠습니까?')){
				location.href='/board/cmt?&i_board=${data.i_board}&i_cmt=' + i_cmt
			}
		}
		
		
		function toggleLike(yn_like) {
			location.href='/board/toggleLike?i_board=${data.i_board}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&yn_like=' + yn_like			
		}
	</script>
</body>
</html>