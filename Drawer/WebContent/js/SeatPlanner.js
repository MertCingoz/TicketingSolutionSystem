var maxWidth = 0;
var areaSeatMap = [];
var totalRowNumber = 0;
var selectedSeats = [];
var seatCategoriesIds = [];
var alphabet = "ABCÇDEFGHIİJKLMNOÖPRSŞTUÜVYZ".split("");
$(document)
		.ready(
				function() {
					$("#createPlan")
							.click(
									function() {
										if ($('#category option:selected')
												.index() == 0) {
											alert("Lütfen kategori seçiniz!");
										} else {
											rowNumber = $("#rowNumber").val();
											seatNumber = $("#seatNumber").val();
											for (var i = 0; i < rowNumber; i++) {
												rowPattern = "";
												for (var j = 0; j < seatNumber; j++) {
													rowPattern += "k";
												}
												var seatDivs = [];
												var rowNameByAlphabet = alphabet[totalRowNumber];
												for (var t = 0; t < areaSeatMap.length; t++) {
													if (areaSeatMap[t].rowName == alphabet[totalRowNumber]) {
														rowNameByAlphabet = alphabet[totalRowNumber]
																+ totalRowNumber;
													}
												}
												seatDivs = getSeatObjectsFromPattern(
														rowNameByAlphabet,
														rowPattern,
														seatCategoriesIds[$(
																'#category option:selected')
																.index() - 1],
														$(
																'#category option:selected')
																.val());
												addRow(seatNumber, rowPattern,
														rowNameByAlphabet,
														seatDivs);
												totalRowNumber++;
											}
											refreshDraw();
										}
									});
					$("#changeCategory")
							.click(
									function() {
										console.log(areaSeatMap);
										for (var i = 0; i < selectedSeats.length; i++) {
											for (var j = 0; j < areaSeatMap.length; j++) {
												for (var k = 0; k < areaSeatMap[j].seatDivs.length; k++) {
													if (areaSeatMap[j].seatDivs[k].divId == selectedSeats[i]) {
														areaSeatMap[j].seatDivs[k].category = seatCategoriesIds[$(
																'#category option:selected')
																.index() - 1];
														areaSeatMap[j].seatDivs[k].color = $(
																'#category option:selected')
																.val();
													}
												}
											}
										}
										console.log(areaSeatMap);
										refreshDraw();
										selectedSeats = [];
									});
				});

function getSeatObjectsFromPattern(rowNameByAlphabet, rowPattern, category,
		color) {
	rowLabel = 1;
	var rowsString = "";
	seatObjects = [];
	for (var j = 0; j < rowPattern.length; j++) {
		var id = "";
		var seatType = rowPattern.substring(j, j + 1);
		if (seatType == "k") {
			id = rowNameByAlphabet + rowLabel;
			rowLabel++;
		} else if (seatType == "c") {
			id = rowNameByAlphabet + rowLabel;
			rowLabel++;
			rowLabel++;
		} else if (seatType == "_") {
			id = rowNameByAlphabet + "_" + rowLabel;
		} else if (seatType == "x") {
			id = rowNameByAlphabet + rowLabel;
			rowLabel++;
		} else if (seatType == "X") {
			id = rowNameByAlphabet + rowLabel;
			rowLabel++;
			rowLabel++;
		}
		seat = {
			id : id,
			seatType : seatType,
			category : category,
			color : color,
			divId : rowNameByAlphabet + (rowLabel-1)
		}
		seatObjects.push(seat);
	}
	return seatObjects;

}

function getWidthFromPattern(rowPattern) {
	var newWidth = 0;
	for (var j = 0; j < rowPattern.length; j++) {
		var seatType = rowPattern.substring(j, j + 1);
		if (seatType == "k" || seatType == "_" || seatType == "x") {
			newWidth += 35;
		} else if (seatType == "c" || seatType == "X") {
			newWidth += 70;
		} else {
			newWidth += 35;
		}
	}
	return newWidth;
}

