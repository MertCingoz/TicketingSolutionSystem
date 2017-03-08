<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Mekan Yönetimi <small>Mekan yönetimini bu ekrandan
				yapabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="venueForm">
			<div class="col-md-6">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Yeni Mekan Oluşturma</h3>
					</div>
					<div class="box-body">
						<div class="form-group">
							<input type="hidden" id="venueId"
								class="form-control my-colorpicker"> <label for="speed">Şehir
								seçiniz : </label><br> <select name="city" id="city"
								style="width: 250px">
								<option value="-">Şehir seçiniz...</option>
							</select> <br> <label>Mekan Adı :</label> <input type="text"
								id="venueName" class="form-control"
								placeholder="Mekan adını giriniz..."> <label>Adres
								:</label> <input type="text" id="address" class="form-control"
								placeholder="Adres giriniz..."> <label>Posta
								Kodu :</label> <input type="text" id="postalCode" class="form-control"
								placeholder="Posta Kodu Giriniz..."> <label>Telefon
								:</label> <input type="text" id="telephone" class="form-control"
								placeholder="Telefon Giriniz..."> <label>E-Mail
								:</label> <input type="text" id="email" class="form-control"
								placeholder="Email Giriniz..."> <label>Web
								Sitesi :</label> <input type="text" id="website" class="form-control"
								placeholder="Web sitesi...">
						</div>

						<div class="box-footer">
							<button type="button" style="display: none;"
								class="btn btn-primary" id="deleteVenueButton">Sil</button>
							<button type="button" class="btn btn-primary"
								id="showDetailModalButton">Salonlar</button>
							<button type="button" class="btn btn-primary"
								id="cancelUpdateButton">Vazgeç</button>
							<button type="button" class="btn btn-primary"
								id="updateVenueButton">Güncelle</button>
							<button type="button" class="btn btn-primary"
								id="saveVenueButton">Kaydet</button>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
			</div>
			<div class="col-md-6">

				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Mekan Listesi</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="venueListData"
							class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Mekan Adı</th>
								</tr>
							</thead>
							<tbody id="venueList">

							</tbody>
							<tfoot>
								<tr>
									<th>Mekan Adı</th>
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
<div id="venueHallList" title="Salon Detayı"></div>
<script>
	function getVenueById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getVenueById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#venueId').val(data.response.id);
					$('#venueName').val(data.response.name);
					$('#address').val(data.response.address);
					$('#postalCode').val(data.response.postalCode);
					$('#telephone').val(data.response.phone);
					$('#email').val(data.response.email);
					$('#website').val(data.response.venueUrl);
					$('#city').val(data.response.cityId);
					$('#city').selectmenu('refresh');
					$('#updateVenueButton').show();
					$('#saveVenueButton').hide();
					$('#cancelUpdateButton').show();
					$('#showDetailModalButton').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name) {
		var rowString = "";
		rowString += "<tr onclick=getVenueById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function loadCities() {
		var datas = "";
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getAllCities/",
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					datas += "<option value=->Şehir Seçiniz</option>";
					for (var i = 0; i < data.response.cityList.length; i++) {
						datas += "<option value="+data.response.cityList[i].id+">"
								+ data.response.cityList[i].name + "</option>";
					}

					$('#city').html(datas);
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function reloadVenueLists() {
		var datas = "";
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllVenues/",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							for (var i = 0; i < data.response.venueList.length; i++) {
								datas += getOneRow(data.response.venueList[i].id,
										data.response.venueList[i].status,
										data.response.venueList[i].name);
							}
							$('#venueList').html(datas);
							$("#venueListData").DataTable();
							$("#venueListData").DataTable().init();
						} else {
							alert(data.errorDesc);
						}
					}
				});
	}

	$(document).ready(function() {
		$("#city").selectmenu().selectmenu("menuWidget").addClass("overflow");
		reloadVenueLists();
		loadCities();
		$('#updateVenueButton').hide();
		$('#cancelUpdateButton').hide();
		$('#showDetailModalButton').hide();
	});
	$('#saveVenueButton').on(
			'click',
			function(e) {
				var city = $('#city option:selected').val();
				var venueName = $('#venueName').val();
				var address = $('#address').val();
				var postalCode = $('#postalCode').val();
				var telephone = $('#telephone').val();
				var email = $('#email').val();
				var website = $('#website').val();
				if (city == "" || venueName == "" || address == ""
						|| postalCode == "" || telephone == "" || email == ""
						|| website == "") {
					alert("Lütfen tüm alanları doldurunuz!");
					return false;
				}
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/createVenue/?name="
							+ venueName + "&countryId=1&stateId=1&cityId="
							+ city + "&address=" + address + "&postalCode="
							+ postalCode + "&phone=" + telephone
							+ "&fax=&email=" + email + "&venueUrl=" + website
							+ "&latitude=0&longitude=0",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							clearForm();
							reloadVenueLists();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	function clearForm() {
		$('#venueId').val("");
		$('#venueName').val("");
		$('#address').val("");
		$('#postalCode').val("");
		$('#telephone').val("");
		$('#email').val("");
		$('#website').val("");
		$('#city').val("-");
		$('#city').selectmenu('refresh');
	}
	$('#updateVenueButton').on(
			'click',
			function(e) {
				var name = $('#venueName').val();
				var id = $('#venueId').val();
				var venueName = $('#venueName').val();
				var address = $('#address').val();
				var postalCode = $('#postalCode').val();
				var telephone = $('#telephone').val();
				var email = $('#email').val();
				var website = $('#website').val();
				var city = $('#city option:selected').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateVenue/?id="
							+ id + "&name=" + venueName
							+ "&countryId=1&stateId=1&cityId=" + city
							+ "&address=" + address + "&postalCode="
							+ postalCode + "&phone=" + telephone
							+ "&fax=0&email=" + email + "&venueUrl=" + website
							+ "&latitude=0&longitude=0",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							clearForm();
							$('#updateVenueButton').hide();
							$('#saveVenueButton').show();
							$('#cancelUpdateButton').hide();
							$('#showDetailModalButton').hide();
							reloadVenueLists();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		clearForm();
		$('#updateVenueButton').hide();
		$('#saveVenueButton').show();
		$('#cancelUpdateButton').hide();
		$('#showDetailModalButton').hide();
	})

	$('#showDetailModalButton').on('click', function(e) {
		var venueId = $('#venueId').val();
		window.location.href = "HallList.jsp?id=" + venueId;
		/*
		$("#venueHallList").load("HallList.jsp?id=" + venueId).dialog({
			height : 450,
			width : 750,
			modal : true,
			buttons : {
				Kapat : function() {
					$("#venueHallList").dialog("close");
				}
			},
			close : function() {
			}
		});
		$("#venueHallList").css("overflow", "hidden");
		*/
	})
</script>