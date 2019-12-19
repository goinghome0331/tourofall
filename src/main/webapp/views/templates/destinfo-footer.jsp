<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
<div>
	<table>
		<tr>
			<th><h3>리뷰</h3></th>
			<c:if test="${reviewWrite}">
				<td><a href="<c:url value="/review/write/${itemTypeId}/${itemId}"/>">리뷰 작성하기</a></td>
			</c:if>
		</tr>
		<c:forEach var="reviewRenderingModel" items="${reviewRenderingModels}">
			<tr>
				<td>
					<h3>제목 : ${reviewRenderingModel.title}</h3>
					<p>작성자 : <a href="<c:url value="/users/${reviewRenderingModel.userId}"/>">${reviewRenderingModel.lastName} ${reviewRenderingModel.firstName}님</a></p>
					<p>작성일자 :
						${reviewRenderingModel.createdDate.year+1900}년${reviewRenderingModel.createdDate.month+1}월${reviewRenderingModel.createdDate.date}일</p>
					<p>평점 : ${reviewRenderingModel.score}</p>
					<p>내용 : ${reviewRenderingModel.content}</p>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<th><h3>QnA</h3></th>
			<td><a href="<c:url value="/qna/question/write/${itemTypeId}/${itemId}"/>">질문 작성하기</a></td>
		</tr>
		<tr>
			<c:forEach var="questionRenderingModel" items="${questionRenderingModels}">
				<tr>
					<td>${questionInfo.id}</td>
					<td><a href="<c:url value="/qna/question/${questionRenderingModel.questionId}"/>">${questionRenderingModel.title}</a></td>
					<td><a href="<c:url value="/users/${questionRenderingModel.userId}"/>">${questionRenderingModel.lastName} ${questionRenderingModel.firstName}님</a></td>
					<td>${questionRenderingModel.visitor}</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</div>	

 -->