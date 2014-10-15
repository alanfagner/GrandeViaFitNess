function addScriptCss() {
	
	jQuery.noConflict(); 
	(function($) {
		$(document).ready(function() {
			$( ".selector" ).button({ disabled: false });
			$( ".home" ).button( "option", "icon", "star" );
		});
	})(jQuery);
}

