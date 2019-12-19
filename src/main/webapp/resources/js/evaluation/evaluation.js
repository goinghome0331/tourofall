function selectCurrentListItem(){
	var urlTemp = window.location.search;
	if(urlTemp == ""){
    	$("div.container .row .col-sm-2 .list-group #sub-navbar-1").addClass("active");
    }
	
	
	var sPageURL = window.location.search.substring(1);
	
    var sURLVariables = sPageURL.split('&');
    var sParameterName1 = sURLVariables[0].split('=');
    var sParameterName2 = sURLVariables[1].split('=');
    
    
    if(sParameterName1[1] == "A01"){
    	switch(sParameterName2[1]){
    	case "A0101":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-1").addClass("active");
    		break;
    	}
    }else if(sParameterName1[1] == "A02"){
    	switch(sParameterName2[1]){
    	case "A0201":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-2").addClass("active");
    		break;
    	case "A0202":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-3").addClass("active");
    		break;
    	case "A0203":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-4").addClass("active");
    		break;
    	case "A0204":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-5").addClass("active");
    		break;
    	case "A0205":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-6").addClass("active");
    		break;
    	case "A0206":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-7").addClass("active");
    		break;
    	}
    }else if(sParameterName1[1] == "A03"){
    	switch(sParameterName2[1]){
    	case "A0302":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-8").addClass("active");
    		break;
    	case "A0303":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-9").addClass("active");
    		break;
    	case "A0304":
    		$("div.container .row .col-sm-2 .list-group #sub-navbar-10").addClass("active");
    		break;
    	}
    }
}

$(document).ready(selectCurrentListItem);
var preValue = 0;
function bottomScrollEvent(){
	
	if ($(window).scrollTop() > $(document).height() -20 - $(window).height() && preValue < $(document).height() -20 - $(window).height()) {
		
		var formUrl = $("#scroll-form").attr("action");
		
		var parameter = new Object();
		var pageValue = $("#scroll-form").find("input[name=request-page]").attr("value");
		parameter.page = parseInt(pageValue,10)+1;
		
		 $("#scroll-form").find("input[name=request-page]").attr("value",parseInt(pageValue,10)+1);
		
		parameter.cat1 = $("#scroll-form").find("input[name=request-cat1]").val();
		parameter.cat2 = $("#scroll-form").find("input[name=request-cat2]").val();
		
		var pData = $.param(parameter);
		
		var csrf_token_name = $('input[type="hidden"]').attr('name');
		var csrf_token_value = $('input[type="hidden"]').attr('value');
		
		var urlForMakingForm = $('form[name="request-eval"]').attr('action');
		
		
		var request = $.ajax({
			type:"GET",
			url:formUrl,
			data: pData,
			contentType:"text/plain",
			dataType:"json",
			success:function(result,status,xhr){
				$.each( result, function(index, value){
					var tIndex = (parseInt(pageValue,10)*12+index);
					$("#eval-list")
		    		.append($('<div>')
		    		.attr('class','col-md-4 col-sm-6')
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
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star5-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','5')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star5-'+tIndex)
		    											.attr('title','Awesome - 5 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star4half-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','4.5')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star4half-'+tIndex)
		    											.attr('title','Pretty good - 4.5 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star4-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','4')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star4-'+tIndex)
		    											.attr('title','Pretty good - 4 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star3half-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','3.5')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star3half-'+tIndex)
		    											.attr('title','Meh - 3.5 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star3-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','3')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star3-'+tIndex)
		    											.attr('title','Meh - 3 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star2half-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','2.5')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star2half-'+tIndex)
		    											.attr('title','Kinda bad - 2.5 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star2-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','2')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star2-'+tIndex)
		    											.attr('title','Kinda bad - 2 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star1half-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','1.5')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','half')
		    											.attr('for','star1half-'+tIndex)
		    											.attr('title','Meh - 1.5 stars')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','star1-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','1')
		    											.attr('onclick','sendEvaluation()')
		    										)
		    										.append($('<label>')
		    											.attr('class','full')
		    											.attr('for','star1-'+tIndex)
		    											.attr('title','Sucks big time - 1 star')
		    										)
		    										
		    										.append($('<input>')
		    											.attr('type','radio')
		    											.attr('id','starhalf-'+tIndex)
		    											.attr('name','evaluationRenderingModels['+ tIndex +'].score')
		    											.attr('value','0.5')
		    											.attr('onclick','sendEvaluation()')
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
		    			)//form
		    		);
					
					
				});
			},
			error:function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
			}
		});
		
		
	}
	preValue = $(window).scrollTop();
}
$(window).scroll(bottomScrollEvent);
$('body').bind('touchmove', function(e) { 
	bottomScrollEvent();
});

