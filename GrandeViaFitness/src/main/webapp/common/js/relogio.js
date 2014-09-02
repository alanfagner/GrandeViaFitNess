var montharray = new Array(" de Janeiro de "," de Fevereiro de "," de Março de "," de Abril de "," de Maio de "," de Junho de "," de Julho de "," de Agosto de "," de Setembro de "," de Outubro de "," de Novembro de "," de Dezembro de ");
function atualizarRelogio() {
	var currentTime = new Date();
	var currentHours = currentTime.getHours();
	var currentMinutes = currentTime.getMinutes();
	var currentSeconds = currentTime.getSeconds();

	// Pad the minutes and seconds with leading zeros, if required
	currentMinutes = (currentMinutes < 10 ? "0" : "") + currentMinutes;
	currentSeconds = (currentSeconds < 10 ? "0" : "") + currentSeconds;

	var currentDateString = currentTime.getDate() + montharray[currentTime.getMonth()] + currentTime.getFullYear() + " - ";
	// Compose the string for display
	var currentTimeString =  + currentHours + ":" + currentMinutes + ":"
			+ currentSeconds;

	$(".SpanHorarioData").html(currentDateString + currentTimeString);
}