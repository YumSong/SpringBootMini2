function iframeOnLoad(t) {
}
function openUrl(url){
	$("#ifr").attr("src",url);
	return false;
}

$(document).ready(function() {
	var reHeight = function() {
		var b = $(window);
		var bh = b.height();
		$("#left-part").height(bh);
		var lt=$(".right-head").height();
		$(".right-content-bg").height(bh-lt-2);
	};
	reHeight();
	// 窗口高度动态调整
	$(window).resize(reHeight);

	// 左边菜单栏
	$(".list-one").click(function() {
		var t = $(this).next("ul");
		$("#left-part .menu-list  ul").each(function(i, o) {
			if ($(o).is($(t))) {
				$(t).toggle();
			} else {
				$(o).hide();
			}
			return;
		});
	});

	// 弹窗
	$("#right-part  .right-head-nav-list li:nth-child(3) img").click(function(){
	    $("#right-part .right-head-nav .alert-window").slideToggle();
	});

});