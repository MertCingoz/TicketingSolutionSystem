//var ticketElements = [];
//var elementCount = 0;
//var p;
//var position;
//var containerX = 0;
//var containerY = 0;
//var containerBorderSize = 1;
//var pictureElements = [];
//var pictureCount = 0;
//var bgPicture = [];
//var isSaved = false;
//
//function getTicketDesign(ticketDesignId) {
//
//	$
//			.ajax({
//				type : 'GET',
//				url : "/TicketingSolutionRest/getTicketDesignById/?id="
//						+ ticketDesignId,
//				dataType : "json", // data type of response
//				async : false,
//				beforeSend : function() {
//					$("#LoadingImage").show();
//				},
//				complete : function() {
//					$("#LoadingImage").hide();
//				},
//				success : function(data) {
//					if (data.errorCode == "0") {
//						$("#containment-wrapper").css("background-image",
//								"url(" + data.response.backgroundImage + ")");
//						$("#containment-wrapper").css("background-repeat",
//								"no-repeat");
//					} else {
//						alert(data.errorDesc);
//					}
//				}
//			});
//}
//
//function getDivPositionAndSet(elementId) {
//	var p = $("#" + elementId);
//	var position = p.position();
//	for (var x = 0; x < ticketElements.length; x++) {
//		if (ticketElements[x].id == elementId) {
//			ticketElements[x].xAxis = position.left - containerX
//					- containerBorderSize;
//			ticketElements[x].yAxis = position.top - containerY
//					- containerBorderSize;
//		}
//	}
//}
//function getDivHtml(elementCount, id, value) {
//	var divStr = "<div id='draggable"
//			+ elementCount
//			+ "' class='draggable ui-widget-content'><label class='labelClass' id='"
//			+ id + "'>" + value + "</label></div>";
//	return divStr;
//}
//function addElement() {
//	elementCount++;
//	var fieldType = $("#fieldNameCombo option:selected").val();
//	/*
//	 * for (var s = 0; s < ticketElements.length; s++) { if
//	 * (ticketElements[s].fieldType == fieldType && fieldType != "barcode") {
//	 * alert("Bu alanı daha önce eklemişsiniz!"); return; } }
//	 */
//	if (fieldType == "showName") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "showName", "Gösteri Adı")));
//	} else if (fieldType == "eventDate") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "showDate", "Etkinlik Tarihi")));
//	} else if (fieldType == "eventTime") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "eventTime", "Etkinlik Saati")));
//	} else if (fieldType == "barcode") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "barcode", "Barkod")));
//	} else if (fieldType == "block") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "block", "Blok")));
//	} else if (fieldType == "row") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "row", "Sıra")));
//	} else if (fieldType == "seat") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "seat", "Koltuk")));
//	} else if (fieldType == "venueName") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "venueName", "Mekan Adı")));
//	} else if (fieldType == "hallName") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "hallName", "Salon Adı")));
//	} else if (fieldType == "priceCategory") {
//		$('#containment-wrapper')
//				.append(
//						$(getDivHtml(elementCount, "priceCategory",
//								"Fiyat Kategorisi")));
//	} else if (fieldType == "customerName") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "customerName", "Müşteri Adı")));
//	} else if (fieldType == "customerSurname") {
//		$('#containment-wrapper')
//				.append(
//						$(getDivHtml(elementCount, "customerSurname",
//								"Müşteri Soyadı")));
//	} else if (fieldType == "customerNationalId") {
//		$('#containment-wrapper')
//				.append(
//						$(getDivHtml(elementCount, "customerNationalId",
//								"Müşteri TC")));
//	} else if (fieldType == "ticketPrintingTime") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "ticketPrintingTime",
//						"Bilet Basım Zamanı")));
//	} else if (fieldType == "serviceFee") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "serviceFee", "Hizmet Bedeli")));
//	} else if (fieldType == "totalPrice") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "totalPrice", "Toplam Tutar")));
//	} else if (fieldType == "transactionId") {
//		$('#containment-wrapper').append(
//				$(getDivHtml(elementCount, "transactionId", "İşlem Numarası")));
//	} else if (fieldType == "freeText1") {
//		$('#containment-wrapper')
//				.append(
//						$("<div id='draggable"
//								+ elementCount
//								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText1'></div>"));
//	} else if (fieldType == "freeText2") {
//		$('#containment-wrapper')
//				.append(
//						$("<div id='draggable"
//								+ elementCount
//								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText2'></div>"));
//	} else if (fieldType == "freeText3") {
//		$('#containment-wrapper')
//				.append(
//						$("<div id='draggable"
//								+ elementCount
//								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText3'></div>"));
//	}
//
//	$('#draggable' + elementCount).draggable({
//		containment : "#containment-wrapper",
//		scroll : false,
//		start : function() {
//			setTextToDetail(this);
//		},
//		drag : function(e) {
//			setTextToDetail(this);
//		},
//		stop : function() {
//			setTextToDetail(this);
//			getDivPositionAndSet('draggable' + elementCount);
//		}
//	});
//
//	var ticketElement = {
//		id : 'draggable' + elementCount,
//		order : elementCount,
//		fieldType : fieldType,
//		xAxis : 0,
//		yAxis : 0,
//		font : 'Verdana',
//		size : 10
//	};
//
//	isSaved = false;
//}
//function readURL(input) {
//	if (input.files && input.files[0]) {
//		pictureCount++;
//		var reader = new FileReader();
//		reader.onload = function(e) {
//			$('#containment-wrapper')
//					.append(
//							$("<div id='picture"
//									+ pictureCount
//									+ "' class='draggable ui-widget-content'><img id='blah' src='"
//									+ e.target.result
//									+ "' alt='your image' style='height: 100%; width: 100%; object-fit: contain' /></div>"));
//
//			var pictureElement = {
//				id : 'picture' + pictureCount,
//				order : pictureCount,
//				xAxis : 0,
//				yAxis : 0,
//				width : 0,
//				height : 0,
//				picture : input.files[0],
//				status : 'N',
//				dbFileName : ''
//			};
//
//		}
//		reader.readAsDataURL(input.files[0]);
//	}
//}
//
//function readURLforBgPicture(input) {
//	if (input.files && input.files[0]) {
//		var reader = new FileReader();
//		reader.onload = function(e) {
//			$("#containment-wrapper").css("background-image",
//					"url(" + e.target.result + ")");
//			$("#containment-wrapper").css("background-repeat", "no-repeat");
//		}
//		reader.readAsDataURL(input.files[0]);
//		bgPictureElement = {
//			file : input.files[0],
//			status : 'N',
//			dbFileName : ''
//		};
//		bgPicture=[];
//		bgPicture.push(bgPictureElement);
//	}
//}
