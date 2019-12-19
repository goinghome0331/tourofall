function openDestInfo(evt, contentCategoryName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("menu-item");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" selected", "");
	}
	document.getElementById(contentCategoryName).style.display = "block";
	evt.currentTarget.className += " selected";
}

document.getElementById("default").click();

function init() {


    var mapOptions = {

        zoom: 15,


        center: new google.maps.LatLng(mapy,mapx), // New York
    };


    var mapElement = document.getElementById('map');


    map = new google.maps.Map(mapElement, mapOptions);
    

    var image = 'http://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png';
    var myLatLng = new google.maps.LatLng(mapy,mapx);
    var beachMarker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        icon: image
    });
   
}

function showMap(){

	mapx = $('#mapx').attr("title");
	mapy = $('#mapy').attr("title");
	var map = null;

	google.maps.event.addDomListener(window, 'load', init);
	
//	google.maps.event.addDomListener(window, 'resize', function() {
//	    map.setCenter(new google.maps.LatLng(mapy,mapx));
//	});
}
$(document).ready(showMap());

function getReviewRenderingModels(){
	$('#getReviewMore').click(function(){
		var clickedForm = $('form[name="reviewmore"]');
		var itemId = clickedForm.find('input[name="itemId"]').val();
		var index = clickedForm.find('input[name="index"]').val();
		var formUrl = clickedForm.attr("action");
		
		var parameter = new Object();
		
		parameter.itemId = itemId;
		parameter.index = index;
		
		clickedForm.find("input[name=index]").attr("value",parseInt(index,10)+1);
	
		var pData = $.param(parameter);
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				var reviewRenderingModels = result.reviewRenderingModels;
				if(result.nextIndex == false){
					$('#getReviewMore').css('display','none');
				}
								$.each( reviewRenderingModels , function(index, value){
									var iScore = parseInt(value.score, 10);
									
									var rating = $('<div>').attr('class','rating');
									for(var i = 0; i < iScore; i++){
										rating.append($('<i>')
											.attr('class','fa fa-star')	
										)
									}
										
									if(value.score - iScore > 0){
										rating.append($('<i>')
												.attr('class','fa fa-star-half-empty')	
										)
									}
									
									
									$("#reviewline")
									.append($('<li>')
										.append($('<div>')
											.attr('class','reviewline-userbadge magenta')
											.append($('<i>')
												.attr('class','fa fa-user')
											)
										)
										.append($('<div>')
											.attr('class',"reviewline-username")
											.append(value.lastName+value.firstName)
										)
										.append($('<div>')
											.attr('class',"reviewline-panel")
											.append(rating
											)
											.append($('<div>')
												.attr('class',"reviewline-heading")
												.append($('<h4>')
													.attr('class',"reviewline-title")
													.append(value.title)
												)
											)
											.append($('<div>')
												.attr('class',"reviewline-body")
												.append($('<p>')
													.append(value.content)
												)
											)
										)
									);
				});
				
							
					
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
	});
}
$(document).ready(getReviewRenderingModels);
$(document).ready(function(){
	$('#qna ul.pagination li a').on('click',function(){
		//alert('entered');
		var pageNo;
		var pIndex = $('#qna').find('input[name="currentPageNo"]').attr('value');
		switch($(this).text()){
		case '<':
			pageNo = parseInt(pIndex,10)-1;
			if(pageNo < 1)
				pageNo = 1;
			break;
		case '<<':
			pageNo = parseInt(pIndex,10)-5;
			if(pageNo < 1)
				pageNo = 1;
			break;
		case '>':
			pageNo = parseInt(pIndex,10)+1;
			break;
		case '>>':
			pageNo = parseInt(pIndex,10)+5;
			break;
		default:
			pageNo = parseInt($(this).text(),10);
			break;
		}
		$('#qna').find('input[name="currentPageNo"]').attr('value', pageNo);
		
		var itemId = $('#qna').find('input[name="itemId"]').val();
		var formUrl = "/tourofall/dest/info/questions";
		
		var parameter = new Object();
		
		parameter.itemId = itemId;
		parameter.pageNo = pageNo;
		
	
		var pData = $.param(parameter);
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				$('.table-responsive tbody').empty();
				
				$('.pagination').empty();
				
				var questionRenderingModels = result.questionRenderingModels;
				$.each( questionRenderingModels , function(index, value){
									var date = new Date(value.createdDate);
									$(".table-responsive tbody")
										.append($('<tr>')
											.append($('<td>')
												.append((result.pageNo-1)*5+index+1)
											)
											.append($('<td>')
												.append($('<a>')
													.attr('id',value.questionId)
													.attr('data-toggle','modal')
													.attr('data-target','#question')
													.attr('class','js-question')
													.attr('onclick','getQnARenderingModels(event)')
													.append(value.title)
													.append($('<span>')
															.attr('class','badge')
															.append(value.answerCount)
													)
												)
											)
											.append($('<td>')
												.append((date.getFullYear())+'-'+(date.getMonth()+1)+'-'+(date.getDate()))
											)
											.append($('<td>')
													.append($('<a>')
															.attr('class','js-users')
															.attr('href','/tourofall/users/'+value.userId)
																.append(value.lastName+value.firstName)
													)
											)
										);
				});
				
				var indexList = result.indexList;
				var v;
				if(result.pageNo - 5 >= 1){
					v = result.pageNo - 5;
					$('.pagination')
						.append($('<li>')
							.append($('<a>')
								.attr('onclick','getQuestionRenderingModels(+'+v+'+)')
								.attr('href','#qna')
								.append("<<")
							)
						);
				} 
				if(result.pageNo - 1 >= 1){
					v= result.pageNo - 1;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.attr('href','#qna')
							.append("<")
						)
					);
				} 
				
				$.each( indexList, function(index, value){
					v = value;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.attr('href','#qna')
							.append(value)
						)
					);
				});
				
				if(result.pageNo + 1 <= result.totalPage){
					v = result.pageNo + 1;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.attr('href','#qna')
							.append(">")
						)
					);
				}
				if(result.pageNo + 5 <= result.totalPage){
					v = result.pageNo + 5;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('href','#qna')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.append(">>")
						)
					);
				}
				
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
	});
});
function getQuestionRenderingModels(v){
		//alert(v);
		var pageNo;
		var pIndex = $('#qna').find('input[name="currentPageNo"]').attr('value');
		switch(v){
		case '<':
			pageNo = parseInt(pIndex,10)-1;
			if(pageNo < 1)
				pageNo = 1;
			break;
		case '<<':
			pageNo = parseInt(pIndex,10)-5;
			if(pageNo < 1)
				pageNo = 1;
			break;
		case '>':
			pageNo = parseInt(pIndex,10)+1;
			break;
		case '>>':
			pageNo = parseInt(pIndex,10)+5;
			break;
		default:
			pageNo = parseInt(v,10);
			break;
		}
		$('#qna').find('input[name="currentPageNo"]').attr('value', pageNo);
		
		var itemId = $('#qna').find('input[name="itemId"]').val();
		var formUrl = "/tourofall/dest/info/questions";
		
		var parameter = new Object();
		
		parameter.itemId = itemId;
		parameter.pageNo = pageNo;
		
	
		var pData = $.param(parameter);
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				$('.table-responsive tbody').empty();
				
				$('.pagination').empty();
				
				var questionRenderingModels = result.questionRenderingModels;
				$.each( questionRenderingModels , function(index, value){
									var date = new Date(value.createdDate);
									$(".table-responsive tbody")
										.append($('<tr>')
												.append($('<td>')
														.append((result.pageNo-1)*5+index+1)
													)
													.append($('<td>')
														.append($('<a>')
															.attr('id',value.questionId)
															.attr('data-toggle','modal')
															.attr('data-target','#question')
															.attr('class','js-question')
															.attr('onclick','getQnARenderingModels(event)')
															.append(value.title)
															.append($('<span>')
																	.attr('class','badge')
																	.append(value.answerCount)
															)
														)
													)
													.append($('<td>')
														.append((date.getFullYear())+'-'+(date.getMonth()+1)+'-'+(date.getDate()))
													)
													.append($('<td>')
															.append($('<a>')
																	.attr('class','js-users')
																	.attr('href','/tourofall/users/'+value.userId)
																		.append(value.lastName+value.firstName)
															)
													)
										);
				});
				
				var indexList = result.indexList;
				var v;
				if(result.pageNo - 5 >= 1){
					v = result.pageNo - 5;
					$('.pagination')
						.append($('<li>')
							.append($('<a>')
								.attr('onclick','getQuestionRenderingModels('+v+')')
								.attr('href','#qna')
								.append("<<")
							)
						);
				} 
				if(result.pageNo - 1 >= 1){
					v = result.pageNo - 1;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.attr('href','#qna')
							.append("<")
						)
					);
				} 
				
				$.each( indexList, function(index, value){
					v = value;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.attr('href','#qna')
							.append(value)
						)
					);
				});
				
				if(result.pageNo + 1 <= result.totalPage){
					v = result.pageNo + 1;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.attr('href','#qna')
							.append(">")
						)
					);
				}
				if(result.pageNo + 5 <= result.totalPage){
					v = result.pageNo + 5;
					$('.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('href','#qna')
							.attr('onclick','getQuestionRenderingModels('+v+')')
							.append(">>")
						)
					);
				}
				
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
};


