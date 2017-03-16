 function checkNum(e,id) {
	 var textval = $("#"+id).val();
	    var k = window.event ? e.keyCode : e.which;
	    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || ((k >=96 ) && (k <= 105)) || k == 46 || k == 37 || k == 39) {
	    } else {
	        if (window.event) {
	            window.event.returnValue = false;
	        }
	        else {
	            e.preventDefault(); // for firefox
	        }
	        $("#"+id).val(textval.replace(/\D/g,''));
	    }
 	} 