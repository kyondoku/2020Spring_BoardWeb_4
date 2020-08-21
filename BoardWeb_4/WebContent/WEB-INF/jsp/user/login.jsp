<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

	#err { 
		text-align: center;
		color: red;
		font-size: 12px;
		margin: 10px;
		}
	
	html, body {
		font-family: 'Noto Sans KR', sans-serif;
		font-weight: 400;
		width: 100%;
		height: 100%;
		min-width: 900px;
	}
	
	.container { 
		display: flex;
		height: 80%;
		justify-content: center;
		align-items: center;
	}
	
	.loginspace {
		
		width: 160px;
		height: 20px;
		text-align: center;
		font-size: 10px;
	}
		
	.logincontainer {
		margin-top: 5px;
		width: 350px;
		padding: 60px;
		border: 1px solid black;
	}
	
	.logincontainer h2 {
		font-size: 60px;
		margin-top: 0;
		margin-bottom: 0;
		text-align: center;
	}
	
	.loginletter {
		font-size: 10px;
		float: left;
		width: 130px;
		
	}
	
	.loginbox {
		float: right;
		width: 180px;
	}
	
	.btncontainer {
		margin-top: 6px;
	}
	
	.btnjoin, .btnlogin {
		width: 80px;
		height: 30px;
		font-size: 12px;
		background: white;
		border: 1px solid gray;
	} 
	
	.btnjoin {
		margin-left: 4px;
	}
	
</style>
</head>
<body>
	<div class="container">
		<div class="logincontainer">
		<div class="loginletter">
			<h2>💁‍♂️</h2>
		</div>
		<div class="loginbox">
			<form id="frm" action="/login" method="post" onsubmit="return chk()">
				<div><input class="loginspace" type="text" name="user_id" value="${user_id}" autofocus placeholder="아이디를 입력하세요"></div>
				<div><input class="loginspace" type="password" name="user_pw" placeholder="패스워드를 입력하세요"></div>
				<div id="err">${msg}</div>
			</form>
				<div class="btncontainer">
					<input class="btnlogin" type="button" onclick="login()" value="LOGIN">
					<a href="/join"><button class="btnjoin">JOIN</button></a>
				</div>
		</div>	
		</div>
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
		
		function login() {
			frm.submit();
		}
	</script>
</body>
</html>