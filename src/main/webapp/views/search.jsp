<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/search/search.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/small-header.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/evaluation/eval-reco-common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/search/search.js"/>"></script>
<div>
	<div class="js-result-section result-section result-section-hide">
		<div class="result-section-text">
		</div>
	</div>
	<div class="empty-header">
	</div>
	<div class="row-content">
		<div class="row-inner">
			<div class="search-results">
				<form class="search-form" action="<c:url value="/search"/>" method="get">
					<label class="search-label search-heading copy-h5" >여행지를 입력하세요</label>
					<input class="search-query" name= "s" value="${query}"/>
					<button class="search-submit icon-search"><i class="fa fa-search search-submit-icon"></i></button>
				</form>
				<h2 class="search-heading copy-h5">
					총 결과(
					<c:choose>
						<c:when test="${searchResultRenderingModelsSet.totalCount != 0}">
							${searchResultRenderingModelsSet.totalCount}
						</c:when>
						<c:otherwise>
							0
						</c:otherwise>
					</c:choose>	
					)
				</h2>
				<div class="search-result-list">
					<c:choose>
						<c:when test="${searchResultRenderingModels.totalCount != 0}">
							<c:forEach var="searchResultRenderingModel" items="${searchResultRenderingModelsSet.searchResultRenderingModels}" varStatus="status">
								<sf:form name="eval-form" action = "${pageContext.request.contextPath}/eval/evalmore" modelAttribute="searchResultRenderingModel${status.index}">
									<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
									<input type="hidden" name="itemId" value="${searchResultRenderingModel.itemId}">	
								<div class="card search-result copy-body">
										<div class="search-result-wrapper">
											<div class="link-wrapper">
											<a href="<c:url value="/dest/info/${searchResultRenderingModel.itemId}"/>">
												<div class="media-img search-result-figure">
													<img class= "search-result-figure-image is-wider" alt="" src="${searchResultRenderingModel.imageUrl}">
												</div>
											</a>
											<div class="search-result-meta search-result-meta-overflow media-body">
												<span class="search-result-type copy-caption">여행지</span>
												<a href="<c:url value="/dest/info/${searchResultRenderingModel.itemId}"/>">
													<h3 class="search-result-title copy-h1">${searchResultRenderingModel.title}</h3>
													<p>
														${searchResultRenderingModel.address}
													</p>
												</a>
											</div>
											<c:if test="${pageContext.request.userPrincipal.name != null}">
											<div class="rating-title">
												<span class="rating-title-content">
													평점을 주세요
												</span>
											</div>
											<div class="rating">
												
												<sf:radiobutton path="score" id="star5-${status.index}" value="5"/><label class = "full" for="star5-${status.index}" title="Awesome - 5 stars"></label>
												<sf:radiobutton path="score" id="star4half-${status.index}" value="4.5"/><label class="half" for="star4half-${status.index}" title="Pretty good - 4.5 stars"></label>
												<sf:radiobutton path="score" id="star4-${status.index}" value="4"/><label class = "full" for="star4-${status.index}" title="Pretty good - 4 stars"></label>
												<sf:radiobutton path="score" id="star3half-${status.index}" value="3.5"/><label class="half" for="star3half-${status.index}" title="Meh - 3.5 stars"></label>
												<sf:radiobutton path="score" id="star3-${status.index}" value="3"/><label class = "full" for="star3-${status.index}" title="Meh - 3 stars"></label>
												<sf:radiobutton path="score" id="star2half-${status.index}" value="2.5"/><label class="half" for="star2half-${status.index}" title="Kinda bad - 2.5 stars"></label>
												<sf:radiobutton path="score" id="star2-${status.index}" value="2"/><label class = "full" for="star2-${status.index}" title="Kinda bad - 2 stars"></label>
												<sf:radiobutton path="score" id="star1half-${status.index}" value="1.5"/><label class="half" for="star1half-${status.index}" title="Meh - 1.5 stars"></label>
												<sf:radiobutton path="score" id="star1-${status.index}" value="1"/><label class = "full" for="star1-${status.index}" title="Sucks big time - 1 star"></label>
												<sf:radiobutton path="score"  id="starhalf-${status.index}" value="0.5"/><label class="half" for="starhalf-${status.index}" title="Sucks big time - 0.5 stars"></label>
    										</div>
    										</c:if>
    										</div>
    										
										</div>
									
								</div>
								</sf:form>
							</c:forEach>
							<div class="text-center">
							<ul class="pagination pagination-lg">
								<c:if test="${searchResultRenderingModelsSet.pageNo-5 >= 1}">
									<li><a href="<c:url value="/search?s=${query}&p=${pageNo-5}"/>">&lt;&lt;</a></li>
								</c:if>
								<c:if test="${searchResultRenderingModelsSet.pageNo-1 >= 1}">
									<li><a href="<c:url value="/search?s=${query}&p=${pageNo-1}"/>">&lt;</a></li>
								</c:if>
								<c:forEach var="num" items="${searchResultRenderingModelsSet.numList}">
									<li><a href="<c:url value="/search?s=${query}&p=${num}"/>">${num}</a></li>
								</c:forEach>
  								
  								<c:if test="${searchResultRenderingModelsSet.pageNo + 1 <= searchResultRenderingModelsSet.totalPage}">
									<li><a href="<c:url value="/search?s=${query}&p=${searchResultRenderingModelsSet.pageNo+1}"/>">&gt;</a></li>
								</c:if>
								<c:if test="${searchResultRenderingModelsSet.pageNo + 5 <= searchResultRenderingModelsSet.totalPage}">
									<li><a href="<c:url value="/search?s=${query}&p=${pageNo+5}"/>">&gt;&gt;</a></li>	
								</c:if>
							</ul>
							</div>
						</c:when>
						<c:otherwise>
							검색된 결과가 없습니다.	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	
</div>