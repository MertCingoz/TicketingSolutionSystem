<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Gösteri Yönetimi <small>Gösteri yönetimini bu ekrandan
				yapabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="showForm">
			<div class="col-md-6">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Yeni Gösteri Oluşturma</h3>
					</div>
					<div class="box-body">
						<div class="form-group">
							<div class="col-xs-12">
								<label for="speed">Gösteri türü seçiniz : </label><br> <select
									name="showType" id="showType" style="width: 250px">
									<option value="-">Gösteri türü seçiniz...</option>
								</select> <br> <input type="hidden" id="showId"
									class="form-control my-colorpicker"> <label>Gösteri
									Adı :</label> <input type="text" id="showName" class="form-control"
									placeholder="Gösteri adını giriniz..."> <label>Özet
									Bilgi :</label>
								<textarea id="summary" class="form-control" rows="3"
									placeholder="Özet bilgi giriniz ..."></textarea>
								<label>Gösteri detay bilgisi :</label>
								<textarea id="description" class="form-control" rows="6"
									placeholder="Detay bilgi giriniz ..."></textarea>
							</div>
							<div class="form-group">
								<div class="col-xs-3">
									<label>Gösteri Başlangıç Tarihi :</label> <input type="text"
										class="form-control" id="showStartDate">
								</div>
								<div class="col-xs-2">
									<label>Saati (ss:dd):</label> <input type="text"
										class="form-control" id="showStartTime">
								</div>
								<div class="col-xs-3">
									<label>Gösteri Bitiş Tarihi :</label> <input type="text"
										class="form-control" id="showEndDate">
								</div>
								<div class="col-xs-2">
									<label>Saati (ss:dd) :</label> <input type="text"
										class="form-control" id="showEndTime">
								</div>
								<div class="col-xs-5">
									<label>Etkinlik Süresi (Dakika) :</label> <input type="text"
										id="duration" class="form-control"
										placeholder="Etkinlik Süresini Giriniz...">
								</div>
								<div class="col-xs-5">
									<label>İşlem başı maksimum bilet sayısı :</label> <input
										type="text" id="maximumTicketCount" class="form-control"
										placeholder="Maksimum bilet sayısı...">
								</div>
								<div class="col-xs-5">
									<label>Mekan :</label><br> <select name="venueList"
										id="venueList" style="width: 250px">
										<option value="-">Mekan seçiniz...</option>
									</select>
								</div>
								<div class="col-xs-5">
									<label>Salon :</label><br> <select name="hallList"
										id="hallList" style="width: 250px">
										<option value="-">Salon seçiniz...</option>
									</select>
								</div>
								<div class="col-xs-5">
									<label>Oturma Planı :</label><br> <select
										name="seatingPlanList" id="seatingPlanList"
										style="width: 250px">
										<option value="-">Oturma Planı Seçiniz...</option>
									</select>
								</div>
								<div class="col-xs-5">
									<label>Fiyat Listesi :</label><br> <select
										name="priceList" id="priceList" style="width: 250px">
										<option value="-">Fiyat listesi Seçiniz...</option>
									</select>
								</div>
							</div>
							<div class="col-xs-12">
								<label>Ödeme Yöntemi</label><br> <select
									id="paymentMethods" multiple="multiple">
								</select>
							</div>
							<div class="col-xs-3">
								<label>Satışa Başlama Tarihi :</label> <input type="text"
									class="form-control" id="saleStartDate">
							</div>
							<div class="col-xs-2">
								<label>Saati (ss:dd):</label> <input type="text"
									class="form-control" id="saleStartTime">
							</div>
							<div class="col-xs-5">
								<label>Satışı Sonlandırma Tarihi :</label> <input type="text"
									class="form-control" id="saleEndDate">
							</div>
							<div class="col-xs-2">
								<label>Saati (ss:dd):</label> <input type="text"
									class="form-control" id="saleEndTime">
							</div>
							<div class="col-xs-12">
								<label>Bilet Sayısı </label><br> <input type="text"
									class="form-control" id="ticketCount">
							</div>
						</div>
						<div class="col-xs-12"></div>
					</div>
					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deleteShowButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updateShowButton">Güncelle</button>
						<button type="button" class="btn btn-primary" id="saveShowButton">Kaydet</button>
					</div>

					<!-- /.box-body -->
				</div>
			</div>
			<div class="col-md-6">

				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Gösteri Listesi</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="showListData"
							class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Gösteri Adı</th>
								</tr>
							</thead>
							<tbody id="showList">

							</tbody>
							<tfoot>
								<tr>
									<th>Gösteri Adı</th>
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
	$("#venueList").selectmenu({
		change : function(event, ui) {
			loadHalls();
		}
	});

	$("#hallList").selectmenu({
		change : function(event, ui) {
			loadSeatingPlans();
		}
	});

	$(document)
			.ready(
					function() {
						$("#showStartDate")
								.datepicker(
										{
											dateFormat : "dd-mm-yy",//tarih formatı yy=yıl mm=ay dd=gün
											autoSize : true,//inputu otomatik boyutlandırır
											changeMonth : true,//ayı elle seçmeyi aktif eder
											changeYear : true,//yılı elee seçime izin verir
											dayNames : [ "pazar", "pazartesi",
													"salı", "çarşamba",
													"perşembe", "cuma",
													"cumartesi" ],//günlerin adı
											dayNamesMin : [ "pa", "pzt", "sa",
													"çar", "per", "cum", "cmt" ],//kısaltmalar
											monthNamesShort : [ "Ocak",
													"Şubat", "Mart", "Nisan",
													"Mayıs", "Haziran",
													"Temmuz", "Ağustos",
													"Eylül", "Ekim", "Kasım",
													"Aralık" ],//ay seçim alanın düzenledik
											nextText : "ileri",//ileri butonun türkçeleştirdik
											prevText : "geri"//buda geri butonu için
										});
						$("#showEndDate")
								.datepicker(
										{
											dateFormat : "dd-mm-yy",//tarih formatı yy=yıl mm=ay dd=gün
											autoSize : true,//inputu otomatik boyutlandırır
											changeMonth : true,//ayı elle seçmeyi aktif eder
											changeYear : true,//yılı elee seçime izin verir
											dayNames : [ "pazar", "pazartesi",
													"salı", "çarşamba",
													"perşembe", "cuma",
													"cumartesi" ],//günlerin adı
											dayNamesMin : [ "pa", "pzt", "sa",
													"çar", "per", "cum", "cmt" ],//kısaltmalar
											monthNamesShort : [ "Ocak",
													"Şubat", "Mart", "Nisan",
													"Mayıs", "Haziran",
													"Temmuz", "Ağustos",
													"Eylül", "Ekim", "Kasım",
													"Aralık" ],//ay seçim alanın düzenledik
											nextText : "ileri",//ileri butonun türkçeleştirdik
											prevText : "geri"//buda geri butonu için
										});

						$("#saleStartDate")
								.datepicker(
										{
											dateFormat : "dd-mm-yy",//tarih formatı yy=yıl mm=ay dd=gün
											autoSize : true,//inputu otomatik boyutlandırır
											changeMonth : true,//ayı elle seçmeyi aktif eder
											changeYear : true,//yılı elee seçime izin verir
											dayNames : [ "pazar", "pazartesi",
													"salı", "çarşamba",
													"perşembe", "cuma",
													"cumartesi" ],//günlerin adı
											dayNamesMin : [ "pa", "pzt", "sa",
													"çar", "per", "cum", "cmt" ],//kısaltmalar
											monthNamesShort : [ "Ocak",
													"Şubat", "Mart", "Nisan",
													"Mayıs", "Haziran",
													"Temmuz", "Ağustos",
													"Eylül", "Ekim", "Kasım",
													"Aralık" ],//ay seçim alanın düzenledik
											nextText : "ileri",//ileri butonun türkçeleştirdik
											prevText : "geri"//buda geri butonu için
										});
						$("#saleEndDate")
								.datepicker(
										{
											dateFormat : "dd-mm-yy",//tarih formatı yy=yıl mm=ay dd=gün
											autoSize : true,//inputu otomatik boyutlandırır
											changeMonth : true,//ayı elle seçmeyi aktif eder
											changeYear : true,//yılı elee seçime izin verir
											dayNames : [ "pazar", "pazartesi",
													"salı", "çarşamba",
													"perşembe", "cuma",
													"cumartesi" ],//günlerin adı
											dayNamesMin : [ "pa", "pzt", "sa",
													"çar", "per", "cum", "cmt" ],//kısaltmalar
											monthNamesShort : [ "Ocak",
													"Şubat", "Mart", "Nisan",
													"Mayıs", "Haziran",
													"Temmuz", "Ağustos",
													"Eylül", "Ekim", "Kasım",
													"Aralık" ],//ay seçim alanın düzenledik
											nextText : "ileri",//ileri butonun türkçeleştirdik
											prevText : "geri"//buda geri butonu için
										});

						$("#showType").selectmenu().selectmenu("menuWidget")
								.addClass("overflow");
						$("#venueList").selectmenu().selectmenu("menuWidget")
								.addClass("overflow");
						$("#hallList").selectmenu().selectmenu("menuWidget")
								.addClass("overflow");
						$("#seatingPlanList").selectmenu().selectmenu(
								"menuWidget").addClass("overflow");
						$("#priceList").selectmenu().selectmenu("menuWidget")
								.addClass("overflow");
						$('#paymentMethods').multiselect();
						reloadShowLists();
						loadShowTypes();
						loadVenues();
						loadPriceLists();
						loadPaymentMethods();
						$('#updateShowButton').hide();
						$('#cancelUpdateButton').hide();
					});
	$('#saveShowButton')
			.on(
					'click',
					function(e) {
						var showType = $('#showType option:selected').val();
						var showName = $('#showName').val();
						var summary = $('#summary').val();
						var description = $('#description').val();
						var showStartDate = $('#showStartDate').val();
						var showStartTime = $('#showStartTime').val();
						var showEndDate = $('#showEndDate').val();
						var showEndTime = $('#showEndTime').val();
						var duration = $('#duration').val();
						var maximumTicketCount = $('#maximumTicketCount').val();
						var venueId = $('#venueList option:selected').val();
						var hallId = $('#hallList option:selected').val();
						var seatingPlanId = "0";
						if ($('#seatingPlanList option:selected').val() != "-") {
							seatingPlanId = $(
									'#seatingPlanList option:selected').val();
						}

						var priceList = $('#priceList option:selected').val();
						var saleStartDate = $('#saleStartDate').val();
						var saleStartTime = $('#saleStartTime').val();
						var saleEndDate = $('#saleEndDate').val();
						var saleEndTime = $('#saleEndTime').val();
						var paymentMethods = $('#paymentMethods').val();
						var ticketCount = $('#ticketCount').val();
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/createShow/?showTypeId="
											+ showType
											+ "&name="
											+ showName
											+ "&summary="
											+ summary
											+ "&description="
											+ description
											+ "&startDate="
											+ showStartDate
											+ " "
											+ showStartTime
											+ "&endDate="
											+ showEndDate
											+ " "
											+ showEndTime
											+ "&duration="
											+ duration
											+ "&venueId="
											+ venueId
											+ "&hallId="
											+ hallId
											+ "&priceListId="
											+ priceList
											+ "&maxTicketSaleCount="
											+ maximumTicketCount
											+ "&saleStartDate="
											+ saleStartDate
											+ " "
											+ saleStartTime
											+ "&saleEndDate="
											+ saleEndDate
											+ " "
											+ saleEndTime
											+ "&paymentMethods="
											+ paymentMethods
											+ "&seatingPlanId="
											+ seatingPlanId
											+ "&ticketCount=" + ticketCount,
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
											alert("Gösteri başarılı olarak yaratılmıştır");
											clearForm();
											reloadShowLists();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})

	$('#updateShowButton')
			.on(
					'click',
					function(e) {
						var id = $('#showId').val();
						var showType = $('#showType option:selected').val();
						var showName = $('#showName').val();
						var summary = $('#summary').val();
						var description = $('#description').val();
						var showStartDate = $('#showStartDate').val();
						var showStartTime = $('#showStartTime').val();
						var showEndDate = $('#showEndDate').val();
						var showEndTime = $('#showEndTime').val();
						var duration = $('#duration').val();
						var maximumTicketCount = $('#maximumTicketCount').val();
						var venueId = $('#venueList option:selected').val();
						var hallId = $('#hallList option:selected').val();
						var seatingPlanId = $(
								'#seatingPlanList option:selected').val();
						var priceList = $('#priceList option:selected').val();
						var saleStartDate = $('#saleStartDate').val();
						var saleStartTime = $('#saleStartTime').val();
						var saleEndDate = $('#saleEndDate').val();
						var saleEndTime = $('#saleEndTime').val();
						var paymentMethods = $('#paymentMethods').val();
						var ticketCount = $('#ticketCount').val();
						$.ajax({
							type : 'GET',
							url : "/TicketingSolutionRest/updateShow/?showId="
									+ id + "&showTypeId=" + showType + "&name="
									+ showName + "&summary=" + summary
									+ "&description=" + description
									+ "&startDate=" + showStartDate + " "
									+ showStartTime + "&endDate=" + showEndDate
									+ " " + showEndTime + "&duration="
									+ duration + "&venueId=" + venueId
									+ "&hallId=" + hallId + "&priceListId="
									+ priceList + "&maxTicketSaleCount="
									+ maximumTicketCount + "&saleStartDate="
									+ saleStartDate + " " + saleStartTime
									+ "&saleEndDate=" + saleEndDate + " "
									+ saleEndTime + "&paymentMethods="
									+ paymentMethods + "&seatingPlanId="
									+ seatingPlanId + "&ticketCount="
									+ ticketCount,
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
									alert("Gösteri başarılı güncellenmiştir.");
									clearForm();
									reloadShowLists();
								} else {
									alert(data.errorDesc);
								}
							}
						});
					})
	$('#cancelUpdateButton').on('click', function(e) {
		clearForm();
		$('#updateShowButton').hide();
		$('#saveShowButton').show();
		$('#cancelUpdateButton').hide();
	})
</script>