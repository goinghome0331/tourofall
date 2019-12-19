function fixedManubar() {
    if ($(".navbar").offset().top > 50) {
        $(".menu-container").addClass("menu-container-fixed");
    } else {
        $(".menu-container").removeClass("menu-container-fixed");
    }
}

$(window).scroll(fixedManubar);
$(document).ready(fixedManubar);
