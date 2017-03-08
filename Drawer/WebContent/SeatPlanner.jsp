<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/SeatPlanner.js"></script>
<link rel="stylesheet" href="css/SeatPlanner.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<script>
	$(document)
			.ready(
					function() {
						var seatCategoryColorList = [];
						var seatCategoryColor;
						var seatingPlanDetailId =
<%=request.getParameter("seatingPlanDetailId")%>
	;
						var datas;
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/getAllSeatCategories/",
									dataType : "json", // data type of response
									async : false,
									beforeSend : function() {
										$("#LoadingImageSeatPlanner").show();
									},
									complete : function() {
										$("#LoadingImageSeatPlanner").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {

											datas += "<option value=->Kategori Seçiniz</option>";
											for (var i = 0; i < data.response.seatCategoryList.length; i++) {
												seatCategoriesIds
														.push(data.response.seatCategoryList[i].id);
												seatCategoryColorList
														.push(data.response.seatCategoryList[i].color);
												datas += "<option value="+data.response.seatCategoryList[i].color+">"
														+ data.response.seatCategoryList[i].name
														+ "</option>";
											}

											$('#category').html(datas);
										} else {
											alert(data.errorDesc);
										}
									}
								});

						//get seat design
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/getSeatDesignBySeatingPlanDetailId/?seatingPlanDetailId="
											+ seatingPlanDetailId,
									dataType : "json", // data type of response
									async : false,
									beforeSend : function() {
									},
									complete : function() {
									},
									success : function(data) {
										if (data.errorCode == "0") {
											for (var i = 0; i < data.response.seatDesign.length; i++) {

												//get seat divs

												$
														.ajax({
															type : 'GET',
															url : "/TicketingSolutionRest/getSeatDivBySeatDesignId/?seatDesignId="
																	+ data.response.seatDesign[i].id,
															dataType : "json", // data type of response
															async : false,
															beforeSend : function() {
															},
															complete : function() {
															},
															success : function(
																	dataDetail) {
																if (dataDetail.errorCode == "0") {
																	var seatObjects = [];
																	seatNumber = data.response.seatDesign[i].seatNumber;

																	rowPattern = data.response.seatDesign[i].rowPattern;

																	var seatDivs = [];
																	var rowNameByAlphabet = data.response.seatDesign[i].rowName;

																	for (var x = 0; x < dataDetail.response.seatDiv.length; x++) {
																		var seatCategoryId = 0;
																		seatCategoryId = dataDetail.response.seatDiv[x].seatCategory;
																		seatCategoryId++;
																		seatCategoryId--;

																		//var seatCategoryColorName=;

																		seat = {
																			id : dataDetail.response.seatDiv[x].id,
																			seatType : dataDetail.response.seatDiv[x].seatType,
																			category : dataDetail.response.seatDiv[x].seatCategory,
																			color : seatCategoryColorList[seatCategoriesIds
																					.indexOf(seatCategoryId)],
																			divId : dataDetail.response.seatDiv[x].divId
																		}
																		seatObjects
																				.push(seat);
																	}

																	addRow(
																			seatNumber,
																			rowPattern,
																			rowNameByAlphabet,
																			seatObjects);
																	totalRowNumber++;

																} else {
																	alert(dataDetail.errorDesc);
																}
															}
														});

											}
											refreshDraw();

										} else {
											alert(data.errorDesc);
										}
									}
								});
						$("#LoadingImageSeatPlanner").hide();
					});
</script>
<body>

	<div class="formContainer">
		<form style="width: 950px">
			<label for="name" class="label">Kategori</label> <select
				name="category" id="category" class="patternTextbox">
			</select> <label for="name" class="label">Sıra Sayısı</label> <input
				type="text" name="rowNumber" id="rowNumber" value="2"
				class="patternTextbox"> <label for="name" class="label">Koltuk
				Sayısı</label> <input type="text" name="seatNumber" id="seatNumber"
				value="10" class="patternTextbox"> <input type="button"
				id="createPlan" name="createPlan" value="Oluştur"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
			<input type="button" id="changeCategory" name="changeCategory"
				value="Kategori Değiştir"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
		</form>
	</div>
	<br />
	<div class="stageContainer" id="stageContainer">Sahne</div>
	<br />
	<div class="seatContainer" id="seatContainer"></div>
</body>
<div id="LoadingImageSeatPlanner"
	style="position: fixed; top: 50%; left: 50%; -webkit-transform: translate(-50%, -50%); transform: translate(-50%, -50%);">
	<img src="/TicketingSolutionManager/plugin/images/ajax-loader.gif" />
</div>
</html>