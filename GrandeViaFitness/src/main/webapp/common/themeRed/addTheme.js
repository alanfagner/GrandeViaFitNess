function addScriptCss() {
	
	jQuery.noConflict(); 
	(function($) {
		$(document).ready(function() {

			$(".cep").mask("99999-999"); 
			$(".cpf").mask("999.999.999-99"); 
			$(".data").mask("99/99/9999"); 
			$(".telefone").mask("(99) 99999-9999"); 

			$(".botao").button();

			$(".campoSelect").selectmenu();

			jQuery(document).ready(function($) {
				atualizarRelogio();
				setInterval('atualizarRelogio()', 1000);
			});

			$(".campoText").autocomplete({

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
	})(jQuery);
}