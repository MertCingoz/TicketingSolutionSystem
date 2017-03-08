<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Salonlar<small>Sistem üzerinde kullanılacak olan salonları
				oluşturabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li> > Mekan Yönetimi
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="priceListForm">
			<div class="box box-info" style="width: 400px">
				<div class="box-header">
					<h3 class="box-title">Salon Oluşturma</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<input type="hidden" value="<%=request.getParameter("id")%>"
							id="venueId"> <input type="hidden" id="hallId"
							class="form-control my-colorpicker"> <label>Salon
							Adı :</label> <input type="text" id="hallName" class="form-control"
							placeholder="Salon adını giriniz...">
						<div class="checkbox">
							<label> <input type="checkbox" id="allowOverlapEvents">
								Aynı anda birden fazla etkinlik yapılabilir
							</label>
						</div>
					</div>

					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deleteHallButton">Sil</button>
						<button type="button" class="btn btn-primary" id="seatingPlan">Oturma
							Planı</button>
						<button type="button" class="btn btn-primary"
							id="showDetailModalButton">Bloklar</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updateHallButton">Güncelle</button>
						<button type="button" class="btn btn-primary" id="saveHallButton">Kaydet</button>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
		</form>
		<!-- /.box -->
		<div class="row">
			<div class="col-xs-12">

				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Salonlar</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Salon Adı</th>
								</tr>
							</thead>
							<tbody id="HallList">

							</tbody>
							<tfoot>
								<tr>
									<th>Salon Adı</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<div id="hallDetailModal" title="Bloklar"></div>
<div id="seatingPlanModal" title="Oturma Planları"></div>

<script>
	function getHallById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getHallById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#hallId').val(data.response.id);
					$('#hallName').val(data.response.name);
					if (data.response.allowOverlapEvents == "1") {
						$('#allowOverlapEvents').prop('checked', true);
					} else {
						$('#allowOverlapEvents').prop('checked', false);
					}
					$('#updateHallButton').show();
					$('#saveHallButton').hide();
					$('#cancelUpdateButton').show();
					$('#showDetailModalButton').show();
					$('#seatingPlan').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name) {
		var rowString = "";
		rowString += "<tr onclick=getHallById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadHalls() {
		var datas = "";
		var venueId = $('#venueId').val();
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getAllHallsByVenueId/?venueId="
					+ venueId,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					for (var i = 0; i < data.response.hallList.length; i++) {
						datas += getOneRow(data.response.hallList[i].id,
								data.response.hallList[i].status,
								data.response.hallList[i].name);
					}
					$('#HallList').html(datas);
					$("#example1").DataTable();
					$("#example1").DataTable().init();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	$(document).ready(function() {
		reloadHalls();
		$('#updateHallButton').hide();
		$('#cancelUpdateButton').hide();
		$('#showDetailModalButton').hide();
		$('#seatingPlan').hide();

	});
	$('#saveHallButton').on(
			'click',
			function(e) {
				var hallName = $('#hallName').val();
				var venueId = $('#venueId').val();
				var allowOverlapEvents = "0";
				if ($('#allowOverlapEvents').prop('checked')) {
					allowOverlapEvents = "1";
				}
				if (hallName == "") {
					alert("Lütfen tüm alanları doldurunuz!");
					return false;
				}
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/createHall/?name=" + hallName
							+ "&venueId=" + venueId + "&allowOverlapEvents="
							+ allowOverlapEvents,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#hallName').val("");
							$('#allowOverlapEvents').prop('checked', false);
							reloadHalls();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})

	$('#updateHallButton').on(
			'click',
			function(e) {
				var name = $('#hallName').val();
				var id = $('#hallId').val();
				var allowOverlapEvents = "0";
				if ($('#allowOverlapEvents').prop('checked')) {
					allowOverlapEvents = "1";
				}
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateHall/?id=" + id
							+ "&name=" + name + "&allowOverlapEvents="
							+ allowOverlapEvents,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#hallName').val("");
							$('#hallId').val("");
							$('#allowOverlapEvents').prop('checked', false);
							$('#updateHallButton').hide();
							$('#saveHallButton').show();
							$('#cancelUpdateButton').hide();
							$('#showDetailModalButton').hide();
							$('#seatingPlan').hide();
							reloadHalls();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		$('#hallName').val("");
		$('#hallId').val("");
		$('#allowOverlapEvents').prop('checked', false);
		$('#updateHallButton').hide();
		$('#saveHallButton').show();
		$('#cancelUpdateButton').hide();
		$('#showDetailModalButton').hide();
		$('#seatingPlan').hide();
	})

	//$('#seatingPlan').on('click', function(e) {
	//	var id = $('#hallId').val();
	//	window.open("/Drawer/?hallId=" + id);
	//})

	$('#showDetailModalButton').on(
			'click',
			function(e) {
				var hallId = $('#hallId').val();
				var venueId = $('#venueId').val();
				$("#hallDetailModal").load(
						"HallBlock.jsp?hallId=" + hallId + "&venueId="
								+ venueId).dialog({
					height : 450,
					width : 750,
					modal : true,
					buttons : {
						Kapat : function() {
							$("#hallDetailModal").dialog("close");
						}
					},
					close : function() {
					}
				});
				//$("#hallDetailModal").css("overflow", "hidden");
			})

	$('#seatingPlan').on(
			'click',
			function(e) {
				var hallId = $('#hallId').val();
				$("#seatingPlanModal").load(
						"SeatingPlanList.jsp?hallId=" + hallId).dialog({
					height : 450,
					width : 750,
					modal : true,
					buttons : {
						Kapat : function() {
							$("#seatingPlanModal").dialog("close");
						}
					},
					close : function() {
					}
				});
				//$("#hallDetailModal").css("overflow", "hidden");
			})		
			
	function savePriceListDetail() {
		alert("save price list detail");
	}
</script>