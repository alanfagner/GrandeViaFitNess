$(function() {
	$("input[type=submit], .classPesquisa, button, a ").button();
	
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
	
	$(".Excluir").button({
		icons : {
			primary : "ui-icon ui-icon-close"
		},
		text : false
	});
	
	$(".Visualizar").button({
		icons : {
			primary : "ui-icon ui-icon-search"
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
$("input[type=submit], .classPesquisa, button, a ").button();
	
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
	
	$(".Excluir").button({
		icons : {
			primary : "ui-icon ui-icon-close"
		},
		text : false
	});
	
	$(".Visualizar").button({
		icons : {
			primary : "ui-icon ui-icon-search"
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