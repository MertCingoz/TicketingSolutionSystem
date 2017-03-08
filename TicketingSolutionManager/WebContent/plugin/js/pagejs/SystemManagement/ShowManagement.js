function getShowById(id) {
	clearForm();
	$
			.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/getShowById/?id=" + id,
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
						$('#showId').val(data.response.id);
						$('#showType').val(data.response.showTypeId);
						$('#showType').selectmenu('refresh');
						$('#showName').val(data.response.name);
						$('#summary').val(data.response.summary);
						$('#description').val(data.response.description);
						$('#showStartDate').val(
								formatDate(data.response.startDate.substring(0,
										10)));
						$('#showStartTime').val(
								data.response.startDate.substring(11, 16));
						$('#showEndDate').val(
								formatDate(data.response.endDate.substring(0,
										10)));
						$('#showEndTime').val(
								data.response.endDate.substring(11, 16));
						$('#duration').val(data.response.duration);
						$('#maximumTicketCount').val(
								data.response.maxTicketSaleCount);
						$('#venueList').val(data.response.venueId);
						$('#venueList').selectmenu('refresh');
						loadHalls();
						$('#hallList').val(data.response.hallId);
						$('#hallList').selectmenu('refresh');
						loadSeatingPlans();
						$('#seatingPlanList').val(data.response.seatingPlanId);
						$('#seatingPlanList').selectmenu('refresh');
						$('#priceList').val(data.response.priceListId);
						$('#priceList').selectmenu('refresh');
						$('#saleStartDate').val(
								formatDate(data.response.saleStartDate
										.substring(0, 10)));
						$('#saleStartTime').val(
								data.response.saleStartDate.substring(11, 16));
						$('#saleEndDate').val(
								formatDate(data.response.saleEndDate.substring(
										0, 10)));
						$('#saleEndTime').val(
								data.response.saleEndDate.substring(11, 16));
						$('#paymentMethods').multiselect('deselectAll', false,
								true);
						$('#ticketCount').val(data.response.ticketCount);
						for (var x = 0; x < data.response.showPaymentMethods.length; x++) {
							$('#paymentMethods')
									.multiselect(
											'select',
											data.response.showPaymentMethods[x].paymentMethodId,
											true);
						}

						$('#paymentMethods').multiselect('rebuild');
						$('#updateShowButton').show();
						$('#saveShowButton').hide();
						$('#cancelUpdateButton').show();
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

function getOneRow(id, status, name) {
	var rowString = "";
	rowString += "<tr onclick=getShowById('" + id + "')>";
	rowString += "<td>" + name + "</td>";
	rowString += "</tr>";
	return rowString;
}

function loadPriceLists() {
	var datas = "";
	$
			.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/getAllPriceLists/",
				dataType : "json", // data type of response
				beforeSend : function() {
					$("#LoadingImage").show();
				},
				complete : function() {
					$("#LoadingImage").hide();
				},
				success : function(data) {
					if (data.errorCode == "0") {
						datas += '<option value="-">Fiyat Listesi Seçiniz...</option>';
						for (var i = 0; i < data.response.priceListList.length; i++) {
							datas += "<option value="
									+ data.response.priceListList[i].id + ">"
									+ data.response.priceListList[i].name
									+ "</option>";
						}

						$('#priceList').html(datas);
					} else {
						alert(data.errorDesc);
					}
				}
			});
}

function loadPaymentMethods() {
	var datas = "";
	$
			.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/getAllPaymentMethods/",
				dataType : "json", // data type of response
				beforeSend : function() {
					$("#LoadingImage").show();
				},
				complete : function() {
					$("#LoadingImage").hide();
				},
				success : function(data) {
					if (data.errorCode == "0") {
						datas += "";
						for (var i = 0; i < data.response.paymentMethodList.length; i++) {
							datas += "<option value="
									+ data.response.paymentMethodList[i].id
									+ ">"
									+ data.response.paymentMethodList[i].name
									+ "</option>";
						}
						$('#paymentMethods').append(datas);
						$('#paymentMethods').multiselect('rebuild');

					} else {
						alert(data.errorDesc);
					}
				}
			});
}

