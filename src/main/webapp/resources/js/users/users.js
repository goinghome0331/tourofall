$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });
    $('.nav-tabs a').on('shown.bs.tab', function(event){
        var x = $(event.target).text();         // active tab
        var y = $(event.relatedTarget).text();  // previous tab
        $(".act span").text(x);
        $(".prev span").text(y);
    });
});

$(document).ready(function(){
	$('ul.pagination li a').on('click',function(){
		var pageNo;
		var pIndex = $('#page-index').find('input[name="currentPageNo"]').attr('value');
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
		$('#page-index').find('input[name="currentPageNo"]').attr('value', pageNo);
		
		var userId = $('#page-index').find('input[name="userId"]').val();
		var formUrl = "/tourofall/users/"+userId+"/usereval";
		
		
		
		var parameter = new Object();
		
		parameter.userId = userId;
		parameter.pageNo = pageNo;
		
		var pData = $.param(parameter);
	
		
		var form = $('form[name="request-eval"]');
		
		var csrf_token_name = form.find('input[type="hidden"]').attr('name');
		var csrf_token_value = form.find('input[type="hidden"]').attr('value');
		
		var urlForMakingForm = $('form[name="request-eval"]').attr('action');
		
		//alert(pageNo + ", " + formUrl + ", " + pData + ", " + csrf_token_name + ": " + csrf_token_value);
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				$("#eval-list").empty();
				
				$('#page-index ul.pagination').empty();
				
				
				var evaluationRenderingModels = result.evaluationRenderingModels;
				//alert(evaluationRenderingModels.length);
				
				$.each( evaluationRenderingModels, function(index, value){
					
					var tIndex = ((parseInt(pageNo,10)-1)*20+index);
					
					var radio5 = $('<input>')
					.attr('type','radio')
					.attr('id','star5-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','5')
					.attr('onclick','sendEvaluation()');
					
					
					var radio45 = $('<input>')
					.attr('type','radio')
					.attr('id','star4half-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','4.5')
					.attr('onclick','sendEvaluation()');
					
					
					var radio4 = $('<input>')
		    			.attr('type','radio')
		    			.attr('id','star4-'+tIndex)
		    			.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    			.attr('value','4')
		    			.attr('onclick','sendEvaluation()');
					
					var radio35 = $('<input>')
					.attr('type','radio')
					.attr('id','star3half-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','3.5')
					.attr('onclick','sendEvaluation()');
					
					var radio3 = $('<input>')
					.attr('type','radio')
					.attr('id','star3-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','3')
					.attr('onclick','sendEvaluation()');
					
					var radio25 = $('<input>')
					.attr('type','radio')
					.attr('id','star2half-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','2.5')
					.attr('onclick','sendEvaluation()');
					
					var radio2 = $('<input>')
					.attr('type','radio')
					.attr('id','star2-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','2')
					.attr('onclick','sendEvaluation()');
					
					var radio15 = $('<input>')
					.attr('type','radio')
					.attr('id','star1half-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','1.5')
					.attr('onclick','sendEvaluation()');
					
					var radio1 = $('<input>')
					.attr('type','radio')
					.attr('id','star1-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','1')
					.attr('onclick','sendEvaluation()');
					
					var radio05 = $('<input>')
					.attr('type','radio')
					.attr('id','starhalf-'+tIndex)
					.attr('name','evaluationRenderingModels['+ tIndex +'].score')
					.attr('value','0.5')
					.attr('onclick','sendEvaluation()');
					
					
					switch(value.score){
					case 5:
						radio5.attr('checked','true');
						break;
					case 4.5:
						radio45.attr('checked','true');
						break;
					case 4:
						radio4.attr('checked','true');
						break;
					case 3.5:
						radio35.attr('checked','true');
						break;
					case 3:
						radio3.attr('checked','true');
						break;
					case 2.5:
						radio25.attr('checked','true');
						break;
					case 2:
						radio2.attr('checked','true');
						break;
					case 1.5:
						radio15.attr('checked','true');
						break;
					case 1:
						radio1.attr('checked','true');
						break;
					case 0.5:
						radio05.attr('checked','true');
						braek;
					}
					var userScore;
					if(result.userScoreList[index] != 0){
						userScore = $('<div>')
						.attr('class','text-center')
						.append("평점 : "+result.userScoreList[index]);
					}else{
						userScore = "";
					}
					
					
					
					$("#eval-list")
		    		.append($('<div>')
		    		.attr('class','col-lg-3 col-md-4 col-sm-6')
		    			.append($('<form>')
		    			.attr('name','request-eval')
		    			.attr('action',urlForMakingForm)
		    				.append($('<input>')
		    					.attr('type','hidden')
		    					.attr('name',csrf_token_name)
		    					.attr('value',csrf_token_value)
		    				)
		    				.append($('<input>')
		    					.attr('type','hidden')
		    					.attr('name','itemId')
		    					.attr('value',value.itemId)
		    				)
		    				.append($('<div>')
		    					.attr('class','eval-unit')
		    					.append($('<article>')
		    						.attr('class','eval-unit-card eval-unit-card-fixed eval-unit-card-has-img eval-unit-card-article')
		    						.append($('<div>')
		    							.attr('class','eval-unit-card-mask')
		    							.append($('<a>')
		    								.attr('href','/tourofall/dest/info/'+value.itemId)
		    								.append($('<figure>')
		    									.attr('class','eval-unit-card-figure')
		    									.append($('<img>')
		    										.attr('src',value.imageUrl)
		    										.attr('class','img-rounded')
		    										.attr('onerror','this.style.display=none')
		    									)
		    								)
		    							)
		    							.append($('<div>')
		    								.attr('class','eval-unit-card-content')
		    								.append($('<div>')
		    									.attr('class','eval-unit-card-content-title')
		    									.append(value.title)
		    								)
		    								.append($('<div>')
		    									.attr('class','eval-unit-card-content-context')
		    									.append('평점')
		    								)
		    								.append($('<div>')
		    										.attr('class','rating')
		    										.append(radio5
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star5-'+tIndex)
		    											.attr('title','Awesome - 5 stars')
		    										)
		    										
		    										.append(radio45
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star4half-'+tIndex)
		    											.attr('title','Pretty good - 4.5 stars')
		    										)
		    										
		    										.append(radio4
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star4-'+tIndex)
		    											.attr('title','Pretty good - 4 stars')
		    										)
		    										
		    										.append(radio35
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star3half-'+tIndex)
		    											.attr('title','Meh - 3.5 stars')
		    										)
		    										
		    										.append(radio3
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star3-'+tIndex)
		    											.attr('title','Meh - 3 stars')
		    										)
		    										
		    										.append(radio25
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star2half-'+tIndex)
		    											.attr('title','Kinda bad - 2.5 stars')
		    										)
		    										
		    										.append(radio2
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star2-'+tIndex)
		    											.attr('title','Kinda bad - 2 stars')
		    										)
		    										
		    										.append(radio15
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star1half-'+tIndex)
		    											.attr('title','Meh - 1.5 stars')
		    										)
		    										
		    										.append(radio1
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star1-'+tIndex)
		    											.attr('title','Sucks big time - 1 star')
		    										)
		    										
		    										.append(radio05
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','starhalf-'+tIndex)
		    											.attr('title','Sucks big time - 0.5 stars')
		    										)
		    								)
		    							)
		    						)
		    					)
		    				)
		    				.append(userScore
		    				)
		    			)//form
		    		);
					
				});
				
				var indexList = result.indexList;
				var v;
				if(result.pageNo - 5 >= 1){
					v = result.pageNo - 5;
					$('#page-index ul.pagination')
						.append($('<li>')
							.append($('<a>')
								.attr('onclick','getUserEvaluationRenderingModels('+v+')')
								.attr('href','#qna')
								.append("<<")
							)
						);
				} 
				if(result.pageNo - 1 >= 1){
					v = result.pageNo - 1;
					$('#page-index ul.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getUserEvaluationRenderingModels('+v+')')
							.attr('href','#qna')
							.append("<")
						)
					);
				} 
				
				$.each( indexList, function(index, value){
					v = value;
					$('#page-index ul.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getUserEvaluationRenderingModels('+v+')')
							.attr('href','#qna')
							.append(value)
						)
					);
				});
				
				if(result.pageNo + 1 <= result.totalPage){
					v = result.pageNo + 1;
					$('#page-index ul.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getUserEvaluationRenderingModels('+v+')')
							.attr('href','#qna')
							.append(">")
						)
					);
				}
				if(result.pageNo + 5 <= result.totalPage){
					v = result.pageNo + 5;
					$('#page-index ul.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('href','#qna')
							.attr('onclick','getUserEvaluationRenderingModels('+v+')')
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
function getUserEvaluationRenderingModels(v){
	var pageNo;
	var pIndex = $('#page-index').find('input[name="currentPageNo"]').attr('value');
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
	$('#page-index').find('input[name="currentPageNo"]').attr('value', pageNo);
	
	var userId = $('#page-index').find('input[name="userId"]').val();
	var formUrl = "/tourofall/users/"+userId+"/usereval";
	
	
	
	var parameter = new Object();
	
	parameter.userId = userId;
	parameter.pageNo = pageNo;
	
	var pData = $.param(parameter);

	
	var form = $('form[name="request-eval"]');
	
	var csrf_token_name = form.find('input[type="hidden"]').attr('name');
	var csrf_token_value = form.find('input[type="hidden"]').attr('value');
	
	var urlForMakingForm = $('form[name="request-eval"]').attr('action');
	
	//alert(pageNo + ", " + formUrl + ", " + pData + ", " + csrf_token_name + ": " + csrf_token_value);
	
	
	var request = $.ajax({
		type:"GET",
		url:formUrl,
		contentType:"text/plain",
		data:pData,
		dataType:"json",
		success: function(result,status,xhr){
			$("#eval-list").empty();
			
			$('#page-index ul.pagination').empty();
			
			
			var evaluationRenderingModels = result.evaluationRenderingModels;
			//alert(evaluationRenderingModels.length);
			
			$.each( evaluationRenderingModels, function(index, value){
				
				var tIndex = ((parseInt(pageNo,10)-1)*20+index);
				
				var radio5 = $('<input>')
				.attr('type','radio')
				.attr('id','star5-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','5')
				.attr('onclick','sendEvaluation()');
				
				
				var radio45 = $('<input>')
				.attr('type','radio')
				.attr('id','star4half-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','4.5')
				.attr('onclick','sendEvaluation()');
				
				
				var radio4 = $('<input>')
	    			.attr('type','radio')
	    			.attr('id','star4-'+tIndex)
	    			.attr('name','evaluationRenderingModels['+ tIndex +'].score')
	    			.attr('value','4')
	    			.attr('onclick','sendEvaluation()');
				
				var radio35 = $('<input>')
				.attr('type','radio')
				.attr('id','star3half-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','3.5')
				.attr('onclick','sendEvaluation()');
				
				var radio3 = $('<input>')
				.attr('type','radio')
				.attr('id','star3-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','3')
				.attr('onclick','sendEvaluation()');
				
				var radio25 = $('<input>')
				.attr('type','radio')
				.attr('id','star2half-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','2.5')
				.attr('onclick','sendEvaluation()');
				
				var radio2 = $('<input>')
				.attr('type','radio')
				.attr('id','star2-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','2')
				.attr('onclick','sendEvaluation()');
				
				var radio15 = $('<input>')
				.attr('type','radio')
				.attr('id','star1half-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','1.5')
				.attr('onclick','sendEvaluation()');
				
				var radio1 = $('<input>')
				.attr('type','radio')
				.attr('id','star1-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','1')
				.attr('onclick','sendEvaluation()');
				
				var radio05 = $('<input>')
				.attr('type','radio')
				.attr('id','starhalf-'+tIndex)
				.attr('name','evaluationRenderingModels['+ tIndex +'].score')
				.attr('value','0.5')
				.attr('onclick','sendEvaluation()');
				
				
				switch(value.score){
				case 5:
					radio5.attr('checked','true');
					break;
				case 4.5:
					radio45.attr('checked','true');
					break;
				case 4:
					radio4.attr('checked','true');
					break;
				case 3.5:
					radio35.attr('checked','true');
					break;
				case 3:
					radio3.attr('checked','true');
					break;
				case 2.5:
					radio25.attr('checked','true');
					break;
				case 2:
					radio2.attr('checked','true');
					break;
				case 1.5:
					radio15.attr('checked','true');
					break;
				case 1:
					radio1.attr('checked','true');
					break;
				case 0.5:
					radio05.attr('checked','true');
					braek;
				}
				
				var userScore;
				if(result.userScoreList[index] != 0){
					userScore = $('<div>')
					.attr('class','text-center')
					.append("평점 : "+result.userScoreList[index]);
				}else{
					userScore = "";
				}
				
				$("#eval-list")
	    		.append($('<div>')
	    		.attr('class','col-lg-3 col-md-4 col-sm-6')
	    			.append($('<form>')
	    			.attr('name','request-eval')
	    			.attr('action',urlForMakingForm)
	    				.append($('<input>')
	    					.attr('type','hidden')
	    					.attr('name',csrf_token_name)
	    					.attr('value',csrf_token_value)
	    				)
	    				.append($('<input>')
	    					.attr('type','hidden')
	    					.attr('name','itemId')
	    					.attr('value',value.itemId)
	    				)
	    				.append($('<div>')
	    					.attr('class','eval-unit')
	    					.append($('<article>')
	    						.attr('class','eval-unit-card eval-unit-card-fixed eval-unit-card-has-img eval-unit-card-article')
	    						.append($('<div>')
	    							.attr('class','eval-unit-card-mask')
	    							.append($('<a>')
	    								.attr('href','/tourofall/dest/info/'+value.itemId)
	    								.append($('<figure>')
	    									.attr('class','eval-unit-card-figure')
	    									.append($('<img>')
	    										.attr('src',value.imageUrl)
	    										.attr('class','img-rounded')
	    										.attr('onerror','this.style.display=none')
	    									)
	    								)
	    							)
	    							.append($('<div>')
	    								.attr('class','eval-unit-card-content')
	    								.append($('<div>')
	    									.attr('class','eval-unit-card-content-title')
	    									.append(value.title)
	    								)
	    								.append($('<div>')
	    									.attr('class','eval-unit-card-content-context')
	    									.append('평점')
	    								)
	    								.append($('<div>')
	    										.attr('class','rating')
	    										.append(radio5
	    										)
	    										.append($('<label>')
	    											.attr('class','full')
	    											.attr('for','star5-'+tIndex)
	    											.attr('title','Awesome - 5 stars')
	    										)
	    										
	    										.append(radio45
	    										)
	    										.append($('<label>')
	    											.attr('class','half')
	    											.attr('for','star4half-'+tIndex)
	    											.attr('title','Pretty good - 4.5 stars')
	    										)
	    										
	    										.append(radio4
	    										)
	    										.append($('<label>')
	    											.attr('class','full')
	    											.attr('for','star4-'+tIndex)
	    											.attr('title','Pretty good - 4 stars')
	    										)
	    										
	    										.append(radio35
	    										)
	    										.append($('<label>')
	    											.attr('class','half')
	    											.attr('for','star3half-'+tIndex)
	    											.attr('title','Meh - 3.5 stars')
	    										)
	    										
	    										.append(radio3
	    										)
	    										.append($('<label>')
	    											.attr('class','full')
	    											.attr('for','star3-'+tIndex)
	    											.attr('title','Meh - 3 stars')
	    										)
	    										
	    										.append(radio25
	    										)
	    										.append($('<label>')
	    											.attr('class','half')
	    											.attr('for','star2half-'+tIndex)
	    											.attr('title','Kinda bad - 2.5 stars')
	    										)
	    										
	    										.append(radio2
	    										)
	    										.append($('<label>')
	    											.attr('class','full')
	    											.attr('for','star2-'+tIndex)
	    											.attr('title','Kinda bad - 2 stars')
	    										)
	    										
	    										.append(radio15
	    										)
	    										.append($('<label>')
	    											.attr('class','half')
	    											.attr('for','star1half-'+tIndex)
	    											.attr('title','Meh - 1.5 stars')
	    										)
	    										
	    										.append(radio1
	    										)
	    										.append($('<label>')
	    											.attr('class','full')
	    											.attr('for','star1-'+tIndex)
	    											.attr('title','Sucks big time - 1 star')
	    										)
	    										
	    										.append(radio05
	    										)
	    										.append($('<label>')
	    											.attr('class','half')
	    											.attr('for','starhalf-'+tIndex)
	    											.attr('title','Sucks big time - 0.5 stars')
	    										)
	    								)
	    							)
	    						)
	    					)
	    				)
	    				.append(userScore
		    			)
	    			)//form
	    		);
				
			});
			
			var indexList = result.indexList;
			var v;
			if(result.pageNo - 5 >= 1){
				v = result.pageNo - 5;
				$('#page-index ul.pagination')
					.append($('<li>')
						.append($('<a>')
							.attr('onclick','getUserEvaluationRenderingModels('+v+')')
							.attr('href','#qna')
							.append("<<")
						)
					);
			} 
			if(result.pageNo - 1 >= 1){
				v = result.pageNo - 1;
				$('#page-index ul.pagination')
				.append($('<li>')
					.append($('<a>')
						.attr('onclick','getUserEvaluationRenderingModels('+v+')')
						.attr('href','#qna')
						.append("<")
					)
				);
			} 
			
			$.each( indexList, function(index, value){
				v = value;
				$('#page-index ul.pagination')
				.append($('<li>')
					.append($('<a>')
						.attr('onclick','getUserEvaluationRenderingModels('+v+')')
						.attr('href','#qna')
						.append(value)
					)
				);
			});
			
			if(result.pageNo + 1 <= result.totalPage){
				v = result.pageNo + 1;
				$('#page-index ul.pagination')
				.append($('<li>')
					.append($('<a>')
						.attr('onclick','getUserEvaluationRenderingModels('+v+')')
						.attr('href','#qna')
						.append(">")
					)
				);
			}
			if(result.pageNo + 5 <= result.totalPage){
				v = result.pageNo + 5;
				$('#page-index ul.pagination')
				.append($('<li>')
					.append($('<a>')
						.attr('href','#qna')
						.attr('onclick','getUserEvaluationRenderingModels('+v+')')
						.append(">>")
					)
				);
			}
		},
		error: function(xhr){
			alert("An error occured: " + xhr.status + " " + xhr.statusText);
		}
	});
}

function sendEvaluation(){
	$('input[type=radio]').on('change',function(){
		var checkedForm = $(this).closest('form');
		
		var itemId = checkedForm.find('input[name="itemId"]').val();
		var score = $(this).val();
		
		var formUrl = checkedForm.attr("action");
		
		var formData={"itemId":itemId,"score":score};
		
		var jsonData = JSON.stringify(formData);
		
		var csrf_token = checkedForm.find(':first-child').val();
		
		
		
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
				$(".js-result-section .result-section-text").empty();
				$(".js-result-section .result-section-text").append(result.message);
				$(".js-result-section").removeClass("result-section-hide");
				setTimeout(function(){
					$(".js-result-section").addClass("result-section-hide");
				}, 1500);
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
}
$(document).ready(sendEvaluation);


function getUserReviewRenderingModels(){
	$('#getUserReviewMore').click(function(){
		var clickedForm = $('form[name="userreviewmore"]');
		var index = clickedForm.find('input[name="index"]').val();
		var formUrl = clickedForm.attr("action");
		
		var userId = clickedForm.find('input[name="userId"]').val();
		var myId = clickedForm.find('input[name="myId"]').val();
		
		
		var parameter = new Object();
		
		parameter.index = index;
		
		clickedForm.find("input[name=index]").attr("value",parseInt(index,10)+1);
	
		var pData = $.param(parameter);
		
		var csrf_token_name = $('form[name="request-eval"] :first-child').attr('name');
		var csrf_token_value = $('form[name="request-eval"] :first-child').attr('value');
		
		//alert(csrf_token_name + ', ' + csrf_token_value);
		//alert(formUrl + " ? " + pData);
		
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				var userReviewRenderingModels = result.userReviewRenderingModels;
				if(result.nextIndex == false){
					$('#getUserReviewMore').css('display','none');
				}
				
				$.each(userReviewRenderingModels , function(index, value){
									var iScore = parseInt(value.score, 10);
									var rating = $('<div>');
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
									
									var tIndex = (parseInt(index,10)*6);
									
									var date = new Date(value.createdDate);
									
									var radio5 = $('<input>')
									.attr('type','radio')
									.attr('id','star5-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','5')
									.attr('onclick','sendEvaluation()');
									
									
									var radio45 = $('<input>')
									.attr('type','radio')
									.attr('id','star4half-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','4.5')
									.attr('onclick','sendEvaluation()');
									
									
									var radio4 = $('<input>')
						    			.attr('type','radio')
						    			.attr('id','star4-'+tIndex)
						    			.attr('name','userReviewRenderingModels['+ tIndex +'].score')
						    			.attr('value','4')
						    			.attr('onclick','sendEvaluation()');
									
									var radio35 = $('<input>')
									.attr('type','radio')
									.attr('id','star3half-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','3.5')
									.attr('onclick','sendEvaluation()');
									
									var radio3 = $('<input>')
									.attr('type','radio')
									.attr('id','star3-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','3')
									.attr('onclick','sendEvaluation()');
									
									var radio25 = $('<input>')
									.attr('type','radio')
									.attr('id','star2half-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','2.5')
									.attr('onclick','sendEvaluation()');
									
									var radio2 = $('<input>')
									.attr('type','radio')
									.attr('id','star2-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','2')
									.attr('onclick','sendEvaluation()');
									
									var radio15 = $('<input>')
									.attr('type','radio')
									.attr('id','star1half-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','1.5')
									.attr('onclick','sendEvaluation()');
									
									var radio1 = $('<input>')
									.attr('type','radio')
									.attr('id','star1-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','1')
									.attr('onclick','sendEvaluation()');
									
									var radio05 = $('<input>')
									.attr('type','radio')
									.attr('id','starhalf-'+tIndex)
									.attr('name','userReviewRenderingModels['+ tIndex +'].score')
									.attr('value','0.5')
									.attr('onclick','sendEvaluation()');
									
									
									switch(value.score){
									case 5:
										radio5.attr('checked','true');
										break;
									case 4.5:
										radio45.attr('checked','true');
										break;
									case 4:
										radio4.attr('checked','true');
										break;
									case 3.5:
										radio35.attr('checked','true');
										break;
									case 3:
										radio3.attr('checked','true');
										break;
									case 2.5:
										radio25.attr('checked','true');
										break;
									case 2:
										radio2.attr('checked','true');
										break;
									case 1.5:
										radio15.attr('checked','true');
										break;
									case 1:
										radio1.attr('checked','true');
										break;
									case 0.5:
										radio05.attr('checked','true');
										braek;
									}
									
									var evalContent;
									if(userId == myId){
										evalContent = "";
									}else{
										evalContent = $('<div>')
										.attr('class','review-eval-content')
										.append($('<div>')
											.attr('class','review-eval-content-rating')
											.append($('<div>')
												.attr('class','review-eval-content-context')
												.append('평점')
											)
											.append($('<div>')
												.attr('class','review-rating')
													.append(radio5
													)
													.append($('<label>')
														.attr('class','full')
														.attr('for','star5-'+tIndex)
														.attr('title','Awesome - 5 stars')
													)
													.append(radio45
													)
													.append($('<label>')
														.attr('class','half')
														.attr('for','star4half-'+tIndex)
														.attr('title','Pretty good - 4.5 stars')
													)
													.append(radio4
													)
													.append($('<label>')
														.attr('class','full')
														.attr('for','star4-'+tIndex)
														.attr('title','Pretty good - 4 stars')
													)
													.append(radio35
													)
													.append($('<label>')
														.attr('class','half')
														.attr('for','star3half-'+tIndex)
														.attr('title','Meh - 3.5 stars')
													)    										
													.append(radio3
													)
													.append($('<label>')
														.attr('class','full')
														.attr('for','star3-'+tIndex)
														.attr('title','Meh - 3 stars')
													)
													.append(radio25
													)
													.append($('<label>')
														.attr('class','half')
														.attr('for','star2half-'+tIndex)
														.attr('title','Kinda bad - 2.5 stars')
													)
													.append(radio2
													)
													.append($('<label>')
														.attr('class','full')
														.attr('for','star2-'+tIndex)
														.attr('title','Kinda bad - 2 stars')
													)
		    										.append(radio15
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star1half-'+tIndex)
		    											.attr('title','Meh - 1.5 stars')
		    										)
		    										.append(radio1
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star1-'+tIndex)
		    											.attr('title','Sucks big time - 1 star')
		    										)
							
		    										.append(radio05
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','starhalf-'+tIndex)
		    											.attr('title','Sucks big time - 0.5 stars')
		    										)
											)	
										);
									}
									
									
									
									$("#userreviewline")
									.append($('<div>')
										.attr('class','col-sm-6 row-gap')
										.append($('<form>')
											.attr('name','request-eval')
											.attr('action','/tourofall/eval/evalmore')
											.append($('<input>')
												.attr('type','hidden')
												.attr('name',csrf_token_name)
												.attr('value',csrf_token_value)
											)
											.append($('<input>')
												.attr('type','hidden')
												.attr('name',"itemId")
												.attr('value',value.itemId)
											)
											.append($('<div>')
												.attr('class','review-card card-body')
												.append($('<div>')
													.attr('class','review-card-wrapper')
													.append($('<div>')
														.attr('class','review-card-figure review-img')
														.append($('<img>')
															.attr('src',value.imageUrl)
															.attr('width',210)
															.attr('height',348)
														)
														.append(evalContent
														)
													)
													.append($('<div>')
														.attr('class','review-card-meta review-card-meta-overflow media-body')
														.append($('<div>')
															.attr('class','review-card-title')
															.append(value.itemTitle)
														)
														.append($('<div>')
															.attr('class','review-card-address')
															.append(value.address)
														)
														.append($('<div>')
															.append('평점')
															.append($('<br/>'))
															.append(rating)
														)
														.append($('<div>')
															.attr('class','review-card-content')
															.append($('<p>')
																.append(value.title)
															)
															.append(value.content)
														)	
													)
													.append($('<div>')
														.attr('class','review-card-date')
														.append((date.getFullYear())+'년'+(date.getMonth()+1)+'월'+(date.getDate())+'일')
													)	
												)
											)
										)	
									)
				});
				
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
	
}
$(document).ready(getUserReviewRenderingModels);

function getAnswerRenderingModels(){
	$('#getUserAnswerMore').click(function(){
		var clickedForm = $('form[name="useranswermore"]');
		
		var index = clickedForm.find('input[name="index"]').val();
		
		var formUrl = clickedForm.attr("action");
		
		var userId = clickedForm.find('input[name="userId"]').val();
		
		var parameter = new Object();
		
		parameter.index = index;
		
		clickedForm.find("input[name=index]").attr("value",parseInt(index,10)+1);
	
		var pData = $.param(parameter);
		
		//alert(formUrl + " ? " + pData);
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			data:pData,
			dataType:"json",
			success: function(result,status,xhr){
				var answerRenderingModels = result.answerRenderingModels;
				if(result.nextIndex == false){
					$('#getUserAnswerMore').css('display','none');
				}
				
				$.each(answerRenderingModels , function(index, value){
					
					var answerdate = new Date(value.createdDate);
					var questiondate = new Date(value.questionCreatedDate);
					$("#answer-line")
						.append($('<div>')
							.attr('class','answers-content js-answers-content'+value.answerId)
							.append($('<div>')
								.attr('class','answers-section-wrapper')
								.append($('<div>')
									.attr('class','answers-section')
									.append($('<div>')
										.attr('class','answers-section-header')
										.append($('<b>')
											.append(value.questionTitle)
										)
										.append($('<p>')
											.attr('class','pull-right')
											.append(" | "+(answerdate.getFullYear())+'년'+(answerdate.getMonth()+1)+'월'+(answerdate.getDate())+'일')
										)
									)
									.append($('<div>')
										.attr('class','answers-section-body')
										.append(value.content)
									)
									.append($('<div>')
										.attr('class','answers-section-footer')
										.append((questiondate.getFullYear())+'년'+(questiondate.getMonth()+1)+'월'+(questiondate.getDate())+'일')
										.append($('<button>')
											.attr('id','delete-answer')
											.attr('class','js-delete btn btn-default')
											.attr('onclick','deleteAnswer(event)')
											.append('삭제')
											.append($('<input>')
												.attr('type','hidden')
												.attr('name','answerId')
												.attr('value',value.answerId)
											)
										)
									)
								)
							)	
						)
				});
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
}
$(document).ready(getAnswerRenderingModels);


$(document).ready(function(){
	$("button.js-delete").click(function(){
		var answerId = $(this).find('input[name="answerId"]').attr('value');
		
		formUrl = "/tourofall/users/deleteanswer/"+answerId;
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			dataType:"json",
			success: function(result,status,xhr){
				$('.js-answers-content'+answerId).empty();
				var count = $("#answer-badge").text();
				count = parseInt(count,10)-1;
				$("#answer-badge").text(count);
				alert(result.message);
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
				
	});
});
function deleteAnswer(event){	
	var answerId = $(event.target).find('input[name="answerId"]').attr('value');
	formUrl = "/tourofall/users/deleteanswer/"+answerId;
			
	var request = $.ajax({
			type:"GET",
			url:formUrl,
			contentType:"text/plain",
			dataType:"json",
			success: function(result,status,xhr){
				$('.js-answers-content'+answerId).empty();
				var count = $("#answer-badge").text();
				count = parseInt(count,10)-1;
				$("#answer-badge").text(count);
				alert(result.message);
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
}



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
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
	});
})