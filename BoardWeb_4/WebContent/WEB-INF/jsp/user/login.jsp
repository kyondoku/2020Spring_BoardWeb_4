<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	#err { color: red; }
</style>
</head>
<body>
	<h1>로그인</h1>
	<div>
		<form id="frm" action="/login" method="post" onsubmit="return chk()">
			<div><input type="text" name="user_id" value="${user_id}" placeholder="아이디"></div>
			<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
			<div id="err">${msg}</div>
			<div><input type="submit" value="로그인"></div>
		</form>
		<a href="/join"><button>회원가입</button></a>
	</div>
	<script>
		function chk() {
			if(frm.user_id.value.length < 5) {
				alert('아이디는 5글자 이상이 되어야 합니다.')
				frm.user_id.focus()
				return false
				
			} else if(frm.user_pw.value.length < 5) {
				alert('비밀번호는 5글자 이상이 되어야 합니다.')
				frm.user_pw.focus()
				return false
				}
			} 
	</script>
</body>
</html>