function getDivFromPattern(k) {
	rowLabel = 1;
	rowName = areaSeatMap[k].rowName;
	rowPattern = areaSeatMap[k].seatPattern;
	var rowsString = "";
	for (var j = 0; j < rowPattern.length; j++) {
		var seatType = rowPattern.substring(j, j + 1);
		if (seatType == "k") {
			rowsString += "<div onclick=selectSeat(" + rowName + rowLabel
					+ ") class='seat' id='" + rowName + rowLabel
					+ "' style='background-color:#"
					+ areaSeatMap[k].seatDivs[j].color + "'>" + rowLabel
					+ "</div>";
			rowLabel++;
		} else if (seatType == "c") {
			rowsString += "<div class='doubleseat' onclick=selectSeat("
					+ rowName + rowLabel + ")  id='" + rowName + rowLabel
					+ "' style='background-color:#"
					+ areaSeatMap[k].seatDivs[j].color + "'>" + rowLabel + "-"
					+ (rowLabel + 1) + "</div>";
			rowLabel++;
			rowLabel++;
		} else if (seatType == "_") {
			rowsString += "<div class='empty' id='" + rowName + "_" + rowLabel
					+ "'></div>";
		} else if (seatType == "x") {
			rowsString += "<div class='blockedseat' id='" + rowName + rowLabel
					+ "'>" + rowLabel + "</div>";
			rowLabel++;
		} else if (seatType == "X") {
			rowsString += "<div class='blockeddoubleseat' id='" + rowName
					+ rowLabel + "'>" + rowLabel + "-" + (rowLabel + 1)
					+ "</div>";
			rowLabel++;
			rowLabel++;
		} else {
			alert(seatType);
			alert("Tanımlı olmayan koltuk kodu tespit edildi!yerine boş koltuk yerleştirildi!");
			rowsString += "<div class='empty'></div>";
		}
	}
	return rowsString;
}

function addRow(seatNumber, seatPattern, rowName, seatDivs) {
	row = {
		rowName : rowName,
		seatNumber : seatNumber,
		seatPattern : seatPattern,
		seatDivs : seatDivs
	}
	areaSeatMap.push(row);
}

function selectSeat(seatId) {
	$(seatId).css("background-color", "blue");
	selectedSeats.push($(seatId).attr('id'));
}

function refreshDraw() {
	var newWidth = 0;
	var rowPattern = "";
	var rowsString = "";
	$("#seatContainer").html("");
	for (var k = 0; k < areaSeatMap.length; k++) {
		newWidth = 0;
		rowsString = "";
		rowPattern = "";
		rowLabel = 1;
		rowsString = getDivFromPattern(k);
		newWidth = getWidthFromPattern(areaSeatMap[k].seatPattern);
		var bodyAppendStr = "<div id='row" + k + "' class='row'>";
		bodyAppendStr += "<div class='inputsPattern'>";
		bodyAppendStr += "<input type='text' class='patternTextbox' name='patternText'"
				+ k
				+ " id='patternText"
				+ k
				+ "' value='"
				+ areaSeatMap[k].seatPattern
				+ "' onblur='refreshSeatPatternOnRow(" + k + ")'>";
		bodyAppendStr += "</div>";
		bodyAppendStr += "<div class='inputsRowName'>";

		bodyAppendStr += "<input type='text' class='rowNameTextbox' name='rowName"
				+ k
				+ "' id='rowName"
				+ k
				+ "' value="
				+ areaSeatMap[k].rowName
				+ " onblur=onRowNameChange(" + k + ")>";
		bodyAppendStr += "</div>";
		bodyAppendStr += rowsString;
		bodyAppendStr = bodyAppendStr + "</div>";
		$("#seatContainer").append(bodyAppendStr);
		newWidth = newWidth + 175;
		if (maxWidth < newWidth) {
			maxWidth = newWidth;
		}
	}

	$('.row').css({
		width : maxWidth
	});
}

function onRowNameChange(k) {
	var rowNameVal = $("#rowName" + k).val();
	for (var i = 0; i < areaSeatMap.length; i++) {
		if (areaSeatMap[i].rowName == rowNameVal && i != k) {
			alert("Satır adı farklı olmalıdır!");
			refreshDraw();
			return false;
		}
	}
	areaSeatMap[k].rowName = rowNameVal;
	areaSeatMap[k].seatDivs = getSeatObjectsFromPattern(rowNameVal,
			areaSeatMap[k].seatPattern, areaSeatMap[k].seatDivs[0].category,
			areaSeatMap[k].seatDivs[0].color);
}

function refreshSeatPatternOnRow(rowNum) {
	var newPattern = $("#patternText" + rowNum).val();
	areaSeatMap[rowNum].seatNumber = newPattern.length;
	areaSeatMap[rowNum].seatPattern = newPattern;
	areaSeatMap[rowNum].seatDivs = getSeatObjectsFromPattern(
			areaSeatMap[rowNum].rowName, newPattern,
			areaSeatMap[rowNum].seatDivs[0].category,
			areaSeatMap[rowNum].seatDivs[0].color);
	refreshDraw();

}