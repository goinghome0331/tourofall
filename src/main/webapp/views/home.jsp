<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common/page-structure.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header/slide-intro.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home/home.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/big-header.js"/>"></script>
<div>
	<div id="todayCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#todayCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#todayCarousel" data-slide-to="1"></li>
			<li data-target="#todayCarousel" data-slide-to="2"></li>
		</ol>

    <!-- Wrapper for slides -->
		<div class="carousel-inner carousel-custom-inner" role="listbox">
			<c:forEach var="todayDestinationRenderingModel" items="${todayDestinationRenderingModels}" varStatus="status">
				<c:if test="${status.index == 0}">
					<div class="item active">
						<img src="${todayDestinationRenderingModel.imageUrl}" alt="" width="460" height="345">
       					<div class="carousel-custom-caption">
       						<h3>오늘의 ${todayDestinationRenderingModel.destinationType}</h3>
       						<p class="lead"><a href="<c:url value="/dest/info/${todayDestinationRenderingModel.itemId}"/>" >${todayDestinationRenderingModel.title}</a></p>
       						<p>
       							주소 : ${todayDestinationRenderingModel.address}<br/>
								평균 평점 : ${todayDestinationRenderingModel.meanScore}
							</p>
       					</div>
       				</div>
				</c:if>
				<c:if test="${status.index != 0}">
					<div class="item">
						<img src="${todayDestinationRenderingModel.imageUrl}" alt="" width="460" height="345">
       					<div class="carousel-custom-caption">
       						<h3>오늘의 ${todayDestinationRenderingModel.destinationType}</h3>
       						<p class="lead"><a href="<c:url value="/dest/info/${todayDestinationRenderingModel.itemId}"/>" >${todayDestinationRenderingModel.title}</a></p>
       						<p>
       							주소 : ${todayDestinationRenderingModel.address}<br/>
								평균 평점 : ${todayDestinationRenderingModel.meanScore}
							</p>
       					</div>
       				</div>
				</c:if>
			</c:forEach>		
		</div>

    		<!-- Left and right controls -->
    	<a class="left carousel-control" href="#todayCarousel" role="button" data-slide="prev">
      		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
     		<span class="sr-only">Previous</span>
    	</a>
    	<a class="right carousel-control" href="#todayCarousel" role="button" data-slide="next">
   			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
   			<span class="sr-only">Next</span>
 		</a>
  	</div>
	
	<section class="page-container page-container-ready" >
		<div class="segment">
			<div class="container-fluid">
				<article class="intro">
					<h2 class="segment-heading">Best 여행지</h2>
					<div class="rank-container">
						<div class="rank-list-container">
							<ul class="rank-list" style="margin-top: 0px;transform: translate3d(0px, 0px, 0px);">
								<c:forEach var="bestDestinationRenderingModel" items="${bestDestinationRenderingModels}" varStatus="status">
									<li class="rank-item">
										<a href="<c:url value="/dest/info/${bestDestinationRenderingModel.itemId}"/>" class="rank-item-imagecard">
											<div class="rank-item-imagecard-image" style="background-image: url('${bestDestinationRenderingModel.imageUrl}');">
												<div class="rank-item-imagecard-content">
													<div class="rank-item-imagecard-marker">${status.index + 1}</div>
														<p class="rank-item-imagecard-subtitle">
															${bestDestinationRenderingModel.description}
														</p>
														<h3 class="rank-item-imagecard-title">
															${bestDestinationRenderingModel.title}
														</h3>
												</div>
											</div>
										</a>
									</li>								
								</c:forEach>
							</ul>
						</div>
						<div class="rank-navigation">
							<button class="rank-less" disabled>
								<i class="rank-less-icon rank-icon-left">&lt</i>
							</button>
							<button class="rank-more" disabled>
								<i class="rank-more-icon rank-icon-right">&gt</i>
							</button>
						</div>
					</div>						
				</article>
			</div>
		</div>
	</section>
</div>