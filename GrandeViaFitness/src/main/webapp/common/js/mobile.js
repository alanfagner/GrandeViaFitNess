function addScriptCss() {
	jQuery(function($) {	
		$('#listview').listview().listview('refresh');
		$(".data").mask("99/99/9999");
		$(".cep").mask("99999-999");
		$(".numero").mask("9?99999");
		$(".cpf").mask("999.999.999-99");
		$(".telefone").mask("(99) 99999-9999");
		$('.valor').mask("9?99,9", {
			reverse : true
		});
	})(jQuery);
}
