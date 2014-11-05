function addScriptCss() {
	
	 
	(function($) {
		$(document).ready(function() {
			$(".data").mask("99/99/9999",{placeholder:" "}); 
		
			$(".cep").mask("99999-999"); 
			$(".numero").mask("9?9999999",{placeholder:" "}); 
			$(".ano").mask("9999",{placeholder:" "}); 
			$(".valor").maskMoney();
			$(".cpf").mask("999.999.999-99",{placeholder:" "}); 
 
			$(".telefone").mask("(99) 99999-9999",{placeholder:" "}); 

			jQuery(document).ready(function($) {
				atualizarRelogio();
				setInterval('atualizarRelogio()', 1000);
			});
			
			$(".campoText").autocomplete({});
			
			$(".aparelho").button({
				icons : {
					primary : "ui-icon ui-icon-close"
				},
				text : false
			});
			$( ".radioEquipamento" ).buttonset();
			$( ".radioCorpo" ).buttonset();
			
		
		});
	})(jQuery);
}

