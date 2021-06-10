/**
 * 
 */
$('#getfilmsall').on('click',function(){
	
	let format = $('#combobox option:selected').text();
	$.ajax({
		
		type:'GET',
		url: `HomeServlet?data-format=${format}`,
		success: function(){
			window.location.href = `HomeServlet?data-format=${format}`;
		},
	
	    error: function(errMsg) {
	         alert(errMsg);
		}
		
		
	});

	
	
});