function sendReviewRegistration(){
	$('#register-review').click(function(){
		var form = $('form[name="review-register"]');
		var title = form.find('input[name="title"]').val();
		var content = form.find('textarea[name="content"]').val();
		var score = form.find('input[name="score"]:checked').val();
		var itemId = $('#qna').find('input[name="itemId"]').val();
		
		var formUrl = form.attr("action");
		
		var formData = {"title":title,"content":content,"score":score,"itemId":itemId};
		
		var jsonData = JSON.stringify(formData);
		
		
		
		var csrf_token = form.find(':first-child').val();
		
		//alert(formUrl +", "+jsonData+", "+ csrf_token);
		
		
		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			if (!options.crossDomain) {
			  return jqXHR.setRequestHeader('X-CSRF-Token', csrf_token);
			}
		});
		
		var request = $.ajax({
			url:formUrl,
			type:"POST",
			contentType:"application/json",
			data:jsonData,
			dataType:"json",
			success: function(result,status,xhr){
				window.location.href = "/tourofall/dest/info/"+itemId;
			},
			error: function(xhr){
				switch(xhr.status){
				case 200:
					window.location.href = "/tourofall/signin";
					break;
				case 204:
					var jsonResponse = JSON.parse(xhr.responseText);
					//alert(jsonResponse.message);
					break;
				case 409:
					var jsonResponse = JSON.parse(xhr.responseText);
					for(var i=0;i < jsonResponse.length;i++){
						switch(jsonResponse[i].field){
						case "title":
							$('form[name="review-register"] #title-error').append(jsonResponse[i].defaultMessage);
							break;
						case "score":
							$('form[name="review-register"] #rating-error').append(jsonResponse[i].defaultMessage);
							break;
						case "content":
							$('form[name="review-register"] #content-error').append(jsonResponse[i].defaultMessage);
							break;
						}
					}
					break;
				}
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
}

$(document).ready(sendReviewRegistration);


function sendQuestionRegistration(){
	$('#register-question').click(function(){
		
		var form = $('form[name="question-register"]');
		var title = form.find('input[name="title"]').val();
		var content = form.find('textarea[name="content"]').val();
		var itemId = $('#qna').find('input[name="itemId"]').val();
		
		
		
		var formUrl = form.attr("action");

		var formData = {"title":title,"content":content,"itemId":itemId};
		
		var jsonData = JSON.stringify(formData);
		
		var csrf_token = form.find(':first-child').val();
		
		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			if (!options.crossDomain) {
			  return jqXHR.setRequestHeader('X-CSRF-Token', csrf_token);
			}
		});
		
		
		
		
		//alert(formUrl +", "+jsonData+", "+ csrf_token);
		var request = $.ajax({
			url:formUrl,
			type:"POST",
			contentType:"application/json",
			data:jsonData,
			dataType:"json",
			success: function(result,status,xhr){
				window.location.href = "/tourofall/dest/info/"+itemId;
			},
			error: function(xhr){
				switch(xhr.status){
				case 200:
					window.location.href = "/tourofall/signin";
					break;
				case 204:
					var jsonResponse = JSON.parse(xhr.responseText);
					//alert(jsonResponse.message);
					break;
				case 409:
					var jsonResponse = JSON.parse(xhr.responseText);
					for(var i=0;i < jsonResponse.length;i++){
						switch(jsonResponse[i].field){
						case "title":
							$('form[name="question-register"] #title-error').append(jsonResponse[i].defaultMessage);
							break;
						case "content":
							$('form[name="question-register"] #content-error').append(jsonResponse[i].defaultMessage);
							break;
						}
					}
					break;
				}
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
			
	});
}

$(document).ready(sendQuestionRegistration);


var selected;
$(document).ready(function(){
	$('#qna table tbody tr td a.js-question').click(function(){
		selected = $(this);
		var questionId = $(this).attr('id');
		
		var formUrl = "/tourofall/qna/question/"+questionId;
		
		//alert(formUrl);
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			dataType:"json",
			success: function(result,status,xhr){
				var questionRenderingModel = result.questionRenderingModel;
				
				
				
				
				$('#question .js-question-title').append("제목> "+questionRenderingModel.title);
				
				
				
				$('#question #question-content').append($('<p>')
												.append("내용> "+questionRenderingModel.content)
											);
				
				var answerRenderingModels = result.answerRenderingModels;
				$('#modal-answers')
				.append($('<hr>')
				)
				.append("댓글")
				.append($('<span>')
					.attr('class','badge')
					.append(questionRenderingModel.answerCount)
				);
				$.each( answerRenderingModels , function(index, value){
					var date = new Date(value.createdDate);
					$('#modal-answers')
								.append($('<hr>')
								)
								.append($('<div>')
									.append($('<a>')
										.attr('href','/tourofall/users/'+value.userId)
										.append(value.lastName+value.firstName)
									)
									.append(" "+(date.getFullYear())+'-'+(date.getMonth()+1)+'-'+(date.getDate()))
									.append($('<br />'))
									.append(value.content)
								)
				});
				$('form[name="answer-register"] input[name="questionId"]').attr('value',questionRenderingModel.questionId);
				
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
})
function getQnARenderingModels(event){
	selected = $(event.target);
	var questionId = $(event.target).attr('id');
	
	var formUrl = "/tourofall/qna/question/"+questionId;
	
	//alert(formUrl);
	
	var request = $.ajax({
		type:"GET",
		url:formUrl,
		contentType:"text/plain",
		dataType:"json",
		success: function(result,status,xhr){
			var questionRenderingModel = result.questionRenderingModel;
			$('#question .js-question-title').append("제목> "+questionRenderingModel.title);
			
			
			
			$('#question #question-content').append($('<p>')
											.append("내용> "+questionRenderingModel.content)
										);
			
			var answerRenderingModels = result.answerRenderingModels;
			$('#modal-answers')
			.append($('<hr>')
			)
			.append("댓글")
			.append($('<span>')
				.attr('class','badge')
				.append(questionRenderingModel.answerCount)
			);
			$.each( answerRenderingModels , function(index, value){
				var date = new Date(value.createdDate);
				$('#modal-answers')
							.append($('<hr>')
							)
							.append($('<div>')
								.append($('<a>')
									.attr('href','/tourofall/users/'+value.userId)
									.append(value.lastName+value.firstName)
								)
								.append(" "+(date.getFullYear())+'-'+(date.getMonth()+1)+'-'+(date.getDate()))
								.append($('<br />'))
								.append(value.content)
							)
			});
			$('form[name="answer-register"] input[name="questionId"]').attr('value',questionRenderingModel.questionId);
			
		},
		error: function(xhr){
			alert("An error occured: " + xhr.status + " " + xhr.statusText);
		}
	});
}


function sendAnswerRegistration(){
	$('#register-answer').click(function(){
		var form = $('form[name="answer-register"]');
		
		var questionId = form.find('input[name="questionId"]').val();
		
		var content = form.find('textarea[name="answer-write"]').val();
		
		var formUrl = form.attr('action');
		
		var formData = {"content":content,"questionId":questionId};
		
		var jsonData = JSON.stringify(formData);
		var csrf_token = form.find('input[name="_csrf"]').val();
		
		//alert(formUrl+", "+jsonData+", "+csrf_token);
		
		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			if (!options.crossDomain) {
			  return jqXHR.setRequestHeader('X-CSRF-Token', csrf_token);
			}
		});
		
		
		var request = $.ajax({
			type:"POST",
			url:formUrl,
			data:jsonData,
			contentType:"application/json",
			dataType:"json",
			success: function(result,status,xhr){
				var date = new Date(result.createdDate);
				$('#modal-answers')
							.append($('<hr>')
							)
							.append($('<div>')
								.append(result.lastName+result.firstName+" ")
								.append((date.getFullYear())+'-'+(date.getMonth()+1)+'-'+(date.getDate()))
								.append($('<br />'))
								.append(result.content)
							);
							
							answerCount = $('#question #modal-answers span.badge').text();
							$('form[name="answer-register"]').find('textarea').val('').end();
							
							$('#question #modal-answers span.badge').text('');
							$('#question #modal-answers span.badge').text(parseInt(answerCount)+1);
							
							selected.find('span.badge').text('');
							selected.find('span.badge').text(parseInt(answerCount)+1);
			},
			error: function(xhr){
				switch(xhr.status){
				case 200: // 로그인 안한 경우
					window.location.href = "/tourofall/signin";
					break;
				case 204: // NO CONTENT
					var jsonResponse = JSON.parse(xhr.responseText);
					//alert(jsonResponse.message);
					break;
				case 409: // CONFLICT
					var jsonResponse = JSON.parse(xhr.responseText);
					for(var i=0;i < jsonResponse.length;i++){
						switch(jsonResponse[i].field){
						case "content":
							$('form[name="answer-register"] #content-error').append(jsonResponse[i].defaultMessage);
							break;
						}
					}
					break;
				}
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
	});
	
}
$(document).ready(sendAnswerRegistration);

$(document).ready(function(){
	$('#review_write').on('hidden.bs.modal', function () {
	    $(this).find('input[type="text"],textarea').val('').end();
	    $(this).find('.error-msg').text("");
	    $(this).find('input[type="radio"]').prop('checked', false);

	});
	
	$('#register-question').on('hidden.bs.modal', function () {
	    $(this).find('input[type="text"],textarea').val('').end();
	    $(this).find('.error-msg').text("");
	});
	
	$('#question').on('hidden.bs.modal', function () {
	    $(this).find('.modal-header .modal-title').text('');
		$(this).find('.modal-body').text('');
		$("#modal-answers").text('');
		$(this).find('input[type="hidden"],textarea').val('').end();
	    $(this).find('.error-msg').text("");
	    
	});
	
	
	
});