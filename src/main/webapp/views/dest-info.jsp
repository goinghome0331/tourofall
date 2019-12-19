<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.5.1/fotorama.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common/page-structure.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/destination/destination.css"/>">

<div>
	<div class="js-result-section result-section result-section-hide">
		<div class="result-section-text">
			등록이 완료되었습니다.
		</div>
	</div>
	<div class="segment">
	<div class="container">
		<h2 class="segment-heading">${basicInfo.title}</h2>
		<div class="sub-info">
			<span class="info">평균 평점 : ${evaluationMean}</span>
			<span class="info"><i class="fa fa-star"></i>: ${evaluationSize}</span>
			<span class="info"><i class="fa fa-commenting-o"></i>:${reviewSize}</span>
			<span class="info"><i class="fa fa-question-circle"></i>:${questionSize}</span>
		</div>
		<div id="basicinfo" class="tabcontent">
			<div class="row">
				<div class="col-sm-6">
					<div class="image-box">
						<img alt="" src="${basicInfo.firstimage}">
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="panel panel-blue">
						<div class="panel-heading">
							기본정보
						</div>
						<div class="panel-body">
							<p>
								우편 번호 : ${basicInfo.zipcode}<br/>
								주소 : ${basicInfo.addr1}${basicInfo.addr2}<br/>
								홈페이지 : ${basicInfo.homepage}
							</p>
						</div>
					</div>
				</div>
				
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-green">
                        <div class="panel-heading">
							개요
                        </div>
                        <div class="panel-body">
                            <p>
                           		${basicInfo.overview}
                            </p>
                        </div>
                        
                    </div>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-yellow">
                        <div class="panel-heading">
							지도
							<!-- 
							<div class="pull-right"><button onclick="showMap()" class="btn btn-default btn-xs">새로고침</button></div>
							 -->
                        </div>
                        <div class="panel-body">
                            <div id="map">
                            	<p id="mapx" title="${basicInfo.mapx}"></p>
                            	<p id="mapy" title="${basicInfo.mapy}"></p>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
			
		</div>
		<div id="introinfo" class="tabcontent">
			<div class="row">
				<table class="table">
					<tbody>
						<!-- 여행지  -->
						<c:if test="${not empty introInfo.infocenter}">
							<tr>
								<td>문의 및 안내</td>
								<td>${introInfo.infocenter}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.expguide}">
							<tr>
								<td>체험 안내</td>
								<td>${introInfo.expguide}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.restdate}">
							<tr>
								<td>휴일</td>
								<td>${introInfo.restdate}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.expagerange}">
							<tr>
								<td>체험가능 연령</td>
								<td>${introInfo.expagerange}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.accomcount}">
							<tr>
								<td>수용인원</td>
								<td>${introInfo.accomcount}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.usetime}">
							<tr>
								<td>이용시간</td>
								<td>${introInfo.usetime}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.parking}">
							<tr>
								<td>주차시설</td>
								<td>${introInfo.parking}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkbabycarriage}">
							<tr>
								<td>유모차 대여여부</td>
								<td>${introInfo.chkbabycarriage}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkpet}">
							<tr>
								<td>애완동물 동반 여부</td>
								<td>${introInfo.chkpet}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkcreditcard}">
							<tr>
								<td>신용카드 사용 여부</td>
								<td>${introInfo.chkcreditcard}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.useseason}">
							<tr>
								<td>이용시기</td>
								<td>${introInfo.useseason}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.opendate}">
							<tr>
								<td>개장일</td>
								<td>${introInfo.opendate}</td>
							</tr>
						</c:if>
						<c:if test="${introInfo.heritage1 != 0 && not empty introInfo.heritage1}">
							<tr>
								<td>세계문화유산 등록여부</td>
								<td>등록됨</td>
							</tr>
						</c:if>
						<c:if test="${introInfo.heritage2 != 0 && not empty introInfo.heritage2}">
							<tr>
								<td>세계자연유산 등록여부</td>
								<td>등록됨</td>
							</tr>
						</c:if>
						<c:if test="${introInfo.heritage3 != 0 && not empty introInfo.heritage3}">
							<tr>
								<td>세계기록유산 등록여부</td>
								<td>등록됨</td>
							</tr>
						</c:if>
						
						<!-- 문화시설  -->
						
						<c:if test="${not empty introInfo.infocenterculture}">
							<tr>
								<td>문의 및 안내</td>
								<td>${introInfo.infocenterculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.scale}">
							<tr>
								<td>규모</td>
								<td>${introInfo.scale}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.usetimeculture}">
							<tr>
								<td>이용시간</td>
								<td>${introInfo.usetimeculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.restdateculture}">
							<tr>
								<td>쉬는날</td>
								<td>${introInfo.restdateculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.usefee}">
							<tr>
								<td>이용요금</td>
								<td>${introInfo.usefee}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.parkingculture}">
							<tr>
								<td>주차시설</td>
								<td>${introInfo.parkingculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.parkingfee}">
							<tr>
								<td>주차요금</td>
								<td>${introInfo.parkingfee}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.chkbabycarriageculture}">
							<tr>
								<td>유모차 대여 여부</td>
								<td>${introInfo.chkbabycarriageculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkcreditcardculture}">
							<tr>
								<td>신용카드 가능 여부</td>
								<td>${introInfo.chkcreditcardculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkpetculture}">
							<tr>
								<td>애완동물 가능 여부</td>
								<td>${introInfo.chkpetculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.accomcountculture}">
							<tr>
								<td>수용인원</td>
								<td>${introInfo.accomcountculture}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.discountinfo}">
							<tr>
								<td>할인정보</td>
								<td>${introInfo.discountinfo}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.spendtime}">
							<tr>
								<td>관람 소요시간</td>
								<td>${introInfo.spendtime}</td>
							</tr>
						</c:if>
						
						<!-- 레포츠  -->
						<c:if test="${not empty introInfo.infocenterleports}">
							<tr>
								<td>문의 및 안내</td>
								<td>${introInfo.infocenterleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.scaleleports}">
							<tr>
								<td>규모</td>
								<td>${introInfo.scaleleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.accomcountleports}">
							<tr>
								<td>수용인원</td>
								<td>${introInfo.accomcountleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.openperiod}">
							<tr>
								<td>개장기간</td>
								<td>${introInfo.openperiod}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.chkbabycarriageleports}">
							<tr>
								<td>유모차 대여 여부</td>
								<td>${introInfo.chkbabycarriageleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.chkcreditcardleports}">
							<tr>
								<td>신용카드 가능 여부</td>
								<td>${introInfo.chkcreditcardleports}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.chkpetleports}">
							<tr>
								<td>애완동물 가능 여부</td>
								<td>${introInfo.chkpetleports}</td>
							</tr>
						</c:if>
						<c:if test="${not empty introInfo.expagerangeleports}">
							<tr>
								<td>체험 가능연령</td>
								<td>${introInfo.expagerangeleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.parkingfeeleports}">
							<tr>
								<td>주차요금</td>
								<td>${introInfo.parkingfeeleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.parkingleports}">
							<tr>
								<td>주차시설</td>
								<td>${introInfo.parkingleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.reservation}">
							<tr>
								<td>예약안내</td>
								<td>${introInfo.reservation}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.restdateleports}">
							<tr>
								<td>쉬는날</td>
								<td>${introInfo.restdateleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.usefeeleports}">
							<tr>
								<td>입장료</td>
								<td>${introInfo.usefeeleports}</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty introInfo.usetimeleports}">
							<tr>
								<td>이용시간</td>
								<td>${introInfo.usetimeleports}</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			
		</div>
		<div id= "detailinfo" class="tabcontent">
			<div class="row">
				<c:choose>
					<c:when test="${not empty detailInfoes}">
						<c:forEach var="info" items="${detailInfoes}">
							<div class="col-lg-3 col-md-4 col-sm-6">
								<div class="panel panel-green">
									<div class="panel-heading">${info.infoname}</div>
									<div class="panel-body">${info.infotext}</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:when test="${not empty detailInfo}">
						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="panel panel-green">
								<div class="panel-heading">
									${detailInfo.infoname}
								</div>
								<div class="panel-body">
									${detailInfo.infotext}
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div id= "imageinfo" class="tabcontent">
			<div class="row">
				<div class="fotorama" data-arrows="true" data-width="80%" data-ratio="700/467" data-allowfullscreen="true" data-nav="thumbs">
					<c:choose>
						<c:when test="${not empty imageInfoes}">
							<c:forEach var="image" items="${imageInfoes}">
								<a href="${image.originimgurl}"><img src="${image.smallimageurl}" data-caption="호수공원" /></a>
							</c:forEach>
						</c:when>
						<c:when test="${not empty imageInfo}">
							<a href="${imageInfo.originimgurl}"><img src="${imageInfo.smallimageurl}" data-caption="호수공원" /></a>
						</c:when>
					</c:choose>                  
             	</div> 
             </div>
		</div>
		<hr/>
		<div class="row">
			<div id="review" class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-commenting-o"></i> 리뷰
					<div class="pull-right"><button type="button" data-toggle="modal" data-target="#review_write" class="btn btn-default btn-xs">리뷰작성하기</button></div>
				</div>
				<div class="panel-body">				
					<ul id="reviewline" class="reviewline">
						<c:forEach var="reviewRenderingModel" items="${reviewRenderingModelsSet.reviewRenderingModels}">
							<li>
								<div class="reviewline-userbadge magenta">
									<a href="<c:url value="/users/${reviewRenderingModel.userId}"/>">
									<i class="fa fa-user"></i>
									</a>
								</div>
								<div class="reviewline-username">
									<a href="<c:url value="/users/${reviewRenderingModel.userId}"/>">
									${reviewRenderingModel.lastName}${reviewRenderingModel.firstName}
									</a>
								</div>
								<div class="reviewline-panel">
									<div class="review-rating">
									<fmt:parseNumber var = "iScore" type = "number" integerOnly="true" value = "${reviewRenderingModel.score}" />
									
										<c:forEach begin="1" end="${iScore}" step="1">
											<i class="fa fa-star"></i>
										</c:forEach>
										<c:if test="${reviewRenderingModel.score - iScore > 0}">
											<i class="fa fa-star-half-empty"></i>
										</c:if>
									</div>
									<div class="reviewline-heading">
										<h4 class="reviewline-title">${reviewRenderingModel.title}</h4>
									</div>
									<div class="reviewline-body">
										<p>
											${reviewRenderingModel.content}
										</p>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
					<c:if test="${reviewRenderingModelsSet.nextIndex}">
						<button id="getReviewMore" class="btn btn-primary btn-block">더 보기 <i class="fa fa-arrow-circle-down"></i></button>
						<form name="reviewmore" action="<c:url value="/dest/info/reviewmore"/>">
								<input type="hidden" name="itemId" value="${itemId}"/>
								<input type="hidden" name="index" value="1" />
						</form>
					</c:if>
					
				</div>
			</div>
		</div>
		
		<hr/>
		<div class="row">
			<div id="qna" class="panel panel-default">
				<input type="hidden" name="currentPageNo" value="1" />
  				<input type="hidden" name="itemId" value="${itemId}"/>
				<div class="panel-heading">
					<i class="fa fa-question-circle"></i>QnA
					<div class="pull-right"><button type="button" data-toggle="modal" data-target="#question_write" class="btn btn-default btn-xs">질문작성하기</button></div>
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
								<c:forEach var="questionRenderingModel" items="${questionRenderingModelsSet.questionRenderingModels}" varStatus="status">								
									<tr>
										<td>${(questionRenderingModelsSet.pageNo-1)*5 + status.index+1}</td>
										<td><a class="js-question" id="${questionRenderingModel.questionId}" data-toggle="modal" data-target="#question">${questionRenderingModel.title}<span class="badge">${questionRenderingModel.answerCount}</span></a></td>
										<td>${questionRenderingModel.createdDate.year+1900}-${questionRenderingModel.createdDate.month+1}-${questionRenderingModel.createdDate.date}</td>
										<td><a class="js-users" href="<c:url value="/users/${questionRenderingModel.userId}"/>">${questionRenderingModel.lastName}${questionRenderingModel.firstName}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination">
							<li>
								<c:if test="${questionRenderingModelsSet.pageNo -  5>= 1}">
  									<a class="js-pagination-double-left" href="#qna">&lt;&lt;</a>
  								</c:if>
							</li>
							<li>
								<c:if test="${questionRenderingModelsSet.pageNo -  1>= 1}">
									<a class="js-pagination-left" href="#qna">&lt;</a>
  								</c:if>
							</li>
  							<c:forEach var="num" items="${questionRenderingModelsSet.indexList}">
  								<li>
  									<a class="js-pagination-num" href="#qna">
  										${num}

  									</a>
  								</li>
  							</c:forEach>
  							<li>
  								<c:if test="${questionRenderingModelsSet.pageNo +  1 <= questionRenderingModelsSet.totalPage}">
  									<a class="js-pagination-right" href="#qna">&gt;</a>
  								</c:if>
  							</li>
  							<li>
  								<c:if test="${questionRenderingModelsSet.pageNo +  5 <= questionRenderingModelsSet.totalPage}">
  									<a class="js-pagination-double-right" href="#qna">&gt;&gt;</a>
  								</c:if>
  							</li>
						</ul>
					</div>
					
				</div>
			</div>
		</div>
		
	</div>
	</div>
	
	<div id="review_write" class="modal fade" role="dialog">
		<div class="modal-dialog">
  		  <!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">리뷰 작성하기</h4>
				</div>
				<div class="modal-body">
					<form action = "<c:url value = "/review/write"/>" name="review-register">
						<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
      	 	 			<div class="form-group">
							<label for="title">제목</label>
							<input type="text" class="form-control" id="title" placeholder="제목을 입력하세요" name="title">
							<span id = "title-error" class="error-msg"></span>
						</div>
						<div class="form-group">
							<label for="score" style="float:left;margin-top:5px;">평점</label>
							<div class="rating">	
								<input type="radio" id="star5" name="score" value="5"/><label class = "full" for="star5" title="Awesome - 5 stars"></label>
								<input type="radio" id="star4half" name="score" value="4.5" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
    							<input type="radio" id="star4" name="score" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
    							<input type="radio" id="star3half" name="score" value="3.5" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
    							<input type="radio" id="star3" name="score" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
    							<input type="radio" id="star2half" name="score" value="2.5" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
    							<input type="radio" id="star2" name="score" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
    							<input type="radio" id="star1half" name="score" value="1.5" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
    							<input type="radio" id="star1" name="score" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
    							<input type="radio" id="starhalf" name="score" value="0.5" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
    						</div>
    						<div id="rating-error" class="error-msg"></div>
						</div>
						<div class="form-group" style="clear:both">
							<label for="content">내용</label>
							<textarea id="content" name = "content" class="form-control" cols="5"></textarea>
							<div id="content-error" class="error-msg"></div>
						</div>
					</form>
      			</div>
				<div class="modal-footer">
					<button id="register-review" type="button" class="btn btn-default">작성완료</button>
				<!-- 
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				-->
				</div>
    		</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="question_write" role="dialog">
		<div class="modal-dialog">
		<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">질문 작성하기</h4>
				</div>
				<div class="modal-body">
					<form name="question-register" action="<c:url value="/qna/question/write"/>">
						<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
        	 			<div class="form-group">
							<label for="title">제목</label>
							<input type="text" class="form-control" id="title" placeholder="제목을 입력하세요" name="title">
							<span id = "title-error" class="error-msg"></span>
						</div>
						<div class="form-group" style="clear:both">
							<label for="content">내용</label>
							<textarea id="content" name="content" class="form-control" cols="5"></textarea>
							<div id="content-error" class="error-msg"></div>
						</div>
					</form>
				</div>
        		<div class="modal-footer">
					<button id="register-question" type="button" class="btn btn-default">작성완료</button>
        		</div>
			</div>
    	</div>
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
        	<form name="answer-register" action="<c:url value="/qna/answer/write"/>">
        		<input type="hidden" name="${_csrf.parameterName}" value="${ _csrf.token}" />
        		<input type="hidden" name="questionId" name=""/>
				<textarea  placeholder="로그인 후 입력하실 수 있습니다." id="answer-write" name="answer-write" class="form-control" cols="5"></textarea>
				<div id="content-error" class="error-msg"></div>
			</form>
         	<button id="register-answer" type="button" class="btn btn-default">작성완료</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.5.1/fotorama.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA-UDsWJx5dkJpLf4HitN6Uy4-JWADLu14&sensor=true"></script>
<script type="text/javascript" src="<c:url value="/resources/js/destination/destination.js"/>"></script>