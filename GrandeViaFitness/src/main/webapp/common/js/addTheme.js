function addScriptCss() {
	
	jQuery.noConflict(); 
	(function($) {
		$(document).ready(function() {

			$(".cep").mask("99999-999"); 
			$(".numero").mask("9?99999"); 
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
		});
	})(jQuery);
}