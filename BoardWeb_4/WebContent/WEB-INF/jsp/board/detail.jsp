<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>
	<div>
		<a href="/board/list">리스트</a>
		<!-- 밑에거 $표시 있는건 세션에 있는거임, 세션은 로그인 성공할때 받는당 -->
		<c:if test="${loginUser.i_user == data.i_user }">
			<a href="/board/regmod?i_board=${data.i_board}">수정</a>
			<form id="delFrm" action="/board/del" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">
				<a href="#" onclick="submitDel()">삭제</a>
			</form>
		</c:if>
	</div>
	<div>제목 : ${data.title}</div>
	<div>작성자 : ${data.nm} </div>
	<div>내용 : ${data.ctnt} </div>
	<div>작성일시 : ${data.r_dt}</div>
	<div>조회수 : ${data.hits}</div>
	<script>
		function submitDel() {
			if(confirm('삭제 하시겠습니까?')){
				delFrm.submit()
			}
		}
	</script>
</body>
</html>