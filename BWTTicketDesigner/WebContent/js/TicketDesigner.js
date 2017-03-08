var ticketElements = [];
var elementCount = 0;
var p;
var position;
var containerX = 0;
var containerY = 0;
var containerBorderSize = 1;
var pictureElements = [];
var pictureCount = 0;
var bgPicture = [];
var isSaved = false;

function addDragableElementFromDb(pdbTableId, pelementType, pfieldType, porder,
		pxAxis, pyAxis, pfont, psize, pwidth, pheight) {
	elementCount++;

	if (pfieldType == "showName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "showName", "Gösteri Adı")));
	} else if (pfieldType == "eventDate") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "showDate", "Etkinlik Tarihi")));
	} else if (pfieldType == "eventTime") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "eventTime", "Etkinlik Saati")));
	} else if (pfieldType == "barcode") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "barcode", "Barkod")));
	} else if (pfieldType == "block") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "block", "Blok")));
	} else if (pfieldType == "row") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "row", "Sıra")));
	} else if (pfieldType == "seat") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "seat", "Koltuk")));
	} else if (pfieldType == "venueName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "venueName", "Mekan Adı")));
	} else if (pfieldType == "hallName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "hallName", "Salon Adı")));
	} else if (pfieldType == "priceCategory") {
		$('#containment-wrapper')
				.append(
						$(getDivHtml(elementCount, "priceCategory",
								"Fiyat Kategorisi")));
	} else if (pfieldType == "customerName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "customerName", "Müşteri Adı")));
	} else if (pfieldType == "customerSurname") {
		$('#containment-wrapper')
				.append(
						$(getDivHtml(elementCount, "customerSurname",
								"Müşteri Soyadı")));
	} else if (pfieldType == "customerNationalId") {
		$('#containment-wrapper')
				.append(
						$(getDivHtml(elementCount, "customerNationalId",
								"Müşteri TC")));
	} else if (pfieldType == "ticketPrintingTime") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "ticketPrintingTime",
						"Bilet Basım Zamanı")));
	} else if (pfieldType == "serviceFee") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "serviceFee", "Hizmet Bedeli")));
	} else if (pfieldType == "totalPrice") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "totalPrice", "Toplam Tutar")));
	} else if (pfieldType == "transactionId") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "transactionId", "İşlem Numarası")));
	} else if (pfieldType == "freeText1") {
		$('#containment-wrapper')
				.append(
						$("<div id='draggable"
								+ elementCount
								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText1'></div>"));
	} else if (pfieldType == "freeText2") {
		$('#containment-wrapper')
				.append(
						$("<div id='draggable"
								+ elementCount
								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText2'></div>"));
	} else if (pfieldType == "freeText3") {
		$('#containment-wrapper')
				.append(
						$("<div id='draggable"
								+ elementCount
								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText3'></div>"));
	}

	$('#draggable' + elementCount).draggable({
		containment : "#containment-wrapper",
		scroll : false,
		start : function() {
			setTextToDetail(this);
		},
		drag : function(e) {
			setTextToDetail(this);
		},
		stop : function() {
			setTextToDetail(this);
			getDivPositionAndSet(this);
		}
	});
	$('#draggable' + elementCount).resizable({
		containment : "#containment-wrapper",
		start : function() {
		},
		resize : function() {
		},
		stop : function() {
			getDivPositionAndSet(this);
		}
	}).selectable();
	var ticketElement = {
		dbTableId : pdbTableId,
		id : 'draggable' + elementCount,
		order : elementCount,
		fieldType : pfieldType,
		xAxis : pxAxis,
		yAxis : pyAxis,
		font : pfont,
		size : psize,
		width : pwidth,
		height : pheight
	};
	ticketElements.push(ticketElement);
	$('#draggable' + elementCount).on('dblclick', function() {
		this.remove();
		var temp = [];
		for (var x = 0; x < ticketElements.length; x++) {
			if (ticketElements[x].id != this.id) {
				temp.push(ticketElements[x]);
			}
		}
		ticketElements = temp;
		temp = [];
	});
	$('#draggable' + elementCount).on('click', function() {

		$("#elementName").val(this.id);
		var p = $(this);
		var position = p.position();
		$("#xAxis").val(position.left - containerX - containerBorderSize);
		$("#yAxis").val(position.top - containerY - containerBorderSize);
		$("#width").val(p.width());
		$("#height").val(p.height());

		for (var x = 0; x < ticketElements.length; x++) {
			var elementName = 'draggable' + elementCount;
			console.log(elementName);
			console.log(ticketElements[x].id);
			if (ticketElements[x].id == elementName) {
				console.log(ticketElements[x].size);
				$("#punto").val(ticketElements[x].size);
			}
		}
	});
	var p = $('#draggable' + elementCount);
	p.css('left', pxAxis + containerX);
	p.css('top', pyAxis + containerY);
	p.css('width', pwidth);
	p.css('height', pheight);
}

function addPictureElementFromDb(pdbTableId, pdbFileName, pxAxis, pyAxis,
		pwidth, pheight, ppicture) {
	pictureCount++;
	$('#containment-wrapper')
			.append(
					$("<div id='picture"
							+ pictureCount
							+ "' class='draggable ui-widget-content'><img id='blah' src='"
							+ pdbFileName
							+ "' alt='your image' style='height: 100%; width: 100%; object-fit: contain' /></div>"));

	var pictureElement = {
		dbTableId : pdbTableId,
		id : 'picture' + pictureCount,
		order : pictureCount,
		xAxis : pxAxis,
		yAxis : pyAxis,
		width : pwidth,
		height : pheight,
		picture : ppicture,
		status : 'C',
		dbFileName : pdbFileName
	};
	pictureElements.push(pictureElement);

	$('#picture' + pictureCount).draggable({
		containment : "#containment-wrapper",
		scroll : false,
		start : function() {
			setTextToDetail(this);
		},
		drag : function(e) {
			setTextToDetail(this);
		},
		stop : function() {
			setTextToDetail(this);
			getPictureDivPositionAndSet('picture' + pictureCount);
		}
	});
	$('#picture' + pictureCount).resizable({
		containment : "#containment-wrapper"
	}).selectable();

	$('#picture' + pictureCount).on('dblclick', function() {
		this.remove();
		var temp = [];
		for (var x = 0; x < pictureElements.length; x++) {
			if (pictureElements[x].id != this.id) {
				temp.push(pictureElements[x]);
			}
		}
		pictureElements = temp;
		temp = [];
	});
	$('#picture' + pictureCount).on('click', function() {
		setTextToDetail(this);
	});
	var p = $('#picture' + pictureCount);
	p.css('left', pxAxis + containerX);
	p.css('top', pyAxis + containerY);
	p.css('width', pwidth);
	p.css('height', pheight);
}

function addElementFromDb(pdbTableId, pelementType, pfieldType, porder, pxAxis,
		pyAxis, pfont, psize, pdbFileName, pwidth, pheight, ppicture) {
	if (pelementType == "2") {
		addDragableElementFromDb(pdbTableId, pelementType, pfieldType, porder,
				pxAxis, pyAxis, pfont, psize, pwidth, pheight);
	} else if (pelementType == "1") {
		addPictureElementFromDb(pdbTableId, pdbFileName, pxAxis, pyAxis,
				pwidth, pheight, ppicture);
	}

}

function loadBackgroundFromUrl(imageUrl) {
	$("#containment-wrapper").css("background-image", "url(" + imageUrl + ")");
	$("#containment-wrapper").css("background-repeat", "no-repeat");
}

function getDivPositionAndSet(ths) {
	// var p = $("#" + elementId);
	var p = $(ths);
	var position = p.position();
	for (var x = 0; x < ticketElements.length; x++) {
		if (ticketElements[x].id == ths.id) {
			ticketElements[x].xAxis = position.left - containerX
					- containerBorderSize;
			ticketElements[x].yAxis = position.top - containerY
					- containerBorderSize;
			ticketElements[x].width = p.width();
			ticketElements[x].height = p.height();

		}
	}
}
function getDivHtml(elementCount, id, value) {
	var divStr = "<div id='draggable"
			+ elementCount
			+ "' class='draggable ui-widget-content'><label class='labelClass' id='"
			+ id + "'>" + value + "</label></div>";
	return divStr;
}
function addElement() {
	elementCount++;
	var fieldType = $("#fieldNameCombo option:selected").val();
	/*
	 * for (var s = 0; s < ticketElements.length; s++) { if
	 * (ticketElements[s].fieldType == fieldType && fieldType != "barcode") {
	 * alert("Bu alanı daha önce eklemişsiniz!"); return; } }
	 */
	if (fieldType == "showName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "showName", "Gösteri Adı")));
	} else if (fieldType == "eventDate") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "showDate", "Etkinlik Tarihi")));
	} else if (fieldType == "eventTime") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "eventTime", "Etkinlik Saati")));
	} else if (fieldType == "barcode") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "barcode", "Barkod")));
	} else if (fieldType == "block") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "block", "Blok")));
	} else if (fieldType == "row") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "row", "Sıra")));
	} else if (fieldType == "seat") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "seat", "Koltuk")));
	} else if (fieldType == "venueName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "venueName", "Mekan Adı")));
	} else if (fieldType == "hallName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "hallName", "Salon Adı")));
	} else if (fieldType == "priceCategory") {
		$('#containment-wrapper')
				.append(
						$(getDivHtml(elementCount, "priceCategory",
								"Fiyat Kategorisi")));
	} else if (fieldType == "customerName") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "customerName", "Müşteri Adı")));
	} else if (fieldType == "customerSurname") {
		$('#containment-wrapper')
				.append(
						$(getDivHtml(elementCount, "customerSurname",
								"Müşteri Soyadı")));
	} else if (fieldType == "customerNationalId") {
		$('#containment-wrapper')
				.append(
						$(getDivHtml(elementCount, "customerNationalId",
								"Müşteri TC")));
	} else if (fieldType == "ticketPrintingTime") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "ticketPrintingTime",
						"Bilet Basım Zamanı")));
	} else if (fieldType == "serviceFee") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "serviceFee", "Hizmet Bedeli")));
	} else if (fieldType == "totalPrice") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "totalPrice", "Toplam Tutar")));
	} else if (fieldType == "transactionId") {
		$('#containment-wrapper').append(
				$(getDivHtml(elementCount, "transactionId", "İşlem Numarası")));
	} else if (fieldType == "freeText1") {
		$('#containment-wrapper')
				.append(
						$("<div id='draggable"
								+ elementCount
								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText1'></div>"));
	} else if (fieldType == "freeText2") {
		$('#containment-wrapper')
				.append(
						$("<div id='draggable"
								+ elementCount
								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText2'></div>"));
	} else if (fieldType == "freeText3") {
		$('#containment-wrapper')
				.append(
						$("<div id='draggable"
								+ elementCount
								+ "' class='draggableForText ui-widget-content'><input class='inputTextClass' type='text' id='freeText3'></div>"));
	}

	$('#draggable' + elementCount).draggable({
		containment : "#containment-wrapper",
		scroll : false,
		start : function() {
			setTextToDetail(this);
		},
		drag : function(e) {
			setTextToDetail(this);
		},
		stop : function() {
			setTextToDetail(this);
			// getDivPositionAndSet('draggable' + elementCount);
			getDivPositionAndSet(this);
		}
	});
	$('#draggable' + elementCount).resizable({
		containment : "#containment-wrapper",
		start : function() {
		},
		resize : function() {
		},
		stop : function() {
			getDivPositionAndSet(this);
		}
	}).selectable();
	var ticketElement = {
		dbTableId : "",
		id : 'draggable' + elementCount,
		order : elementCount,
		fieldType : fieldType,
		xAxis : 0,
		yAxis : 0,
		font : 'Verdana',
		size : 10,
		width : 0,
		height : 0
	};
	ticketElements.push(ticketElement);
	$('#draggable' + elementCount).on('dblclick', function() {
		this.remove();
		var temp = [];
		for (var x = 0; x < ticketElements.length; x++) {
			if (ticketElements[x].id != this.id) {
				temp.push(ticketElements[x]);
			}
		}
		ticketElements = temp;
		temp = [];
	});
	$('#draggable' + elementCount).on('click', function() {

		$("#elementName").val(this.id);
		var p = $(this);
		console.log(p);
		var position = p.position();
		$("#xAxis").val(position.left - containerX - containerBorderSize);
		$("#yAxis").val(position.top - containerY - containerBorderSize);
		$("#width").val(p.width());
		$("#height").val(p.height());

		for (var x = 0; x < ticketElements.length; x++) {
			var elementName = 'draggable' + elementCount;
			console.log(elementName);
			console.log(ticketElements[x].id);
			if (ticketElements[x].id == elementName) {
				console.log(ticketElements[x].size);
				$("#punto").val(ticketElements[x].size);
			}
		}
	});
	isSaved = false;
}

function readURL(input) {
	if (input.files && input.files[0]) {
		pictureCount++;
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#containment-wrapper')
					.append(
							$("<div id='picture"
									+ pictureCount
									+ "' class='draggable ui-widget-content'><img id='blah' src='"
									+ e.target.result
									+ "' alt='your image' style='height: 100%; width: 100%; object-fit: contain' /></div>"));
			// $('#blah').attr('src', e.target.result);
			$('#picture' + pictureCount).draggable({
				containment : "#containment-wrapper",
				scroll : false,
				start : function() {
					setTextToDetail(this);
				},
				drag : function(e) {
					setTextToDetail(this);
				},
				stop : function() {
					setTextToDetail(this);
					getPictureDivPositionAndSet('picture' + pictureCount);
				}
			});
			$('#picture' + pictureCount).resizable({
				containment : "#containment-wrapper"
			// ghost : true
			}).selectable();
			var pictureElement = {
				dbTableId : "",
				id : 'picture' + pictureCount,
				order : pictureCount,
				xAxis : 0,
				yAxis : 0,
				width : 0,
				height : 0,
				picture : input.files[0],
				status : 'N',
				dbFileName : ''
			};
			pictureElements.push(pictureElement);
			$('#picture' + pictureCount).on('dblclick', function() {
				this.remove();
				var temp = [];
				for (var x = 0; x < pictureElements.length; x++) {
					if (pictureElements[x].id != this.id) {
						temp.push(pictureElements[x]);
					}
				}
				pictureElements = temp;
				temp = [];
			});
			$('#picture' + pictureCount).on('click', function() {
				setTextToDetail(this);
			});
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function readURLforBgPicture(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#containment-wrapper").css("background-image",
					"url(" + e.target.result + ")");
			$("#containment-wrapper").css("background-repeat", "no-repeat");
		}
		reader.readAsDataURL(input.files[0]);
		bgPictureElement = {
			file : input.files[0],
			status : 'N',
			dbFileName : ''
		};
		bgPicture = [];
		bgPicture.push(bgPictureElement);
	}
}

function getPictureDivPositionAndSet(elementId) {
	var p = $("#" + elementId);
	var position = p.position();
	for (var x = 0; x < pictureElements.length; x++) {
		if (pictureElements[x].id == elementId) {
			pictureElements[x].xAxis = position.left - containerX
					- containerBorderSize;
			pictureElements[x].yAxis = position.top - containerY
					- containerBorderSize;
			pictureElements[x].width = p.width();
			pictureElements[x].height = p.height();
		}
	}
}

function setTextToDetail(ths) {
	$("#elementName").val(ths.id);
	var p = $(ths);
	var position = p.position();
	$("#xAxis").val(position.left - containerX - containerBorderSize);
	$("#yAxis").val(position.top - containerY - containerBorderSize);
	$("#width").val(p.width());
	$("#height").val(p.height());
}
function applyChangesToObject() {
	for (var x = 0; x < pictureElements.length; x++) {
		var elementName = $("#elementName").val();
		if (pictureElements[x].id == elementName) {
			pictureElements[x].xAxis = $("#xAxis").val();
			pictureElements[x].yAxis = $("#yAxis").val();
			pictureElements[x].width = $("#width").val();
			pictureElements[x].height = $("#height").val();
			var xAxis = parseInt($("#xAxis").val()) + containerX
					+ containerBorderSize;
			var yAxis = parseInt($("#yAxis").val()) + containerY
					+ containerBorderSize;
			var width = parseInt($("#width").val());
			var height = parseInt($("#height").val());
			var p = $("#" + elementName);
			p.css('left', xAxis);
			p.css('top', yAxis);
			p.css('width', width);
			p.css('height', height);
			return;
		}
	}
	for (var x = 0; x < ticketElements.length; x++) {
		var elementName = $("#elementName").val();
		if (ticketElements[x].id == elementName) {
			ticketElements[x].xAxis = $("#xAxis").val();
			ticketElements[x].yAxis = $("#yAxis").val();
			ticketElements[x].width = $("#width").val();
			ticketElements[x].height = $("#height").val();
			ticketElements[x].font = $("#font option:selected").val();
			ticketElements[x].size = $("#punto").val();
			var xAxis = parseInt($("#xAxis").val()) + containerX
					+ containerBorderSize;
			var yAxis = parseInt($("#yAxis").val()) + containerY
					+ containerBorderSize;
			var width = parseInt($("#width").val());
			var height = parseInt($("#height").val());
			var p = $("#" + elementName);
			p.css('left', xAxis);
			p.css('top', yAxis);
			p.css('width', width);
			p.css('height', height);
			console.log(ticketElements);
			return;
		}
	}
	isSaved = false;
}

function saveChangesToDb() {
	uploadTicketImages();
	uploadTicketBgImage();
	var bgPictureElement;
	var ticketDesignId = $("#ticketDesignId").val();
	if (bgPicture.length > 0) {
		bgPictureElement = bgPicture[0].dbFileName;
	} else {
		bgPictureElement = "";
	}
	if (ticketDesignId == "") {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/updateTicketDesign/?id="
					+ $("#ticketDesignId").val() + "&name=&backgroundImage="
					+ bgPictureElement,
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
					$("#ticketDesignId").val(data.response.ticketDesign.id);
					saveTicketElementsToDb(data.response.ticketDesign.id);
				} else {
					alert(data.errorDesc);
				}
			}
		});
	} else {
		alert("");
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/updateTicketDesign/?id="
					+ $("#ticketDesignId").val() + "&name=&backgroundImage="
					+ bgPictureElement,
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
					saveTicketElementsToDb($("#ticketDesignId").val());
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

}

