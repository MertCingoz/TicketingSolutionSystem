<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Bilet Tasarım Aracı</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/TicketDesigner.js"></script>
<style>
.labelClass {
	width: 100px;
	font-size: 12px;
	font-family: Arial;
}

.labelClassForIndex {
	width: 100px;
	font-size: 14px;
	font-family: Arial;
}

.inputTextClass {
	width: 150px;
	font-size: 12px;
	font-family: Arial;
}

.draggableForText {
	width: 180px;
	height: 25px;
	padding: 0.1em;
	float: left;
	margin: 0 1px 1px 0;
	position: absolute;
}

.draggable {
	width: 120px;
	height: 15px;
	padding: 0.1em;
	float: left;
	margin: 0 1px 1px 0;
	position: absolute;
}

#draggable, #draggable2 {
	margin-bottom: 0px;
}

#draggable {
	cursor: n-resize;
}

#draggable2 {
	cursor: e-resize;
}

#containment-wrapper {
	width: 915px;
	height: 284px;
	border: 1px solid #ccc;
	padding: 0px;
}

h3 {
	clear: left;
}
</style>
<script>
	$(function() {
		/* $("#draggable3").draggable({
			containment : "#containment-wrapper",
			scroll : false
		});
		$("#draggable3").resizable();
		 */
	});
</script>
</head>
<body>

	<select id="fieldNameCombo"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
		<option value="showName">Gösteri Adı</option>
		<option value="eventDate">Etkinlik Tarihi</option>
		<option value="eventTime">Etkinlik Saati</option>
		<option value="barcode">Barkod</option>
		<option value="block">Blok</option>
		<option value="row">Sıra</option>
		<option value="seat">Koltuk</option>
		<option value="venueName">Mekan Adı</option>
		<option value="hallName">Salon Adı</option>
		<option value="priceCategory">Fiyat Kategorisi</option>
		<option value="customerName">Müşteri Adı</option>
		<option value="customerSurname">Müşteri Soyadı</option>
		<option value="customerNationalId">Müşteri TC</option>
		<option value="ticketPrintingTime">Bilet Yazdırma Tarihi</option>
		<option value="serviceFee">Hizmet Bedeli</option>
		<option value="totalPrice">Toplam Tutar</option>
		<option value="transactionId">Bilet işlem numarası</option>
		<option value="freeText1">Serbest Yazı 1</option>
		<option value="freeText2">Serbest Yazı 2</option>
		<option value="freeText3">Serbest Yazı 3</option>
	</select>
	<input type="text" id="ticketDesignId"
		value="<%=request.getParameter("ticketDesignId")%>">
	<input type="button" id="addField" value="Alanını Bilete Ekle"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<label class="labelClassForIndex">Bilet üzerine</label>
	<input type='file' id="imgInp" value="Resim ekle"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" />
	<div id="containment-wrapper"></div>
	<br>
	<label class="labelClassForIndex">Arka Plan için</label>
	<input type="file" id="bgPictureSelect" name="bgPictureSelect"
		value="Resim ekle"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" />
	<br>
	<br>
	<!-- <label>Element Adı : </label -->
	<input type="text" id="elementName" readonly="readonly"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<br>
	<table>
		<tr>
			<td><label class="labelClassForIndex">X : </label></td>
			<td><input type="text" id="xAxis"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"></td>
		</tr>
		<tr>
			<td><label class="labelClassForIndex">Y : </label></td>
			<td><input type="text" id="yAxis"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"></td>
		</tr>
		<tr>
			<td><label class="labelClassForIndex">Genişlik : </label></td>
			<td><input type="text" id="width"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"></td>
		</tr>
		<tr>
			<td><label class="labelClassForIndex">Yükseklik : </label></td>
			<td><input type="text" id="height"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"></td>
		</tr>
		<tr>
			<td><label class="labelClassForIndex">Yazı Tipi : </label></td>
			<td><select
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
				id="font">
					<option value="Verdana">Verdana</option>
					<option value="Arial">Arial</option>
					<option value="Calibri">Calibri</option>
			</select></td>
		</tr>
		<tr>
			<td><label class="labelClassForIndex">YazıBoyut : </label></td>
			<td><input type="text" id="punto"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
				value="10"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="button" id="applyChanges" value="Uygula"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</table>
	<br>
	<br>
	<input type="button" id="ticketPreview" value="Ön İzleme"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<br>
	<br>
	<input type="button" id="saveChanges" value="Değişiklikleri Kaydet"
		class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
	<div id="ticketPreviewModal" title="Bilet Tasarım Özizlemesi"></div>
</body>
<script>
	$(document)
			.ready(
					function() {
						p = $("#containment-wrapper");
						position = p.position();
						console.log(position);
						containerX = position.left;
						containerY = position.top;

						var ticketDesignId = $('#ticketDesignId').val();
						if (ticketDesignId != "null") {
							$
									.ajax({
										type : 'GET',
										url : "/TicketingSolutionRest/getTicketDesignById/?id="
												+ ticketDesignId,
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
												bgPictureElement = {
													file : null,
													status : 'C',
													dbFileName : data.response.backgroundImage
												};
												bgPicture
														.push(bgPictureElement);
												loadBackgroundFromUrl(data.response.backgroundImage);
											} else {
												alert(data.errorDesc);
											}
										}
									});
							$
									.ajax({
										type : 'GET',
										url : "/TicketingSolutionRest/getAllTicketElementsByTicketDesignId/?ticketDesignId="
												+ ticketDesignId,
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
												for (var x = 0; x < data.response.ticketElementList.length; x++) {

													addElementFromDb(
															data.response.ticketElementList[x].id,
															data.response.ticketElementList[x].elementType,
															data.response.ticketElementList[x].fieldType,
															data.response.ticketElementList[x].order,
															data.response.ticketElementList[x].xAxis,
															data.response.ticketElementList[x].yAxis,
															data.response.ticketElementList[x].font,
															data.response.ticketElementList[x].size,
															data.response.ticketElementList[x].dbFileName,
															data.response.ticketElementList[x].width,
															data.response.ticketElementList[x].height,
															data.response.ticketElementList[x].picture);

												}
											} else {
												alert(data.errorDesc);
											}
										}
									});
						} else {
							$('#ticketDesignId').val("");
						}
					});

	$("#addField").click(function() {
		addElement();
	});

	$("#imgInp").change(function() {
		readURL(this);
	});
	$("#applyChanges").click(function() {
		applyChangesToObject();
	});
	$("#bgPictureSelect").change(function() {
		readURLforBgPicture(this);
	});
	$("#saveChanges").click(function() {
		saveChangesToDb();
	});
	$("#ticketPreview").click(function() {
		ticketPreview();
	});
</script>
</html>