function loadShowTypes() {
	var datas = "";
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getAllShowTypes/",
		dataType : "json", // data type of response
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {
			if (data.errorCode == "0") {
				datas += '<option value="-">Gösteri türü seçiniz...</option>';
				for (var i = 0; i < data.response.showTypeList.length; i++) {
					datas += "<option value="
							+ data.response.showTypeList[i].id + ">"
							+ data.response.showTypeList[i].name + "</option>";
				}

				$('#showType').html(datas);
			} else {
				alert(data.errorDesc);
			}
		}
	});
}
function loadVenues() {
	var datas = "";
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/getAllVenues/",
		dataType : "json", // data type of response
		beforeSend : function() {
			$("#LoadingImage").show();
		},
		complete : function() {
			$("#LoadingImage").hide();
		},
		success : function(data) {
			if (data.errorCode == "0") {
				datas += '<option value="-">Mekan seçiniz...</option>';
				for (var i = 0; i < data.response.venueList.length; i++) {
					datas += "<option value=" + data.response.venueList[i].id
							+ ">" + data.response.venueList[i].name
							+ "</option>";
				}
				$('#venueList').html(datas);
			} else {
				alert(data.errorDesc);
			}
		}
	});
}
function loadHalls() {
	var venueId = $('#venueList option:selected').val();
	$('#seatingPlanList').html(
			'<option value="-">Oturma Planı Seçiniz...</option>');
	$('#seatingPlanList').selectmenu('refresh');
	if (venueId == "-") {
		$('#hallList').html('<option value="-">Salon seçiniz...</option>');
		$('#hallList').selectmenu('refresh');
	} else {
		var datas = "";
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getAllHallsByVenueId/?venueId="
					+ venueId,
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
					datas += '<option value="-">Salon seçiniz...</option>';
					for (var i = 0; i < data.response.hallList.length; i++) {
						datas += "<option value="
								+ data.response.hallList[i].id + ">"
								+ data.response.hallList[i].name + "</option>";
					}
					$('#hallList').html(datas);
				} else {
					alert(data.errorDesc);
				}
			}
		});
		$('#hallList').selectmenu('refresh');
	}
}

function loadSeatingPlans() {
	var hallId = $('#hallList option:selected').val();
	if (hallId == "-") {
		$('#seatingPlanList').html(
				'<option value="-">Oturma Planı Seçiniz...</option>');
		$('#seatingPlanList').selectmenu('refresh');
	} else {
		var datas = "";
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllSeatingPlansByHallId/?hallId="
							+ hallId,
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
							datas += '<option value="-">Oturma Planı Seçiniz...</option>';
							for (var i = 0; i < data.response.seatingPlanList.length; i++) {
								datas += "<option value="
										+ data.response.seatingPlanList[i].id
										+ ">"
										+ data.response.seatingPlanList[i].name
										+ "</option>";
							}
							$('#seatingPlanList').html(datas);
						} else {
							alert(data.errorDesc);
						}
					}
				});
		$('#seatingPlanList').selectmenu('refresh');
	}
}

function reloadShowLists() {
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
				for (var i = 0; i < data.response.showList.length; i++) {
					datas += getOneRow(data.response.showList[i].id,
							data.response.showList[i].status,
							data.response.showList[i].name);
				}
				$('#showList').html(datas);
				$("#showListData").DataTable();
				$("#showListData").DataTable().init();
			} else {
				alert(data.errorDesc);
			}
		}
	});
}

function clearForm() {

	$('#showName').val("");
	$('#summary').val("");
	$('#description').val("");
	$('#showStartDate').val("");
	$('#showStartTime').val("");
	$('#showEndDate').val("");
	$('#showEndTime').val("");
	$('#duration').val("");
	$('#maximumTicketCount').val("");

	$('#saleStartDate').val("");
	$('#saleStartTime').val("");
	$('#saleEndDate').val("");
	$('#saleEndTime').val("");
	
	$('#showType').val("-");
	$('#showType').selectmenu('refresh');
	$('#venueList').val("-");
	$('#venueList').selectmenu('refresh');
	$('#hallList').val("-");
	$('#hallList').selectmenu('refresh');
	$('#seatingPlanList').val("-");
	$('#seatingPlanList').selectmenu('refresh');
	$('#priceList').val("-");
	$('#priceList').selectmenu('refresh');
	$('#ticketCount').val("");
	$('#paymentMethods').multiselect('deselectAll', false, true);
	$('#paymentMethods').multiselect('rebuild');
}