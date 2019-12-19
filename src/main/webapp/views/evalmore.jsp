<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/evaluation/evaluation.css"/>">
<script type="text/javascript" src="<c:url value="/resources/js/header/small-header.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/header/evaluation-link.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/evaluation/evaluation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/evaluation/eval-reco-common.js"/>"></script>
<div>
	<div class="js-result-section result-section result-section-hide">
		<div class="result-section-text">
		</div>
	</div>
	<div class="empty-header">
	</div>
	<div class="container">
		<div class="row">
		<!-- Example row of columns -->
			<div class="col-sm-2">
            	<p class="copy-menu-title text-center">여행지 타입</p>
                <div class="list-group">
                	<a id ="sub-navbar-1" href="<c:url value="/eval/evalmore?itemCat1=A01&itemCat2=A0101"/>" class="list-group-item">자연관광지</a>
					<a id ="sub-navbar-2" href="<c:url value="/eval/evalmore?itemCat1=A02&itemCat2=A0201"/>" class="list-group-item">역사관광지</a>
					<a id ="sub-navbar-3" href="<c:url value="/eval/evalmore?itemCat1=A02&itemCat2=A0202"/>" class="list-group-item">휴양관광지</a>
					<a id ="sub-navbar-4" href="<c:url value="/eval/evalmore?itemCat1=A02&itemCat2=A0203"/>" class="list-group-item">체험관광지</a>
					<a id ="sub-navbar-5" href="<c:url value="/eval/evalmore?itemCat1=A02&itemCat2=A0204"/>" class="list-group-item">산업관광지</a>
					<a id ="sub-navbar-6" href="<c:url value="/eval/evalmore?itemCat1=A02&itemCat2=A0205"/>" class="list-group-item">건축/조형물</a>
					<a id ="sub-navbar-7" href="<c:url value="/eval/evalmore?itemCat1=A02&itemCat2=A0206"/>" class="list-group-item">문화시설</a>
					<a id ="sub-navbar-8" href="<c:url value="/eval/evalmore?itemCat1=A03&itemCat2=A0302"/>" class="list-group-item">육상레포츠</a>
					<a id ="sub-navbar-9" href="<c:url value="/eval/evalmore?itemCat1=A03&itemCat2=A0303"/>" class="list-group-item">수상레포츠</a>
					<a id ="sub-navbar-10" href="<c:url value="/eval/evalmore?itemCat1=A03&itemCat2=A0304"/>" class="list-group-item">항공레포츠</a> 	
				</div>
        	</div>
        	<div id="eval-list" class="col-sm-10">
        		<div class="center-container">
					<p class="lead">다녀 온 여행지를 평가 해 주세요</p>
				</div>
				
				<c:forEach var = "evaluationRenderingModel" items="${evaluationRenderingModels}" varStatus="status">
					<div class="col-md-4 col-sm-6">
						<form name = "request-eval" action="<c:url value="/eval/evalmore"/>">
							<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
							<input type="hidden" name="itemId" value="${evaluationRenderingModel.itemId}">
							<div class="eval-unit">
								<article class="eval-unit-card eval-unit-card-fixed eval-unit-card-has-img eval-unit-card-article">
									<div class="eval-unit-card-mask">
										<a href="<c:url value="/dest/info/${evaluationRenderingModel.itemId}"/>">
											<figure class="eval-unit-card-figure">
												<img alt="이미지 없음" src="${evaluationRenderingModel.imageUrl}" class="img-rounded"  onerror="this.style.display='none'">
											</figure>
										</a>
										<div class="eval-unit-card-content">
											<div class="eval-unit-card-content-title">
												${evaluationRenderingModel.title}
											</div>
											<div class="eval-unit-card-content-context">
												평점
											</div>
											<div class="rating">
												<input type="radio" id="star5-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="5"/><label class = "full" for="star5-${status.index}" title="Awesome - 5 stars"></label>
								    			<input type="radio" id="star4half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="4.5" /><label class="half" for="star4half-${status.index}" title="Pretty good - 4.5 stars"></label>
    											<input type="radio" id="star4-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="4" /><label class = "full" for="star4-${status.index}" title="Pretty good - 4 stars"></label>
    											<input type="radio" id="star3half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="3.5" /><label class="half" for="star3half-${status.index}" title="Meh - 3.5 stars"></label>
    											<input type="radio" id="star3-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="3" /><label class = "full" for="star3-${status.index}" title="Meh - 3 stars"></label>
    											<input type="radio" id="star2half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="2.5" /><label class="half" for="star2half-${status.index}" title="Kinda bad - 2.5 stars"></label>
    											<input type="radio" id="star2-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="2" /><label class = "full" for="star2-${status.index}" title="Kinda bad - 2 stars"></label>
    											<input type="radio" id="star1half-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="1.5" /><label class="half" for="star1half-${status.index}" title="Meh - 1.5 stars"></label>
    											<input type="radio" id="star1-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="1" /><label class = "full" for="star1-${status.index}" title="Sucks big time - 1 star"></label>
    											<input type="radio" id="starhalf-${status.index}" name="recommendationRenderingModels[${status.index}].score" value="0.5" /><label class="half" for="starhalf-${status.index}" title="Sucks big time - 0.5 stars"></label>
    										</div>
    									</div>
    								</div>
    							</article>
    						</div>
    					</form>
					</div>
				</c:forEach>
			</div>
				
		</div>
		<form id="scroll-form" action="<c:url value="/eval/moreeval"/>">
			<input type="hidden" name="request-cat1" value="${currentItemCat1}" />
			<input type="hidden" name="request-cat2" value="${currentItemCat2}" />
			<input type="hidden" name="request-page" value="${currentPageNo}" />
		</form>
	</div>
</div>
<a href="#"><div class="fix">↑</div></a>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>