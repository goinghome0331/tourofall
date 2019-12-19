<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common/page-structure.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/evaluation/evaluation.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/users/users.css"/>">
<div>
	<sec:authentication var="user" property="principal"/>
	<div class="js-result-section result-section result-section-hide">
		<div class="result-section-text">
		</div>
	</div>
	<div class="tab-content">
		<div id="user-evaluation" class="tab-pane fade in active">
			<div class="segment">
				<div class="container">
					<div id="eval-list" class="row">
						<c:forEach var = "evaluationRenderingModel" items="${evaluationRenderingModelsSet.evaluationRenderingModels}" varStatus="status">
							<div class="col-lg-3 col-md-4 col-sm-6">
								<sf:form name = "request-eval" action="${pageContext.request.contextPath}/eval/evalmore" commandName="evaluationRenderingModel${status.index}">
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
    											</div>
    										</div>
    									</article>
    								</div>
    								<c:if test="${evaluationRenderingModelsSet.userScoreList[status.index] != 0}">
    									<div class="text-center">
    										평점 : ${evaluationRenderingModelsSet.userScoreList[status.index]}
    									</div>
    								</c:if>
    							</sf:form>
							</div>
						</c:forEach>
					</div>
					<div class="row">
						<div id="page-index" class="text-center">
							<input type="hidden" name="currentPageNo" value="1" />
							<input type="hidden" name="userId" value="${userId}" />
							<ul class="pagination pagination-lg">
								<c:if test="${evaluationRenderingModelsSet.pageNo-5 >= 1}">
									<li><a href="#">&lt;&lt;</a></li>
								</c:if>
								<c:if test="${evaluationRenderingModelsSet.pageNo-1 >= 1}">
									<li><a href="#">&lt;</a></li>
								</c:if>
								<c:forEach var="num" items="${evaluationRenderingModelsSet.indexList}">
									<li><a href="#">${num}</a></li>
								</c:forEach>
  									
  								<c:if test="${evaluationRenderingModelsSet.pageNo + 1 <= evaluationRenderingModelsSet.totalPage}">
									<li><a href="#">&gt;</a></li>
								</c:if>
								<c:if test="${evaluationRenderingModelsSet.pageNo + 5 <= evaluationRenderingModelsSet.totalPage}">
									<li><a href="#">&gt;&gt;</a></li>	
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="user-review" class="tab-pane fade">
			<div class="segment">
				<div class="container-fluid">
					<div id="userreviewline" class="row">
						<c:forEach var = "userReviewRenderingModel" items="${userReviewRenderingModelsSet.userReviewRenderingModels}" varStatus="status">
							<div class="col-sm-6 row-gap">
								<sf:form name = "request-eval" action="${pageContext.request.contextPath}/eval/evalmore" commandName="userReviewRenderingModel${status.index}">
									<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
									<input type="hidden" name="itemId" value="${userReviewRenderingModel.itemId}">
									<div class="review-card card-body">
										<div class="review-card-wrapper">
              								<div class="review-card-figure review-img">
                								<img src="${userReviewRenderingModel.imageUrl}" width=210 height=348>
                								<c:if test="${user.id != userId}">
                								<div class="review-eval-content">
                								
                  									<div class="review-eval-content-rating">
                  										<div class="review-eval-content-context">
                    										평점
                  										</div>
                  										<div class="review-rating">
															<sf:radiobutton path="myScore" id="Rstar5-${status.index}" value="5"/><label class = "full" for="Rstar5-${status.index}" title="Awesome - 5 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar4half-${status.index}" value="4.5"/><label class="half" for="Rstar4half-${status.index}" title="Pretty good - 4.5 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar4-${status.index}" value="4"/><label class = "full" for="Rstar4-${status.index}" title="Pretty good - 4 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar3half-${status.index}" value="3.5"/><label class="half" for="Rstar3half-${status.index}" title="Meh - 3.5 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar3-${status.index}" value="3"/><label class = "full" for="Rstar3-${status.index}" title="Meh - 3 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar2half-${status.index}" value="2.5"/><label class="half" for="Rstar2half-${status.index}" title="Kinda bad - 2.5 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar2-${status.index}" value="2"/><label class = "full" for="Rstar2-${status.index}" title="Kinda bad - 2 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar1half-${status.index}" value="1.5"/><label class="half" for="Rstar1half-${status.index}" title="Meh - 1.5 stars"></label>
															<sf:radiobutton path="myScore" id="Rstar1-${status.index}" value="1"/><label class = "full" for="Rstar1-${status.index}" title="Sucks big time - 1 star"></label>
															<sf:radiobutton path="myScore"  id="Rstarhalf-${status.index}" value="0.5"/><label class="half" for="Rstarhalf-${status.index}" title="Sucks big time - 0.5 stars"></label>
														</div>
				   									</div>
                								</div>
                								</c:if>
              								</div>
            								<div class="review-card-meta review-card-meta-overflow media-body">
                								<div class="review-card-title">
              	    								${userReviewRenderingModel.itemTitle}
                								</div>
                								<div class="review-card-address">
                  									${userReviewRenderingModel.address}<br/>
                								</div>						
                								<div>
                  									평점<br/>
                  									<fmt:parseNumber var = "iScore" type = "number" integerOnly="true" value = "${userReviewRenderingModel.score}" />
                  									<c:forEach begin="1" end="${iScore}" step="1">
														<i class="fa fa-star"></i>
													</c:forEach>
													<c:if test="${reviewRenderingModel.score - iScore > 0}">
														<i class="fa fa-star-half-empty"></i>
													</c:if>
                								</div>
                								<div class="review-card-content">
                									<p>${userReviewRenderingModel.title}</p>
                  									${userReviewRenderingModel.content}
                								</div>
             								</div>
             								<div class="review-card-date">
       	      									${userReviewRenderingModel.createdDate.year+1900}년${userReviewRenderingModel.createdDate.month+1}월${userReviewRenderingModel.createdDate.date}일
			 								</div>
            							</div>
         							 </div>
        						  </sf:form>
        						  
        						</div>
        					</c:forEach>
					</div>
					<c:if test="${userReviewRenderingModelsSet.nextIndex}">
        				<div class="col-sm-12">
							<button id="getUserReviewMore" class="btn btn-primary btn-block">더 보기 <i class="fa fa-arrow-circle-down"></i></button>
							<form name="userreviewmore" action="<c:url value="/users/${userId}/reviewmore"/>">
								<input type="hidden" name="index" value="1" />
								<input type="hidden" name="userId" value="${userId}" />
								<input type="hidden" name="myId" value="${user.id}" />
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<c:if test="${user.id == userId}">
			<div id="user-question" class="tab-pane fade">
				<div id="qna" class="panel panel-default">
					<input type="hidden" name="currentPageNo" value="1" />
  					<input type="hidden" name="itemId" value="${itemId}"/>
					<div class="panel-heading">
						<i class="fa fa-question-circle"></i>QnA
					</div>
					<div class="panel-body">
						<div class="table-responsive table-bordered">
							<table class = "table">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>작성일자</th>
										<th>작성자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="questionRenderingModel" items="${userQuestionRenderingModelsSet.questionRenderingModels}" varStatus="status">								
										<tr>
											<td>${status.index+1}</td>
											<td><a class="js-question" id="${questionRenderingModel.questionId}" data-toggle="modal" data-target="#question">${questionRenderingModel.title}<span class="badge">${questionRenderingModel.answerCount}</span></a></td>
											<td>${questionRenderingModel.createdDate.year+1900}-${questionRenderingModel.createdDate.month+1}-${questionRenderingModel.createdDate.date}</td>
											<td><a class="js-users" href="<c:url value="/users/${questionRenderingModel.userId}"/>">${questionRenderingModel.lastName}${questionRenderingModel.firstName}</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${answerRenderingModelsSet.nextIndex}">
								<button id="getUserQuestionMore" class="btn btn-primary btn-block">더 보기 <i class="fa fa-arrow-circle-down"></i></button>
								<form name="userquestionmore" action="<c:url value="/users/${userId}/questionmore"/>">
									<input type="hidden" name="index" value="1" />
									<input type="hidden" name="userId" value="${userId}" />
								</form>
							</c:if>
							
					</div>
					
				</div>
			</div>
			</div>
			<div id="user-answer" class="tab-pane fade">
				<div class="container-fluid">
					<div id="answer-line" class="row">
				<c:forEach var="answerRenderingModel" items="${answerRenderingModelsSet.answerRenderingModels}" varStatus="status">
					<div class="answers-content  js-answers-content${answerRenderingModel.answerId}">
  						<div class="answers-section-wrapper">
  							<div class="answers-section">
  								<div class="answers-section-header">
  									<b>${answerRenderingModel.questionTitle}</b> <p class="pull-right">| ${answerRenderingModel.questionCreatedDate.year+1900}년 ${answerRenderingModel.questionCreatedDate.month+1}월 ${answerRenderingModel.questionCreatedDate.date}일</p>
  								</div>
  								<div class="answers-section-body">
  									${answerRenderingModel.content}
  								</div>
  								<div class="answers-section-footer">
  									${answerRenderingModel.createdDate.year+1900}년 ${answerRenderingModel.createdDate.month+1}월 ${answerRenderingModel.createdDate.date}일<button id="delete-answer" class="js-delete btn btn-default">삭제<input type="hidden" name="answerId" value="${answerRenderingModel.answerId}" /></button>
  									
  								</div>
			  				</div>
  						</div>
  					</div>
				</c:forEach>
				</div>
				</div>
				<c:if test="${answerRenderingModelsSet.nextIndex}">
					<button id="getUserAnswerMore" class="btn btn-primary btn-block">더 보기 <i class="fa fa-arrow-circle-down"></i></button>
						<form name="useranswermore" action="<c:url value="/users/${userId}/answermore"/>">
							<input type="hidden" name="index" value="1" />
							<input type="hidden" name="userId" value="${userId}" />
						</form>
				</c:if>
			</div>
		</c:if>
	</div>
	<div id="question" class="modal fade" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">QnA</h4>
        </div>
        <div class="modal-header">
          <h4 class="modal-title js-question-title"></h4>
        </div>
        <div class="modal-body" id="question-content">
          	 
        </div>
         <div class="modal-body" id="modal-answers">
         	
    	    
        </div>
        <div class="modal-footer">
         	<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
        </div>
      </div>
      
    </div>
  </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/users/users.js"/>"></script>