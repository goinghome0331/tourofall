<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/header.css"/>">
<div>
<!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand " href="<c:url value="/"/>">
                    <i class="fa fa-play-circle"></i>
                </a>
                <div class="mobile-center">
                	<div class="hidden-sm hidden-md hidden-lg">
                		<a class="page-scroll" href="<c:url value="/"/>">TourOfAll</a>
                	</div>
                </div>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
			
            <div class="collapse navbar-collapse navbar-main-collapse">
            	<ul class="nav navbar-nav">
            		<li class="hidden">
                        <a href="#page-top"></a>
                    </li>
            		<li>
                    	<a id="recommend" class="page-scroll" href="<c:url value="/recommend"/>">추천여행지</a>
                    </li>
          			<li>
                    	<a id="evalmore" class="page-scroll" href="<c:url value="/eval/evalmore"/>">추천더하기</a>
                    </li>
                    <li class="hidden-sm hidden-md hidden-lg">
                    	<a class="page-scroll" href="<c:url value="/search"/>">검색하기</a>
                    </li>
                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                    	<li class="hidden-sm hidden-md hidden-lg">
	 						<a class="page-scroll" href="<c:url value="/signin"/>">로그인</a>
	 					</li>
	 					<li class="hidden-sm hidden-md hidden-lg">
	 						<a class="page-scroll" href="<c:url value="/signup"/>">회원가입</a>
	 					</li>
	 				</c:if>
	 				<c:if test="${pageContext.request.userPrincipal.name != null}">
	 					<sec:authentication var="user" property="principal"/>
      					<li class="hidden-sm hidden-md hidden-lg">
							<a class="page-scroll" href="<c:url value="/users/${user.id}"/>">내정보</a>
						</li>
	 				</c:if>
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                	
                </ul>
                
                <div class="visible-sm-block visible-md-block visible-lg-block clearfix">
                <div class="nav navbar-nav navbar-right">
                	<c:if test="${pageContext.request.userPrincipal.name == null}">
                		<li class="dropdown"><a id ="myinfo" class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-vcard"></i><span class="caret"></span></a>
        					<ul class="dropdown-menu">
          							<li>
	 									<a class="page-scroll" href="<c:url value="/signin"/>">로그인</a>
	 								</li>
	 								<li>
	 									<a class="page-scroll" href="<c:url value="/signup"/>">회원가입</a>
	 								</li>
        					</ul>
      					</li>
	 				</c:if>
	 				<c:if test="${pageContext.request.userPrincipal.name != null}">
	 				<sec:authentication var="user" property="principal"/>
	 				<li class="dropdown"><a id ="myinfo" class="dropdown-toggle" data-toggle="dropdown" href="#">${user.lastName}${user.firstName}<i class="fa fa-user-circle"></i><span class="caret"></span></a>
        					<ul class="dropdown-menu">
        						
        						<li>
	 								<a class="page-scroll" href="<c:url value="/users/${user.id}"/>">내정보</a>
		 						</li>
		 						<li>
	 								<a class="page-scroll" href="<c:url value="/signout"/>">로그아웃</a>
	 							</li>
        					</ul>
	 				</c:if>
                </div>
                
             	<div class="navbar-center">
             		<form class="navbar-form" action="<c:url value="/search"/>" method="get">
      					<div class="input-group">
        					<input type="text" class="form-control" placeholder="Search" name="s" value="${query}">
        					<div class="input-group-btn">
          						<button class="btn btn-default" type="submit">
            						<i class="glyphicon glyphicon-search"></i>
          						</button>
        					</div>
			       		</div>
    				</form>   
    			</div>
    			</div>
            </div>
            
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/header/common-header.js"/>"></script>