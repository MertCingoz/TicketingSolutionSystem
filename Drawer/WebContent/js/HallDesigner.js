var pointsArray = [];
var points = [];
var tempPoints = [];
var areas;
var context;
var img;
var offset;
var movingArea = -1;
var selectedArea = -1;
var lastX = 0;
var lastY = 0;
var canvas;
var dialog;
var areaList = [];
var fillColor = 'rgba(0, 255, 0, 0.5)';
var updateMode = false;
var hasAlreadyImage = false;
var seatDesignIds = [];
function loadImageFromUrl(imageUrl) {
	hasAlreadyImage = true;
	img = new Image();
	img.src = imageUrl;
	areas = new Areas("myCanvas");
	canvas = areas.getCanvas();
	context = areas.getContext();

	img.onload = function() {
		context.drawImage(img, 0, 0);
		canvas.width = this.width;
		canvas.height = this.height;
		$("#canvasContainer").width(this.width);
		$("#canvasContainer").height(this.height);
		$(startDrawButton).attr("disabled", false);
		$(endDrawButton).attr("disabled", false);
		context.drawImage(img, 0, 0);
	}
}

function updateTips(t) {
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}

function checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips(n + " uzunluğu en az " + min + " en çok " + max
				+ " karakter olmalıdır.");
		return false;
	} else {
		return true;
	}
}

function checkRegexp(o, regexp, n) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n);
		return false;
	} else {
		return true;
	}
}

/*
 * refresh block list
 */
function refreshList() {
	var htmlOutput = "<table><tr><td>Blok Adı</td><td>Kapasite</td><td>Oturma Düzeni</td><td></td></tr>";
	for (var j = 0; j < areaList.length; j++) {
		var hasSeatPlanString = "";
		if (areaList[j].hasAreaSeatPlan) {
			hasSeatPlanString = "Var";
		} else {
			hasSeatPlanString = "Yok";
		}
		htmlOutput = htmlOutput
				+ ("<tr style='cursor:pointer' id='tr"
						+ j
						+ "'><td>"
						+ areaList[j].hallBlockName
						+ "</td><td>"
						+ areaList[j].capacity
						+ "</td><td>"
						+ hasSeatPlanString
						+ "</td><td><input type='button' value='...' onclick='tableRowClick("
						+ j
						+ ")' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'><input type='button' value='Sil' onclick='removeFromAreaList("
						+ j + ")' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'></td></tr>");
	}
	htmlOutput = htmlOutput + "</table>";
	$("#areaList").html(htmlOutput);
}

