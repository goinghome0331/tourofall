<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div style="margin-left: 50px; margin-right: 50px;">
	<div class="well">
		<sf:form class="form-horizontal" action="${pageContext.request.contextPath}/myinfo/update" commandName="userModificationForm">
			<sf:hidden path="id"/>
			<sf:hidden path="username"/>
			<c:if test="${userModificationForm.signInProvider == null}">
				<label class="control-label col-sm-3">아이디</label>
				<div class="col-sm-7">
					<p>${userModificationForm.username}</p>
				</div>
			</c:if>
			
			<div class="form-group">
				<label class="control-label col-sm-3" for="lastName">성</label>
				<div class="col-sm-3">
					<sf:input path="lastName" class="form-control" id="lastName"
						placeholder="last name" />
				</div>
				<label class="control-label col-sm-1" for="firstName">이름</label>
				<div class="col-sm-3">
					<sf:input path="firstName" class="form-control" id="firstName"
						placeholder="first name"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="lastName">성별</label>
				<div class="col-sm-3">
					<sf:radiobutton path="gender" value="0" />남성
				</div>
				<div class="col-sm-3">
					<sf:radiobutton path="gender" value="1" />여성
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-3" for="lastName">생년월일</label>
				<div class="col-sm-2">
					<sf:select path="year">
						<c:forEach var="year" items="${years}">
        					<sf:option value="${year}" />
        				</c:forEach>
					</sf:select>년
				</div>
				<div class="col-sm-2">
					<sf:select path="month">
						<c:forEach var="month" items="${months}">
        					<sf:option value="${month}" />
        				</c:forEach>
					</sf:select>월
				</div>
				<div class="col-sm-2">
					<sf:select path="date">
						<c:forEach var="date" items="${dates}">
        					<sf:option value="${date}" />
        				</c:forEach>
					</sf:select>일
				</div>

			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-3" for="existingPassword">사용자 선호 여행지 유형</label>
				<div class="col-sm-7">
					<sf:checkbox path="userPreferences" value="A0101" id = "nature" /><label id = "nature" >자연</label>
					<sf:checkbox path="userPreferences" value="A0201" id = "history" /><label id = "history" >역사</label>
					<sf:checkbox path="userPreferences" value="A0202" id = "resort" /><label id = "resort" >휴양</label>
					<sf:checkbox path="userPreferences" value="A0203" id = "experience" /><label id = "experience" >체험</label>
					<sf:checkbox path="userPreferences" value="A0204" id = "industry" /><label id = "industry" >산업</label>
					<sf:checkbox path="userPreferences" value="A0205" id = "sculpture" /><label id = "sculpture" >건축/조형물</label>
					<sf:checkbox path="userPreferences" value="A0206" id = "culture" /><label id = "culture" >문화시설</label>
					<sf:checkbox path="userPreferences" value="A0302" id = "leports-ground" /><label id = "leports-ground" >레포츠-육상</label>
					<sf:checkbox path="userPreferences" value="A0303" id = "leports-water" /><label id = "leports-water" >레포츠-수상</label>
					<sf:checkbox path="userPreferences" value="A0304" id = "leports-air" /><label id = "leports-air" >레포츠-항공</label>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="existingPassword">기존비밀번호</label>
				<div class="col-sm-7">
					<sf:password path="existingPassword" class="form-control" id="existingPassword"
						placeholder="Existing password" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-3" for="newPassword">새 비밀번호</label>
				<div class="col-sm-7">
					<sf:password path="newPassword" class="form-control" id="newPassword"
						placeholder="New password" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-3" for="newConfirmPassword">새 비밀번호 확인</label>
				<div class="col-sm-7">
					<sf:password path="newConfirmPassword" class="form-control" id="newConfirmPassword"
						placeholder="new Confirm password"/>
				</div>
			</div>
			<div style="text-align:center;">
				<input type="submit" class="btn btn-default" value="변경하기"/>
			</div>
		</sf:form>
	</div>
</div>