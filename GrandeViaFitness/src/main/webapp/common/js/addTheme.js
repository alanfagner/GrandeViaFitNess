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
			
			$(".goto a, .botaoA a, .wicket_orderNone a , .wicket_orderDown a, .wicket_orderUp a").button({
				text : false,
				icons : {
					primary : ""
				}
			});
			$( ".botaoA em, .navigator em" ).button({ disabled: true });	
			

			$(".first a").button({
				text : false,
				icons : {
					primary : "ui-icon-seek-prev"
				}
			});
			$(".prev a ").button({
				text : false,
				icons : {
					primary : "ui-icon-seek-start"
				}
			});

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
			$(".next a").button({
				text : false,
				icons : {
					primary : "ui-icon-seek-next"
				}
			});
			$(".last a").button({
				text : false,
				icons : {
					primary : "ui-icon-seek-end"
				}
			});
		});
	})(jQuery);
}