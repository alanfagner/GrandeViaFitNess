$(function() {
	$("input[type=submit], .classPesquisa, button, #pageLink ").button();
	$(".first").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-prev"
		}
	});
	$(".prev").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-start"
		}
	});
	$("input[type=text]").autocomplete({});
	$("table").tabs();
	$(".classPesquisa").button({
		icons : {
			primary : "ui-icon ui-icon-minus"
		},
		text : false
	});
	$(".next").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-next"
		}
	});
	$(".last").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-end"
		}
	});
});

jQuery.addScriptCss = function() {
	$("input[type=submit], .classPesquisa, button, #pageLink ").button();
	$(".first").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-prev"
		}
	});
	$(".prev").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-start"
		}
	});
	$("input[type=text]").autocomplete({});
	$("table").tabs();
	$(".classPesquisa").button({
		icons : {
			primary : "ui-icon ui-icon-minus"
		},
		text : false
	});
	$(".next").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-next"
		}
	});
	$(".last").button({
		text : false,
		icons : {
			primary : "ui-icon-seek-end"
		}
	});
};