<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.err { color: red; }
	
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	
	* {
		padding: 0;
		margin: 0;
	}
	
	html, body {
		font-family: 'Noto Sans KR', sans-serif;
		font-weight: 400;
		width: 100%;
		height: 100%;
		min-width: 600px;
	}
	
	.submit {
		color: black;
		text-decoration: none;
		font-size: 16px;
		padding: 4px;
		background: white;
		margin-left: 548px;
		margin-top: 16px;
		border: 1px solid gray;
	}
	
	.list:hover, .submit:hover { 
		background: #bdbdbd; 
		transition: 0.2s;
		color: black;
		cursor: pointer; 
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
	
	.title {
		padding-left: 20px;
		width: 600px;
		border: none;
		height: 34px;
		font-size: 18px;
		margin-bottom: 14px;
	}
	
	.listdiv {
		text-align: right;
	}
	
	.list {
		margin-bottom: 20px;
		background: white;
		font-size: 16px;
		border: 1px solid gray;
		padding: 5px;
		
	}
	
	textarea {
		
		font-family: 'Noto Sans KR', sans-serif;
		font-weight: 400;
		font-size: 18px;
		width: 580px;
		height: 300px;
		padding: 20px;
		margin-bottom: 14px;
	}
	
	
</style>
<title>등록/수정</title>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="err">${msg}</div>
			<div class="listdiv"><a href="/board/list?page=${param.page }&record_cnt=${param.record_cnt}&searchText=${param.searchText}"><button class="list">리스트</button></a></div>
			<form id="frm" action="regmod" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">
				<div><input class="title" type="text" name="title" value="${data.title}" placeholder="제목을 입력하세요" autofocus ></div>
				<div><textarea name="ctnt" placeholder="내용을 입력하세요">${data.ctnt}</textarea></div><hr>
				<div><input class="submit" type="submit" value="${data == null ? '등록완료' : '수정완료'}"}></div>
			</form>
		</div>
	</div>
</body>
</html>