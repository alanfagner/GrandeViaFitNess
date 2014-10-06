function addScriptCss() {
	
	jQuery.noConflict(); 
	(function($) {
		$(document).ready(function() {
			$(".data").mask("99/99/9999"); 
			$( ".data" ).datepicker({ altFormat: "dd-mm-yyyy" });
			$(".cep").mask("99999-999"); 
			$(".numero").mask("9?99999"); 
			$(".cpf").mask("999.999.999-99"); 
 
			$(".telefone").mask("(99) 99999-9999"); 

			$(".botao").button();
			
			 $( ".campoSelect" ).selectmenu({
			      change: function( event, data ) {

			      }
			     });


			jQuery(document).ready(function($) {
				atualizarRelogio();
				setInterval('atualizarRelogio()', 1000);
			});

			$(".campoText").autocomplete({

			});
			
			$(".aparelho").button({
				icons : {
					primary : "ui-icon ui-icon-close"
				},
				text : false
			});
			$( ".radioEquipamento" ).buttonset();
			$( ".radioCorpo" ).buttonset();
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

