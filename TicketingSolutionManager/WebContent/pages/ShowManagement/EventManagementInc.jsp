<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Etkinlik Yönetimi <small>Etkinlik yönetimini bu ekrandan
				yapabilirsiniz.</small>
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
						<h3 class="box-title">Yeni Etkinlik Oluşturma</h3>
					</div>
					<div class="box-body">
						<div class="form-group">
							<div class="col-xs-12">
								<input type="hidden" id="eventId"> <label for="speed">Gösteri
									seçiniz : </label><br> <select name="showListCombo"
									id="showListCombo" style="width: 250px">
									<option value="-">Gösteri seçiniz...</option>
								</select> <br> <label>Gösteri Türü :</label> <input type="text"
									id="showType" class="form-control" disabled placeholder="">
							</div>
							<div class="form-group">
								<div class="col-xs-3">
									<label>Tarihi :</label> <input type="text" class="form-control"
										id="eventStartDate">
								</div>
								<div class="col-xs-2">
									<label>Saat :</label> <input type="text" class="form-control"
										id="eventStartTime">
								</div>
								<div class="col-xs-5">
									<label>Süresi :</label> <input type="text" id="duration"
										class="form-control" placeholder="">
								</div>
								<div class="col-xs-5">
									<label>Mekan :</label><br> <input type="text" id="venue"
										class="form-control" disabled placeholder="">
								</div>
								<div class="col-xs-5">
									<label>Salon :</label><br> <input type="text" id="hall"
										class="form-control" disabled placeholder="">
								</div>
								<div class="col-xs-12">
									<label for="speed">Koltuk atama : </label><br> <select
										name="selectSeat" id="selectSeat" style="width: 250px">
										<option value="-">Seçiniz</option>
										<option value="1">Otomatik atansın</option>
										<option value="0">Müşteri seçsin</option>
									</select>
								</div>
								<div class="col-xs-12">
									<label for="speed">Satış durumu : </label><br> <select
										name="saleStatus" id="saleStatus" style="width: 250px">
										<option value="-">Seçiniz</option>
										<option value="1">Satışa açık</option>
										<option value="0">Satışa kapalı</option>
									</select>
								</div>
								<div class="col-xs-12">
									<label for="speed">Bilet Tasarımı : </label><br> <select
										name="ticketDesign" id="ticketDesign" style="width: 250px">
										<option value="-">Seçiniz</option>
									</select>
								</div>
							</div>

						</div>
						<div class="col-xs-12"></div>
					</div>
					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deleteEventButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updateEventButton">Güncelle</button>
						<button type="button" class="btn btn-primary" id="saveEventButton">Kaydet</button>
					</div>

					<!-- /.box-body -->
				</div>
			</div>
			<div class="col-md-6">

				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Etkinlik Listesi</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="eventListData"
							class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Etkinlik Tarihi</th>
								</tr>
							</thead>
							<tbody id="eventList">

							</tbody>
							<tfoot>
								<tr>
									<th>Etkinlik Tarihi</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
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
			onSelectShow(showId);
		}
	});
	$(document).ready(
			function() {
				$('#saveEventButton').hide();

				$("#showListCombo").selectmenu().selectmenu("menuWidget")
						.addClass("overflow");
				$("#selectSeat").selectmenu().selectmenu("menuWidget")
						.addClass("overflow");
				$("#saleStatus").selectmenu().selectmenu("menuWidget")
						.addClass("overflow");
				$("#ticketDesign").selectmenu().selectmenu("menuWidget")
						.addClass("overflow");
				//reloadEventLists();
				reloadShowListsForEvent();
				reloadTicketDesigns();
				$('#updateEventButton').hide();
				$('#cancelUpdateButton').hide();
			});
	$('#saveEventButton').on(
			'click',
			function(e) {
				var showId = $('#showListCombo option:selected').val();
				var selectSeat = $('#selectSeat option:selected').val();
				var saleStatus = $('#saleStatus option:selected').val();
				var ticketDesign = $('#ticketDesign option:selected').val();
				var eventDate = $('#eventStartDate').val();
				var eventTime = $('#eventStartTime').val();
				var duration = $('#duration').val();
				var createUser = "1";
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/createEvent/?showId="
							+ showId + "&eventDate=" + eventDate + " "
							+ eventTime + "&duration=" + duration
							+ "&selectSeat=" + selectSeat + "&saleStatus="
							+ saleStatus + "&ticketDesignId=" + ticketDesign
							+ "&createUser=" + createUser,
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
							alert("Etkinlik başarılı olarak yaratılmıştır");
							clearEventForm(true);
							reloadEventLists();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#updateEventButton').on(
			'click',
			function(e) {
				var eventId = $('#eventId').val();
				var selectSeat = $('#selectSeat option:selected').val();
				var saleStatus = $('#saleStatus option:selected').val();
				var ticketDesign = $('#ticketDesign option:selected').val();
				var eventDate = $('#eventStartDate').val();
				var eventTime = $('#eventStartTime').val();
				var duration = $('#duration').val();
				var createUser = "1";
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateEvent/?id=" + eventId
							+ "&eventDate=" + eventDate + " " + eventTime
							+ "&duration=" + duration + "&selectSeat="
							+ selectSeat + "&saleStatus=" + saleStatus
							+ "&ticketDesignId=" + ticketDesign,
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
							clearEventForm(true);
							alert("Etkinlik başarılı olarak güncellenmiştir");
							//reloadEventLists();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		clearEventForm(true);
		$('#updateEventButton').hide();
		$('#saveEventButton').show();
		$('#cancelUpdateButton').hide();
	})
</script>