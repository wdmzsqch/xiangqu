
function pageto(pindex) {
	$('#pageIndex').val(pindex);
	$('#pageForm').submit();
}

function pageprev() {
	var page = Number($('#pageIndex').val()) - 1;
	if (page > 0) {
		$('#pageIndex').val(page);
		$('#pageForm').submit();
	}
}

function pagenext() {
	var page = Number($('#pageIndex').val()) + 1;
	if (page <= Number($('#pageCount').val())) {
		$('#pageIndex').val(page);
		$('#pageForm').submit();
	}
}