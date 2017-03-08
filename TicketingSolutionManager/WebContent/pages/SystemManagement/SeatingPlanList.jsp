<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section class="content">
	<form id="seatingPlanForm">
		<input type="hidden" value="<%=request.getParameter("hallId")%>"
			id="hallId"><input type="hidden" id="seatingPlanId">
		<label>Oturma Düzeni Adı :</label> <input type="text"
			id="seatingPlanName" class="form-control"
			placeholder="Oturma düzeni adını giriniz...">

		<div class="box-footer">
			<button type="button" style="display: none;" class="btn btn-primary"
				id="deleteSeatingPlanButton">Sil</button>
			<button type="button" class="btn btn-primary"
				id="cancelUpdateButtonSeatingPlan">Vazgeç</button>
			<button type="button" class="btn btn-primary"
				id="updateSeatingPlanButton">Güncelle</button>
			<button type="button" class="btn btn-primary"
				id="saveSeatingPlanButton">Kaydet</button>
			<button type="button" class="btn btn-primary" id="openDrawingButton">Çizimi Düzenle</button>
		</div>
		<!-- /.box-body -->

	</form>
	<!-- /.box -->
	<div class="row">
		<div class="col-xs-12">

			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Oturma Düzenleri</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="seatingPlanTable"
						class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Oturma Düzeni Adı</th>
							</tr>
						</thead>
						<tbody id="SeatingPlanList">

						</tbody>
						<tfoot>
							<tr>
								<th>Oturma Düzeni Adı</th>
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
	<!-- /.row --> </section>
</body>
</html>
<script>
	function getSeatingPlanById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getSeatingPlanById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#seatingPlanId').val(data.response.id);
					$('#seatingPlanName').val(data.response.name);
					$('#updateSeatingPlanButton').show();
					$('#saveSeatingPlanButton').hide();
					$('#cancelUpdateButtonSeatingPlan').show();
					$('#openDrawingButton').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name) {
		var rowString = "";
		rowString += "<tr onclick=getSeatingPlanById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadSeatingPlans() {
		var datas = "";
		var hallId = $('#hallId').val();
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllSeatingPlansByHallId/?hallId="
							+ hallId,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							for (var i = 0; i < data.response.seatingPlanList.length; i++) {
								datas += getOneRow(
										data.response.seatingPlanList[i].id,
										data.response.seatingPlanList[i].status,
										data.response.seatingPlanList[i].name);
							}
							$('#SeatingPlanList').html(datas);
							$("#seatingPlanTable").DataTable();
							$("#seatingPlanTable").DataTable().init();
						} else {
							alert(data.errorDesc);
						}
					}
				});
	}

	$(document).ready(function() {
		reloadSeatingPlans();
		$('#updateSeatingPlanButton').hide();
		$('#cancelUpdateButtonSeatingPlan').hide();
		$('#openDrawingButton').hide();
	});
	$('#saveSeatingPlanButton')
			.on(
					'click',
					function(e) {
						var seatingPlanName = $('#seatingPlanName').val();
						var hallId = $('#hallId').val()
						if (seatingPlanName == "") {
							alert("Lütfen tüm alanları doldurunuz!");
							return false;
						}
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/createSeatingPlan/?name="
											+ seatingPlanName
											+ "&background=&hallId=" + hallId,
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											$('#seatingPlanName').val("");
											reloadSeatingPlans();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})

	$('#updateSeatingPlanButton').on(
			'click',
			function(e) {
				var name = $('#seatingPlanName').val();
				var id = $('#seatingPlanId').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateSeatingPlan/?id=" + id
							+ "&name=" + name + "&background=",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#seatingPlanName').val("");
							$('#seatingPlanId').val("");
							$('#updateSeatingPlanButton').hide();
							$('#saveSeatingPlanButton').show();
							$('#cancelUpdateButtonSeatingPlan').hide();
							$('#openDrawingButton').hide();
							reloadSeatingPlans();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})

	$('#openDrawingButton').on('click', function(e) {
		var seatingPlanId = $('#seatingPlanId').val();
		window.open("/Drawer/?seatingPlanId=" + seatingPlanId);
	})
	$('#cancelUpdateButtonSeatingPlan').on('click', function(e) {
		$('#seatingPlanName').val("");
		$('#seatingPlanId').val("");
		$('#updateSeatingPlanButton').hide();
		$('#saveSeatingPlanButton').show();
		$('#cancelUpdateButtonSeatingPlan').hide();
	})
</script>