function saveTicketElementsToDb(ticketDesignId) {
	for (var x = 0; x < pictureElements.length; x++) {
		if (pictureElements[x].dbTableId == "") {
			console.log(pictureElements[x]);
			elementType = "1";
			$
					.ajax({
						type : 'GET',
						url : "/TicketingSolutionRest/createTicketElement/?ticketDesignId="
								+ ticketDesignId
								+ "&elementType="
								+ elementType
								+ "&elementId="
								+ pictureElements[x].id
								+ "&xAxis="
								+ pictureElements[x].xAxis
								+ "&yAxis="
								+ pictureElements[x].yAxis
								+ "&order="
								+ "0"
								+ "&fieldType="
								+ ""
								+ "&width="
								+ parseInt(pictureElements[x].width)
								+ "&height="
								+ parseInt(pictureElements[x].height)
								+ "&picture="
								+ pictureElements[x].picture
								+ "&font="
								+ ""
								+ "&size="
								+ ""
								+ "&dbFileName="
								+ pictureElements[x].dbFileName,
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
								console.log(data);
								isSaved = true;
							} else {
								alert(data.errorDesc);
							}
						}
					});
		} else {

			console.log(pictureElements[x]);
			elementType = "1";
			$.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/updateTicketElement/?id="
						+ pictureElements[x].dbTableId + "&elementType="
						+ elementType + "&elementId=" + pictureElements[x].id
						+ "&xAxis=" + pictureElements[x].xAxis + "&yAxis="
						+ pictureElements[x].yAxis + "&order=" + "0"
						+ "&fieldType=" + "" + "&width="
						+ parseInt(pictureElements[x].width) + "&height="
						+ parseInt(pictureElements[x].height) + "&picture="
						+ pictureElements[x].picture + "&font=" + "" + "&size="
						+ "" + "&dbFileName=" + pictureElements[x].dbFileName,
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
						console.log(data);
						isSaved = true;
					} else {
						alert(data.errorDesc);
					}
				}
			});

		}

	}

	for (var y = 0; y < ticketElements.length; y++) {

		if (ticketElements[y].dbTableId == "") {
			elementType = "2";
			$
					.ajax({
						type : 'GET',
						url : "/TicketingSolutionRest/createTicketElement/?ticketDesignId="
								+ ticketDesignId
								+ "&elementType="
								+ elementType
								+ "&elementId="
								+ ticketElements[y].id
								+ "&xAxis="
								+ ticketElements[y].xAxis
								+ "&yAxis="
								+ ticketElements[y].yAxis
								+ "&order="
								+ ticketElements[y].order
								+ "&fieldType="
								+ ticketElements[y].fieldType
								+ "&width="
								+ parseInt(ticketElements[y].width)
								+ "&height="
								+ parseInt(ticketElements[y].height)
								+ "&picture="
								+ ""
								+ "&font="
								+ ticketElements[y].font
								+ "&size="
								+ ticketElements[y].size + "&dbFileName=",
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
								console.log(data);
							} else {
								alert(data.errorDesc);
							}
						}
					});
		} else {

			elementType = "2";
			$.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/updateTicketElement/?id="
						+ ticketElements[y].dbTableId + "&elementType="
						+ elementType + "&elementId=" + ticketElements[y].id
						+ "&xAxis=" + ticketElements[y].xAxis + "&yAxis="
						+ ticketElements[y].yAxis + "&order="
						+ ticketElements[y].order + "&fieldType="
						+ ticketElements[y].fieldType + "&width="
						+ parseInt(ticketElements[y].width) + "&height="
						+ parseInt(ticketElements[y].height) + "&picture=" + ""
						+ "&font=" + ticketElements[y].font + "&size="
						+ ticketElements[y].size + "&dbFileName=",
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
						console.log(data);
					} else {
						alert(data.errorDesc);
					}
				}
			});

		}

	}
	alert("Kaydedildi");
}

