<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,java.io.*" %>
	

	
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Satış Yönetimi <small>Etkinlik bilet satışlarını bu ekrandan
				yapabilirsiniz</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="eventForm">
			<div class="col-md-6">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Etkinlik Satış Ekranı</h3>
					</div>
					<div class="box-body">
						<div class="form-group">
							<div class="col-xs-6">
								<input type="hidden" id="eventId"> <label for="speed">Gösteri
									seçiniz : </label><br> <select name="showListCombo"
									id="showListCombo" style="width: 250px">
									<option value="-">Gösteri seçiniz...</option>
								</select>
							</div>
							<div class="col-xs-6">
								<label for="speed">Etkinlik seçiniz : </label><br> <select
									name="eventListCombo" id="eventListCombo" style="width: 250px">
									<option value="-">Etkinlik seçiniz...</option>
								</select>
							</div>

						</div>
						<div id="priceListDiv"></div>

					</div>
					<div class="col-xs-6">
						<br> <label>Toplam bilet adedi : </label><input type="text"
							id="totalTicketCount" readonly></label> <br> <label>Toplam
							tutar : <input type="text" id="totalAmount" readonly> TL
						</label>
					</div>
					<div class="box-footer">
						<button type="button" class="btn btn-primary" id="saleTicket">Bilet
							Sat & Bastır</button>
					</div>

					<!-- /.box-body -->
				</div>
			</div>
			<div class="col-md-6">


				<!-- /.box -->
			</div>
		</form>
		<!-- /.box -->
		<div class="row">

			<!-- /.col -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<script>
	$("#showListCombo").selectmenu({
		change : function(event, ui) {
			var showId = $('#showListCombo option:selected').val();
			getEventListByShowId(showId);
			getPriceListDetailByShowId(showId);
		}
	});
	$(document).ready(
			function() {
				reloadShowListsForSale();
				$("#showListCombo").selectmenu().selectmenu("menuWidget")
						.addClass("overflow");
				$("#eventListCombo").selectmenu().selectmenu("menuWidget")
						.addClass("overflow");

			});
	$('#saleTicket')
			.on(
					'click',
					function(e) {
						var showId = $('#showListCombo option:selected').val();
						var eventId = $('#eventListCombo option:selected')
								.val();
						var ticketCount = $('#totalTicketCount').val();
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/saleTicketByBackOffice/?showId="
											+ showId
											+ "&eventId="
											+ eventId
											+ "&priceTypeIdList=2|4&ticketCountList=1|1",
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											for (var i = 0; i < data.response.ticketPdfList.length; i++) {
												var sampleBytes = base64ToArrayBuffer(data.response.ticketPdfList[i]);
												//saveByteArray([ sampleBytes ],
												//		"C:\\tickets\\" + i
												//				+ ".pdf");
												<%
												 //File creation
												 String strPath = "C:\\tickets\\example.txt";
												 File strFile = new File(strPath);
												 boolean fileCreated = strFile.createNewFile();
												 //File appending
												 Writer objWriter = new BufferedWriter(new FileWriter(strFile));
												 objWriter.write("This is a test");
												 objWriter.flush();
												 objWriter.close();
												%> 
												
											}
										} else {
											alert(data.errorDesc);
										}
									}
								});

					})

	function base64ToArrayBuffer(base64) {
		var binaryString = window.atob(base64);
		var binaryLen = binaryString.length;
		var bytes = new Uint8Array(binaryLen);
		for (var i = 0; i < binaryLen; i++) {
			var ascii = binaryString.charCodeAt(i);
			bytes[i] = ascii;
		}
		return bytes;
	}

	var saveByteArray = (function() {
		var a = document.createElement("a");
		document.body.appendChild(a);
		a.style = "display: none";
		return function(data, name) {
			var blob = new Blob(data, {
				type : "octet/stream"
			}), url = window.URL.createObjectURL(blob);
			a.href = url;
			a.download = name;
			a.click();
			window.URL.revokeObjectURL(url);
		};
	}());
</script>