function tableRowClick(j) {
	if (areaList[j].seatingPlanDetailId != "0") {
		if (areaList[j].hasAreaSeatPlan) {
			$("#seatPlanner")
					.load(
							"SeatPlanner.jsp?seatingPlanDetailId="
									+ areaList[j].seatingPlanDetailId)
					.dialog(
							{
								height : 500,
								width : 950,
								modal : true,
								buttons : {
									"Kaydet" : function() {
										$("#LoadingImageSeatPlanner").show();
										$
												.ajax({
													type : 'GET',
													url : "/TicketingSolutionRest/deleteAllSeatDesignBySeatingPlanDetailId/?seatingPlanDetailId="
															+ areaList[j].seatingPlanDetailId,
													dataType : "json",
													async : false,
													beforeSend : function() {
														$("#LoadingImage")
																.show();
													},
													complete : function() {
														$("#LoadingImage")
																.hide();
													},
													success : function(data) {
														if (data.errorCode == "0") {
															for (var z = 0; z < totalRowNumber; z++) {
																$
																		.ajax({
																			type : 'GET',
																			url : "/TicketingSolutionRest/createSeatDesign/?seatingPlanDetailId="
																					+ areaList[j].seatingPlanDetailId
																					+ "&rowName="
																					+ areaSeatMap[z].rowName
																					+ "&seatNumber="
																					+ areaSeatMap[z].seatNumber
																					+ "&rowPattern="
																					+ areaSeatMap[z].seatPattern
																					+ "&rowOrder="
																					+ z
																					+ "&seatDivs="
																					+ getDivFromPattern(z),
																			dataType : "json",
																			async : false,
																			beforeSend : function() {
																				$(
																						"#LoadingImage")
																						.show();
																			},
																			complete : function() {
																				// $("#LoadingImage").hide();
																			},
																			success : function(
																					data) {
																				if (data.errorCode == "0") {
																					for (var t = 0; t < areaSeatMap[z].seatDivs.length; t++) {

																						$
																								.ajax({
																									type : 'GET',
																									url : "/TicketingSolutionRest/createSeatDiv/?seatDesignId="
																											+ data.response.result.id
																											+ "&divId="
																											+ areaSeatMap[z].seatDivs[t].divId
																											+ "&seatType="
																											+ areaSeatMap[z].seatDivs[t].seatType
																											+ "&seatCategory="
																											+ areaSeatMap[z].seatDivs[t].category,
																									dataType : "json",
																									async : false,
																									beforeSend : function() {
																										// $("#LoadingImage").show();
																									},
																									complete : function() {
																										$(
																												"#LoadingImage")
																												.hide();
																									},
																									success : function(
																											data) {
																										if (data.errorCode == "0") {
																										} else {
																											alert(data.errorDesc);
																										}
																									}
																								});

																					}
																				} else {
																					alert(data.errorDesc);
																				}
																			}
																		});
															}

														} else {
															alert(data.errorDesc);
														}
													}
												});
										$("#LoadingImageSeatPlanner").hide();
										alert("Kayıt işlemi tamamlandı!");
										$("#seatPlanner").html("");
										$(".ui-dialog-titlebar-close").trigger(
												'click');
									},
									Cancel : function() {
										$("#seatPlanner").html("");
										$(".ui-dialog-titlebar-close").trigger(
												'click');
									}
								},
								close : function() {
									// burası modal kapanırken çalışıyor
								}
							});
		} else {
			alert("Oturma düzeni olmayan alanarda düzenleme yapılamaz!");
		}
	} else {
		alert("Bu işlemi yapabilmek için öncelikle değişiklikleri kaydetmelisiniz!");
	}
}

/*
 * create a span for mouse down event
 */
function getSpanPointer(x, y) {
	return $('<span class="spot">').css({
		'position' : 'absolute',
		'background-color' : '#000000',
		'width' : '4px',
		'height' : '4px',
		'top' : y - 2,
		'left' : x - 2
	});
}

/*
 * ends drawing and open dialog for area information
 */
function endDrawing(openDialog) {
	if (isDrawingContinue) {
		pointsArray.push(points);
		selectedArea = pointsArray.length - 1;
		areas.setStage(function() {
			drawRefresh();
		});
		isDrawingContinue = false;
		points = [];
		if (openDialog) {
			dialog.dialog("open");
		}
		$('#myCanvas').css('cursor', 'auto');
	} else {
		alert("Çizime başlamadan çizimi sonlandıramazsınız!");
	}
}

function uploadSeatingPlanImage() {
	if ($('input[name="myFileSelect"]').val() == "") {
		updateSeatingPlanDetail();
	} else {
		$('input[name="myFileSelect"]').each(
				function(index, value) {
					var file = value.files[0];
					if (file) {
						var formData = new FormData();
						formData.append('file', file);
						$.ajax({
							url : '/TicketingSolutionRest/singleUpload',
							type : 'POST',
							data : formData,
							cache : false,
							contentType : false,
							processData : false,
							success : function(data, textStatus, jqXHR) {
								var message = jqXHR.responseText;
								if (message == "0") {
									alert("Hata oluştu");
								} else {
									updateSeatingPlanImage($("#seatingPlanId")
											.val(), message);
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert("Resim yüklenirken hata meydana geldi!");
							}
						});
					}
				});
	}

}

function updateSeatingPlanImage(id, background) {
	$.ajax({
		type : 'GET',
		url : "/TicketingSolutionRest/updateSeatingPlanImage/?id=" + id
				+ "&background=" + background,
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
				updateSeatingPlanDetail();
			} else {
				alert("hata");
			}
		}
	});
}

