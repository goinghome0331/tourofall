<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/sign/signin.css"/>">
<title>Tour of all에 로그인</title>
</head>
<body>
	<div class="form-signin img-rounded">
		<form action="<c:url value="/signin/authenticate"/>" method="post">
			<c:if test="${not empty signoutMsg}">
        		<p style="color:blue;text-align:center;font-size:3rem;">${signoutMsg}</p>
        	</c:if>
			<h1 class="form-signin-heading" style="text-align: center;"><a href="<c:url value="/"/>">Tour Of All</a></h1>
			<img class="img-circle" src="https://lh3.googleusercontent.com/-N2dyACoEm4U/AAAAAAAAAAI/AAAAAAAAAAA/yFLJFGuSbZk/photo.jpg">

			<div class="form-group has-feedback has-feedback-left">
				<div class="icon-addon addon-md">
					<input id="userid" type="text" name="username" class="form-control" placeholder="아이디를 입력해주세요." />
					<label for="userid" class="glyphicon glyphicon-user"  title="ID"></label>
				</div>
				<div class="icon-addon addon-md">
					<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력해주세요." required>
					<label for="password" class="glyphicon glyphicon-lock" title="PASSWORD"></label>
				</div>
				<c:if test="${not empty errorMsg}">
        			<p style="color:red">${errorMsg}</p>
        		</c:if>

				<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
				<button class="btn btn-lg btn-default btn-block buttonsize1" type="submit"><span>로그인</span>
				</button>
			</div>
		</form>

		<form action="<c:url value="/signin/facebook"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
				<button class="btn btn-lg btn-primary btn-block buttonsize2 fa fa-facebook-official" type="submit">
					<span>Facebook 계정으로 로그인</span>
				</button>
				<ul>
					<li><a href="<c:url value="/signup"/>">회원가입</a>
					
				</ul>
		</form>
	</div>
</body>
</html>