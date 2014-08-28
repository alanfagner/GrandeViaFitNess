$(function() {
	$(".botao").button();
	
	$( ".campoText" ).autocomplete({

	});
	
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
$(".botao").button();
	
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