function updateSeatingPlanDetail() {
	var seatingPlanId = $("#seatingPlanId").val();
	var activeSeatingPlanDetailIds = "";
	for (var j = 0; j < areaList.length; j++) {
		var seatingPlanDetailId = areaList[j].seatingPlanDetailId;
		var hallBlockId = areaList[j].hallBlockId;
		var hallBlockCapacity = areaList[j].capacity;
		var hasSeatingPlan = areaList[j].hasAreaSeatPlan;
		var drawingArea = "";
		var createdSuccesfully = false;
		for (var m = 0; m < pointsArray[j].length; m++) {
			drawingArea = drawingArea + pointsArray[j][m] + "|";
		}
		drawingArea = drawingArea.substring(0, drawingArea.length - 1);

		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/createSeatingPlanDetail/?seatingPlanDetailId="
							+ seatingPlanDetailId
							+ "&seatingPlanId="
							+ seatingPlanId
							+ "&hallBlockId="
							+ hallBlockId
							+ "&hallBlockCapacity="
							+ hallBlockCapacity
							+ "&hasSeatingPlan="
							+ hasSeatingPlan
							+ "&drawingArea=" + drawingArea,
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
							createdSuccesfully = true;
							areaList[j].seatingPlanDetailId = data.response.result.id;
						} else {
							alert("hata");
						}
					}
				});
	}

	if (createdSuccesfully) {
		for (var xx = 0; xx < areaList.length; xx++) {
			if (areaList[xx].seatingPlanDetailId != 0) {
				activeSeatingPlanDetailIds = activeSeatingPlanDetailIds
						+ areaList[xx].seatingPlanDetailId + "|";
			}
		}
		activeSeatingPlanDetailIds = activeSeatingPlanDetailIds.substring(0,
				activeSeatingPlanDetailIds.length - 1);
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/changeSeatingPlanDetailStatusToPassive/?seatingPlanId="
							+ seatingPlanId
							+ "&activeSeatingPlanDetailIds="
							+ activeSeatingPlanDetailIds,
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
							alert("Başarılı olarak kaydedildi!");
						} else {
							alert("hata");
						}
					}
				});
	}

}

function reloadSeatingPlanDetails(data) {
	$
			.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/getAllHallBlocksByHallId/?hallId="
						+ $("#hallId").val(),
				dataType : "json", // data type of response
				async : false,
				beforeSend : function() {
					$("#LoadingImage").show();
				},
				complete : function() {
					$("#LoadingImage").hide();
				},
				success : function(hallBlockData) {
					if (data.errorCode == "0") {
						areas.clear();
						for (var i = 0; i < data.response.seatingPlanDetailList.length; i++) {
							points = [];
							points = data.response.seatingPlanDetailList[i].drawingArea
									.split("|");
							isDrawingContinue = true;
							endDrawing(false);
							var hallBlockName = "";

							for (var z = 0; z < hallBlockData.response.hallBlockList.length; z++) {
								if (data.response.seatingPlanDetailList[i].hallBlockId == hallBlockData.response.hallBlockList[z].id) {
									hallBlockName = hallBlockData.response.hallBlockList[z].name;
								}
							}
							if (data.response.seatingPlanDetailList[i].hasSeatingPlan == "Y") {
								hasSeatingPlan = true;
							} else {
								hasSeatingPlan = false;
							}
							var area = {
								seatingPlanDetailId : data.response.seatingPlanDetailList[i].id,
								hallBlockName : hallBlockName,
								hallBlockId : data.response.seatingPlanDetailList[i].hallBlockId,
								capacity : data.response.seatingPlanDetailList[i].hallBlockCapacity,
								hasAreaSeatPlan : hasSeatingPlan
							};
							areaList.push(area);

						}
						drawRefresh();
						refreshList();
					} else {
						alert(data.errorDesc);
					}
				}
			});

}

/*
 * refresh drawing on hall map
 */
function drawRefresh() {
	areas.clear();
	context.drawImage(img, 0, 0);

	for (var j = 0; j < pointsArray.length; j++) {
		$(".spot").remove();
		areas.beginRegion();
		context.beginPath();
		var pointsLocal = pointsArray[j];
		context.moveTo(pointsLocal[0] - offset.left, pointsLocal[1]
				- offset.top);
		for (var i = 0; i < pointsLocal.length; i = i + 2) {
			x = parseInt(jQuery.trim(pointsLocal[i]));
			y = parseInt(jQuery.trim(pointsLocal[i + 1]));
			context.lineTo(x - offset.left, y - offset.top);
		}
		context.lineTo(pointsLocal[0] - offset.left, pointsLocal[1]
				- offset.top);
		if (j == selectedArea) {
			context.fillStyle = fillColor;
			context.fill();
		}
		context.closePath();
		context.stroke();
		areas.addRegionEventListener("mousemove", function() {
			var mousePos = areas.getMousePos();
			var mouseX = mousePos.x - 50;
			var mouseY = mousePos.y - 50;
			if (selectedArea != movingArea) {
				context.fillStyle = fillColor;
				context.fill();
			}
			movingArea = j;
			for (var k = 0; k < areaList.length; k++)
				clearRowBackground();

			$('#tr' + j).children('td, th').css('background-color', 'yellow');
		});

		areas.addRegionEventListener("mouseout", function() {
			clearRowBackground();
		});

		areas.addRegionEventListener("mousedown", function() {
			selectedArea = j;
			setFormValues(j);
			updateMode = true;
			dialog.dialog("open");
		});
		areas.closeRegion();
	}
}

