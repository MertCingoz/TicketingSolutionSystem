var totalTicketType=0;
function getEventListByShowId(showId){
	$('#eventListCombo').val("-");
	$('#eventListCombo').selectmenu('refresh');
	var datas = "";
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getAllEventsByShowId/?showId=" + showId,
		dataType : "json", // data type of response
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {
			if (data.errorCode == "0") {
				for (var i = 0; i < data.response.eventList.length; i++) {
					var date = new Date(data.response.eventList[i].eventDate);
					var dateMinutes = date.getMinutes();
					if (dateMinutes == 0) {
						dateMinutes = dateMinutes + "" + "0"
					}
					var dateHours = date.getHours();
					if (dateHours == 0) {
						dateHours = dateHours + "" + "0"
					}
					
					datas += '<option value="-">Etkinlik Seçiniz...</option>';
					for (var i = 0; i < data.response.eventList.length; i++) {
						datas += "<option value=" + data.response.eventList[i].id
								+ ">" + date.getDate()
								+ "-" + (date.getMonth() + 1) + "-"
								+ date.getFullYear() + " " + dateHours
								+ ":" + dateMinutes
								+ "</option>";
					}
					$('#eventListCombo').html(datas);
					
				}
			} else {
				alert(data.errorDesc);
			}
		}
	});
}

function reloadShowListsForSale() {
	var datas = "";
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getAllShows/",
		dataType : "json", // data type of response
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {

			if (data.errorCode == "0") {
				datas += '<option value="-">Gösteri Seçiniz...</option>';
				for (var i = 0; i < data.response.showList.length; i++) {
					datas += "<option value=" + data.response.showList[i].id
							+ ">" + data.response.showList[i].name
							+ "</option>";
				}
				$('#showListCombo').html(datas);

			} else {
				alert(data.errorDesc);
			}
		}
	});
}
function getPriceListDetailByShowId(showId){
	var datas = "";

	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getPriceListDetailByShowId/?showId="+showId,
		dataType : "json", // data type of response
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {

			if (data.errorCode == "0") {
				
				for (var i = 0; i < data.response.priceListDetailList.length; i++) {
					datas += '<div class="col-xs-6">';
					datas += '<br> <label>'+data.response.priceListDetailList[i].name+' : ('+data.response.priceListDetailList[i].price+' TL)</label>';
					datas +='<input type="text" onchange="calculateTotal('+i+')" id="ticketCount'+i+'" class="form-control" value=0>';
					datas +='<input type="text" id="priceTypeId'+i+'" value="'+data.response.priceListDetailList[i].priceTypeId+'">';
					datas +='<input type="text" id="price'+i+'" value="'+data.response.priceListDetailList[i].price+'">';
					datas +='</div>';
				}
				$('#priceListDiv').html(datas);
				totalTicketType=data.response.priceListDetailList.length;
			} else {
				alert(data.errorDesc);
			}
		}
	});
}

function calculateTotal(i){
	var datas = "";
	var totalTicketC=0;
	var totalAmount=0;
	for(var x=0;x<totalTicketType;x++){
		totalTicketC=parseInt(totalTicketC)+parseInt($('#ticketCount'+x).val());
		totalAmount=parseFloat(totalAmount)+(parseInt($('#ticketCount'+x).val())*parseFloat($('#price'+x).val()));
	}
	$('#totalTicketCount').val(totalTicketC);
	$('#totalAmount').val(totalAmount);

}