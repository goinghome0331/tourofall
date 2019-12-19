<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/sign/signup.css"/>">
<title>TourOfAll에 가입하기</title>
</head>
<body>
	<sf:form class="form-signin img-rounded" method="post" commandName="userRegistrationForm">

		<h1 class="form-signin-heading" style="text-align: center"><a href="<c:url value="/"/>">Tour of all</a>에 가입하기</h1>
		<br>

		<div class="form-group">
			
			<c:if test="${userRegistrationForm.signInProvider == 'ordinary'}">			
				<sf:input id="userid" path="username" class="form-control idsize" placeholder="아이디" />
				<label for="userid" title="ID"></label> 
				<p class="idfont" style="color:red;">
					<sf:errors path="username" />
				</p>	
				<sf:password path="password" id="password" class="form-control pwsize" placeholder="비밀번호" />
				<label for="password" title="PASSWORD"></label>
				<p class="idfont" style="color:red;">
					<sf:errors path="password"></sf:errors>
				</p>
			
				<sf:password path="confirmPassword" id="confirmPassword" class="form-control pwsize" placeholder="비밀번호 재확인" />
				<label for="confirmPassword" title="PASSWORD-CHECK"></label> 
				<p class="idfont" style="color:red;">
					<sf:errors path="confirmPassword"></sf:errors>
				</p>
				<table>
					<tr>
						<td>
							<sf:input id="lastName" path="lastName" class="form-control namesize1" placeholder="성" />
							<label for="lastName" title="ID"></label>
						</td>
						<td>
							<sf:input id="firstName" path="firstName" class="form-control namesize2" placeholder="이름" />
							<label for="firstName" title="ID"></label>
						</td>
					</tr>
					<tr>
						<td>
							<p class="idfont" style="color:red;margin-top:-30px;">
								<sf:errors path="lastName"  /><br/>
								<sf:errors path="firstName" />
							</p>
						</td>
					</tr>
				</table>
				<hr>
				
				<label id="rightt"><sf:radiobutton size="12" id="man" path="gender" value="0"/> 남성</label>
				
				<label id="rightt"><sf:radiobutton id="woman" path="gender" value="1"/> 여성</label>
			</c:if>
    		<c:if test="${userRegistrationForm.signInProvider != 'ordinary'}">
    			<sf:hidden path="username"/>
    			<sf:hidden path="password"/>
    			<sf:hidden path="confirmPassword"/>
    			<sf:hidden path="lastName"/>
    			<sf:hidden path="firstName"/>
    			<sf:hidden path="gender"/>
    			<sf:hidden path="signInProvider"/>
    		</c:if>
			<p class="tffsize">생년월일</p>
			<table class="tmargin tfsize">
				<tr>
					<td>
						<sf:select path="year" style="height:40px;" class="form-control select90">
        					<c:forEach var="year" items="${years}">
        						<sf:option value="${year}" />
        					</c:forEach>
        				</sf:select>
        			</td>
        			<td>년</td>
        			<td>
        				<sf:select path="month" style="height:40px;" class='form-control select80'>
        					<c:forEach var="month" items="${months}">
        						<sf:option value="${month}" />
        					</c:forEach>
        				</sf:select>
					</td>
					<td>월</td>
					<td>
						<sf:select path="date" style="height:40px;" class='form-control select80'>
        					<c:forEach var="date" items="${dates}">
        						<sf:option value="${date}" />
        					</c:forEach>
        				</sf:select>
					</td>
					<td>일</td>
				</tr>
			</table>
			<hr>
			<p class="pcolor">관심있는 여행지 분야</p>
			<label class="labelmargin1030"><sf:checkbox path="userPreferences" value="A0101" />자연</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0201"/>역사</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0202"/>휴양</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0203"/>체험</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0204"/>산업</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0205"/>건축/조형물</label>
			<label class="labelmargin1030"><sf:checkbox path="userPreferences" value="A0206"/>문화시설</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0302"/>레포츠-육상</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0303"/>레포츠-수상</label>
			<label class="labelmargin10"><sf:checkbox path="userPreferences" value="A0304"/>레포츠-항공</label><br/>
			<p class="idfont" style="color:red;margin-left:30px;margin-top:5px;">
				<sf:errors path="userPreferences" />
			</p>
			<hr>
			<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
			<button class="btn btn-lg btn-primary btn-block buttonsize" type="submit">작성완료</button>

		</div>
	</sf:form>
</body>
</html>