$(document).ready(function() {


	$("#addToCartForm").submit(function(event) {

		event.preventDefault();

		addToCard_ajax_submit();

	});



});
function addToCard_ajax_submit() {

	var add = {};
	add["id"] = $("#idItem").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/addToCartAJAX",
		data : JSON.stringify(add),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = JSON.stringify(data, null, 4);
			var result = JSON.parse(json);
			$('#sizeCart').html(result.sizeCart);
			alert("Add to cart successfully!");
			$('#listMiniCart').append('<li><a class="aa-cartbox-img" href="#"><img src="'+result.link_image+'" alt="img"></a><div class="aa-cartbox-info"><h4><a href="#">'+result.name+'</a></h4><p> '+result.quantity+' x '+result.price+' VNĐ</p></div> <a class="aa-remove-product" href="#"><spanclass="fa fa-times"></span></a></li>');

		},
		error : function(e) {

			alert("Add to cart failed!");

		}
	});

}