function reloadEventLists(showId) {
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
					datas += getOneRow(data.response.eventList[i].id,
							data.response.eventList[i].status, date.getDate()
									+ "-" + (date.getMonth() + 1) + "-"
									+ date.getFullYear() + " " + dateHours
									+ ":" + dateMinutes);
				}
				$('#eventList').html(datas);
				$("#eventListData").DataTable();
				$("#eventListData").DataTable().init();
			} else {
				alert(data.errorDesc);
			}
		}
	});
}
function clearEventForm(flag) {
	$('#eventStartDate').val("");
	$('#eventStartTime').val("");
	$('#duration').val("");
	if (flag) {
		$('#showListCombo').val("-");
		$('#showListCombo').selectmenu('refresh');
	}
	$('#eventList').html("");
	$("#eventListData").DataTable();
	$("#eventListData").DataTable().init();
	$('#selectSeat').val("-");
	$('#selectSeat').selectmenu('refresh');
	$('#saleStatus').val("-");
	$('#saleStatus').selectmenu('refresh');
	$('#ticketDesign').val("-");
	$('#ticketDesign').selectmenu('refresh');
	$('#updateEventButton').hide();
	$('#cancelUpdateButton').hide();

}

function reloadShowListsForEvent() {
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

function reloadTicketDesigns() {
	var datas = "";
	$
			.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/getAllTicketDesigns/",
				dataType : "json", // data type of response
				beforeSend : function() {
					$("#LoadingImage").show();
				},
				complete : function() {
					$("#LoadingImage").hide();
				},
				success : function(data) {

					if (data.errorCode == "0") {
						datas += '<option value="-">Bilet Tasarımı Seçiniz...</option>';
						for (var i = 0; i < data.response.ticketDesignList.length; i++) {
							datas += "<option value="
									+ data.response.ticketDesignList[i].id
									+ ">"
									+ data.response.ticketDesignList[i].name
									+ "</option>";
						}
						$('#ticketDesign').html(datas);

					} else {
						alert(data.errorDesc);
					}
				}
			});
}

function getOneRow(id, status, name) {
	var rowString = "";
	rowString += "<tr onclick=getEventById('" + id + "')>";
	rowString += "<td>" + name + "</td>";
	rowString += "</tr>";
	return rowString;
}
function onSelectShow(id) {
	clearEventForm(false);
	$('#saveEventButton').show();
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getShowByIdToCreateEvent/?id=" + id,
		dataType : "json", // data type of response
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {
			if (data.errorCode == "0") {
				$('#showType').val(data.response.showTypeName);
				$('#eventStartDate').val(
						formatDate(data.response.startDate.substring(0, 10)));
				$('#eventStartTime').val(
						data.response.startDate.substring(11, 16));
				$('#duration').val(data.response.duration);
				$('#venue').val(data.response.venueName);
				$('#hall').val(data.response.hallName);
				reloadEventLists(id);
			} else {
				alert(data.errorDesc);
			}
		}
	});
}
function formatDate(dateIn) {
	// yyyy-mm-dd olarak gelecek
	var dateOut = dateIn.substring(8, 10) + "-" + dateIn.substring(5, 7) + "-"
			+ dateIn.substring(0, 4);
	return dateOut;
	// dd-mm-yyyy olarak çıkacak
}
function getEventById(id) {
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getEventById/?id=" + id,
		dataType : "json", // data type of response
		async : false,
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {
			if (data.errorCode == "0") {
				$('#eventId').val(data.response.id);
				$('#eventStartDate').val(
						formatDate(data.response.eventDate.substring(0, 10)));
				$('#eventStartTime').val(
						data.response.eventDate.substring(11, 16));
				$('#duration').val(data.response.duration);
				$('#selectSeat').val(data.response.selectSeat);
				$('#selectSeat').selectmenu('refresh');
				$('#saleStatus').val(data.response.saleStatus);
				$('#saleStatus').selectmenu('refresh');
				$('#ticketDesign').val(data.response.ticketDesignId);
				$('#ticketDesign').selectmenu('refresh');
				$('#updateEventButton').show();
				$('#saveEventButton').hide();
				$('#cancelUpdateButton').show();
			} else {
				alert(data.errorDesc);
			}
		}
	});
}