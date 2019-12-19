function collapseNavbar() {
    if ($(".navbar").offset().top > 400) {
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
	if(a[2] == ""){
		$("nav.navbar .navbar-header .navbar-brand").addClass("selected");
	}
}
$(document).ready(getUrlPath);