function clearRowBackground() {
	for (var k = 0; k < areaList.length; k++)
		$('#tr' + k).children('td, th').css('background-color', 'white');
}

/*
 * set modal form values with nth element - @param {Number} j
 */
function setFormValues(j) {
	$(hallBlockId).val(areaList[j].hallBlockId);
	$(formCapacityArea).val(areaList[j].capacity);
	if (areaList[j].hasAreaSeatPlan) {
		document.querySelector('input[name="hasSeatPlan"]').checked = true;
	} else {
		document.querySelector('input[name="hasSeatPlan"]').checked = false;
	}
}

/*
 * remove last point of an array
 */
function removeLastPoint() {
	tempPoints = []
	for (var i = 0; i < points.length - 2; i = i + 2) {
		tempPoints.push(points[i], points[i + 1]);
	}
	points = [];
	for (var i = 0; i < tempPoints.length; i = i + 2) {
		points.push(tempPoints[i], tempPoints[i + 1]);
	}
	if (points.length > 0) {
		lastX = points[points.length];
		lastY = points[points.length - 1];
	} else {
		areas.clear();
		context.drawImage(img, 0, 0);
	}
}

function removeFromAreaList(areaIndex) {
	removeFromPointsArray(areaIndex);
	var tempAreaList = []
	for (var i = 0; i < areaList.length; i++) {
		tempAreaList.push(areaList[i]);
	}
	areaList = [];
	for (var i = 0; i < tempAreaList.length; i++) {
		if (i != areaIndex) {
			areaList.push(tempAreaList[i]);
		}
	}
	refreshList();
	drawRefresh();
}

function removeFromPointsArray(pointsIndex) {
	var tempPointsArray = []
	for (var i = 0; i < pointsArray.length; i++) {
		tempPointsArray.push(pointsArray[i]);
	}
	pointsArray = [];
	for (var i = 0; i < tempPointsArray.length; i++) {
		if (i != pointsIndex) {
			pointsArray.push(tempPointsArray[i]);
		}
	}
}

