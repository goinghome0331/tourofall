function collapseNavbar() {
    if ($(".navbar").offset().top > 200) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
}

$(window).scroll(collapseNavbar);
$(document).ready(collapseNavbar);

function getUrlPath() {
	var a = location.pathname.split("/");
	//alert(a[2]);
	if(a[2] == "myinfo"){
		$("div.nav li #myinfo").addClass("selected");
	}
}
$(document).ready(getUrlPath);