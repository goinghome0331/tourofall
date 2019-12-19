function getUrlPath() {
	var a = location.pathname.split("/");
	//alert(a[2]);
	if(a[2] == "recommend"){
		$("div.collapse ul.nav li #recommend").addClass("selected");
	}
	if(a[2] == "eval" && a[3] == "evalmore"){
		$("div.collapse ul.nav li #evalmore").addClass("selected");
	}
}
$(document).ready(getUrlPath);