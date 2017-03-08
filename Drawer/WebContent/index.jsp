<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<script type="text/javascript" src="js/areas.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/ButtonLabel.js"></script>
<script type="text/javascript" src="js/HallDesigner.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/HallDesigner.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<body>
	<input type="hidden" value="<%=request.getParameter("seatingPlanId")%>"
		id="seatingPlanId">
	<input type="hidden" value="" id="hallId">
	<input type="file" name="myFileSelect"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" />
	<input type="button" id="startDraw" value="Çizime Başla"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<input type="button" id="endDraw" value="Çizimi Bitir"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<input type="button" id="saveDraw" value="Kaydet"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<div style="width: 100%; overflow: hidden;">
		<div style="width: 600px; float: left;" id="canvasContainer">
			<canvas width="150" height="50" id="myCanvas"
				style="position: relative; margin-left: 0px; margin-top: 0px; border: 0px solid #000000;"></canvas>
			<br />
		</div>
		<div style="margin-left: 620px;">
			<div id="areaList"></div>
		</div>
	</div>
	<div id="dialog-form" title="Yeni Blok Oluştur">
		<p class="validateTips">Tüm alanların girilmesi zorunludur.</p>
		<form>
			<fieldset>
				<label for="name">Blok Adı</label> <select name="hallBlock"
					id="hallBlock" style="width: 250px">
					<option value="-">Blok seçiniz...</option>
				</select> <label for="name">Kapasite</label> <input type="text"
					name="capacity" id="capacity" value=""
					class="text ui-widget-content ui-corner-all"> Oturma Düzeni
				Var<input type="checkbox" name="hasSeatPlan" value="true"><input
					type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>
	<div id="seatPlanner" title="Oturma Düzeni Oluşturma"></div>
	<div id="LoadingImage"
		style="display: none; position: fixed; top: 50%; left: 50%; -webkit-transform: translate(-50%, -50%); transform: translate(-50%, -50%);">
		<img src="/TicketingSolutionManager/plugin/images/ajax-loader.gif" />
	</div>
</body>
</html>
<script>
	var seatingPlan;

	$(document)
			.ready(
					function() {
						var seatingPlanId = $('#seatingPlanId').val();
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/getSeatingPlanById/?id="
											+ seatingPlanId,
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
											$('#hallId').val(
													data.response.hallId);
											seatingPlan = data;
											if (data.response.background != "") {
												loadImageFromUrl(data.response.background);
											}
											loadHallBlocks();
										} else {
											alert(data.errorDesc);
										}
									}
								});
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/getSeatingPlanDetailBySeatingPlanId/?seatingPlanId="
											+ seatingPlanId,
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
											reloadSeatingPlanDetails(data);
										} else {
											alert(data.errorDesc);
										}
									}
								});
					});
</script>