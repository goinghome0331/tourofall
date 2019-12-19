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
				checkedForm.find('article div').addClass('eval-unit-is-checked');
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