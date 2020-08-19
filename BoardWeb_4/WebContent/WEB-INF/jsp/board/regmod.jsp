<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.err { color: red; }
</style>
<title>등록/수정</title>
</head>
<body>
	<div class="err">${msg}</div>
	<form id="frm" action="regmod" method="post">
		<div>제목: <input type="text" name="title"></div>
		<div>내용: <textarea name="ctnt"></textarea></div>
		<div><input type="submit" value="작성완료"></div>	
	</form>
</body>
</html>