$(function() {
	$("#openwindow").dialog({
		open : function(event, ui) {
			$('#divInDialog').load('test.html', function() {
				alert('Load was performed.');
			});
		}
	});
	areas = new Areas("myCanvas");
	canvas = areas.getCanvas();
	context = areas.getContext();
	offset = $(canvasName).offset();
	img = new Image();

	$(startDrawButton).attr("disabled", true);
	$(endDrawButton).attr("disabled", true);

	var form, hallBlockId = $(hallBlockId), capacity = $(formCapacityArea), allFields = $(
			[]).add(name).add(capacity), tips = $(".validateTips");

	function addArea() {
		if (!updateMode) {
			for (var j = 0; j < areaList.length; j++) {
				var hallBlockId = areaList[j].hallBlockId;
				if (hallBlockId == $("#hallBlock").val()) {
					alert("Bu blok için daha önce ekleme yapıldı!Tekrar ekleyemezsiniz!");
					return;
				}
			}
		}
		var valid = true;
		allFields.removeClass("ui-state-error");
		// valid = valid && checkLength(name, "Blok adı ", 1, 16);
		var hasSeatPlan = document.querySelector('input[name="hasSeatPlan"]').checked;
		if (valid) {
			if (!updateMode) {
				var area = {
					seatingPlanDetailId : "0",
					hallBlockName : $("#hallBlock option:selected").text(),
					hallBlockId : $("#hallBlock").val(),
					capacity : capacity.val(),
					hasAreaSeatPlan : hasSeatPlan
				};
				areaList.push(area);
			} else {
				areaList[selectedArea].hallBlockName = $(
						"#hallBlock option:selected").text();
				areaList[selectedArea].hallBlockId = $("#hallBlock").val();
				areaList[selectedArea].capacity = capacity.val();
				areaList[selectedArea].hasAreaSeatPlan = hasSeatPlan;
				updateMode = false;
			}
			refreshList();
			dialog.dialog("close");
		}
		return valid;
	}

	dialog = $("#dialog-form").dialog({
		autoOpen : false,
		height : 380,
		width : 300,
		modal : true,
		buttons : {
			"Kaydet" : addArea,
			Cancel : function() {
				updateMode = false;
				removeFromPointsArray(areaList.length);
				drawRefresh();
				dialog.dialog("close");
			}
		},
		close : function() {
			form[0].reset();
			allFields.removeClass("ui-state-error");
		}
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		addArea();
	});

	$(document).on("click", ".closeIt", function() {
		var parent = $(this).parent();
		parent.remove();
	});

	$("input[name='myFileSelect']").on("change", function() {
		if (hasAlreadyImage) {
			alert("Resim güncelleme yapamazsınız!");
			return;
		}
		var files = !!this.files ? this.files : [];
		if (!files.length || !window.FileReader)
			return;

		if (/^image/.test(files[0].type)) {
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onloadend = function() {
				img.src = this.result;
				img.onload = function() {
					context.drawImage(img, 0, 0);
					canvas.width = this.width;
					canvas.height = this.height;
					$("#canvasContainer").width(this.width);
					$("#canvasContainer").height(this.height);
					$(startDrawButton).attr("disabled", false);
					$(endDrawButton).attr("disabled", false);
					context.drawImage(img, 0, 0);
				}
			}
		}
	});

	$(canvasName)
			.mousemove(
					function(e) {
						areas.clear();
						context.drawImage(img, 0, 0);
						if (pointsArray.length > 0)
							drawRefresh();

						if (isDrawingContinue) {
							$(".spot").remove();
							context.beginPath();
							if (points.length > 0) {
								context.moveTo(points[0] - offset.left,
										points[1] - offset.top);
								for (var i = 0; i < points.length; i = i + 2) {
									x = parseInt(jQuery.trim(points[i]));
									y = parseInt(jQuery.trim(points[i + 1]));
									context.lineTo(x - offset.left, y
											- offset.top);
									var pointer = getSpanPointer(x, y);
									$(document.body).append(pointer);
								}
								context.stroke();
								context.moveTo(lastX - offset.left, lastY
										- offset.top);
								context.lineTo(e.offsetX, e.offsetY);
								context.stroke();

							}
							context.closePath();
						}
					});

	$(canvasName).on("contextmenu", function(e) {
		return false;
	});

	$(canvasName).mousedown(function(e) {
		if (isDrawingContinue) {
			if (e.which == 1) {
				lastX = e.pageX;
				lastY = e.pageY;
				points.push(lastX, lastY);
				var pointer = getSpanPointer(lastX, lastY);
				$(document.body).append(pointer);
			} else if (e.which == 3) {
				removeLastPoint();
			} else {

			}
		} else {
			// drawRefresh();
		}
	});

	$(endDrawButton).click(function() {
		endDrawing(true);
	});

	$(saveDrawButton).click(function() {
		uploadSeatingPlanImage();
	});

	$(startDrawButton).click(function() {
		if (isDrawingContinue) {
			alert("Devam eden çizim varken yeni bir çizime başlayamazsınız!");
		} else {
			$('#myCanvas').css('cursor', 'crosshair');
			if (isDrawingContinue) {
				isDrawingContinue = false;
			} else {
				isDrawingContinue = true;
			}
		}
	});

});

function loadHallBlocks() {
	var datas = "";
	var hallId = $("#hallId").val();
	$
			.ajax({
				type : 'GET',
				url : "/TicketingSolutionRest/getAllHallBlocksByHallId/?hallId="
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
						datas += "<option value=->Blok Seçiniz</option>";
						for (var i = 0; i < data.response.hallBlockList.length; i++) {
							datas += "<option value="
									+ data.response.hallBlockList[i].id + ">"
									+ data.response.hallBlockList[i].name
									+ "</option>";
						}
						$('#hallBlock').html(datas);
					} else {
						alert(data.errorDesc);
					}
				}
			});
}