function uploadTicketImages() {
	for (var x = 0; x < pictureElements.length; x++) {
		var file = pictureElements[x].picture;
		console.log(pictureElements[x].status);
		if (pictureElements[x].status == 'N') {
			console.log(file);
			if (file) {
				var formData = new FormData();
				formData.append('file', file);
				$.ajax({
					url : '/TicketingSolutionRest/ticketImageUpload',
					type : 'POST',
					data : formData,
					cache : false,
					async : false,
					contentType : false,
					processData : false,
					success : function(data, textStatus, jqXHR) {
						var message = jqXHR.responseText;
						if (message == "0") {
							alert("Hata oluştu");
						} else {
							console.log(x);
							pictureElements[x].status = 'C';// c as current
							pictureElements[x].dbFileName = data;
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("Resim yüklenirken hata meydana geldi!");
					}
				});
			}
		}
	}
}

function uploadTicketBgImage() {
	if (bgPicture.length > 0) {
		if (bgPicture[0].status == 'N') {
			var file = bgPicture[0].file;

			var formData = new FormData();
			formData.append('file', file);
			$.ajax({
				url : '/TicketingSolutionRest/bgUpload',
				type : 'POST',
				data : formData,
				cache : false,
				async : false,
				contentType : false,
				processData : false,
				success : function(data, textStatus, jqXHR) {
					var message = jqXHR.responseText;
					if (message == "0") {
						alert("Hata oluştu");
					} else {
						bgPicture[0].status = 'C';// c as current
						bgPicture[0].dbFileName = data;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("Resim yüklenirken hata meydana geldi!");
				}
			});

		}
	}
}

function ticketPreview(ticketPreview, pictureElements) {
	if (isSaved) {
		$("#ticketPreviewModal").load(
				"TicketPreview.jsp?ticketDesignId="
						+ $("#ticketDesignId").val()).dialog({
			height : 500,
			width : 950,
			modal : true,
			buttons : {
				Cancel : function() {
					$("#ticketPreviewModal").html("");
					$(".ui-dialog-titlebar-close").trigger('click');
				}
			},
			close : function() {
				// burası modal kapanırken çalışıyor
			}
		});
	} else {
		alert("Önizleme yapmadan önce kaydetmelisiniz!");
	}
}