<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/destination/menubar.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/small-header.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/destination/menubar.js"/>"></script>
<div class="empty-header">
</div>
<div class="menu-container">
	<div  id="menubar" class="menubar">	
		<a  id= "default" onclick = "openDestInfo(event, 'basicinfo')" class="menu-item" href="#">개요</a>
		<a  onclick = "openDestInfo(event, 'introinfo')" class="menu-item" href="#">소개</a>
		<a  onclick = "openDestInfo(event, 'detailinfo')" class="menu-item" href="#">상세 정보</a>
		<a  onclick = "openDestInfo(event, 'imageinfo')" class="menu-item" href="#">이미지</a>
	</div>
</div>

