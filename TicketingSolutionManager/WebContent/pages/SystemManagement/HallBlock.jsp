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
	<form id="hallBlockForm">
		<input type="hidden" value="<%=request.getParameter("hallId")%>"
			id="hallId"><input type="hidden"
			value="<%=request.getParameter("venueId")%>" id="venueId"><input
			type="hidden" id="hallBlockId"> <label>Blok Adı :</label> <input
			type="text" id="hallBlockName" class="form-control"
			placeholder="Blok adını giriniz...">

		<div class="box-footer">
			<button type="button" style="display: none;" class="btn btn-primary"
				id="deleteHallBlockButton">Sil</button>
			<button type="button" class="btn btn-primary"
				id="cancelUpdateButtonBlock">Vazgeç</button>
			<button type="button" class="btn btn-primary"
				id="updateHallBlockButton">Güncelle</button>
			<button type="button" class="btn btn-primary"
				id="saveHallBlockButton">Kaydet</button>
		</div>
		<!-- /.box-body -->

	</form>
	<!-- /.box -->
	<div class="row">
		<div class="col-xs-12">

			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Bloklar</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="hallBlockTable"
						class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Blok Adı</th>
							</tr>
						</thead>
						<tbody id="HallBlockList">

						</tbody>
						<tfoot>
							<tr>
								<th>Blok Adı</th>
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
	function getHallBlockById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getHallBlockById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#hallBlockId').val(data.response.id);
					$('#hallBlockName').val(data.response.name);
					$('#updateHallBlockButton').show();
					$('#saveHallBlockButton').hide();
					$('#cancelUpdateButtonBlock').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name) {
		var rowString = "";
		rowString += "<tr onclick=getHallBlockById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadHallBlocks() {
		var datas = "";
		var hallId = $('#hallId').val();
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllHallBlocksByHallId/?hallId="
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
							for (var i = 0; i < data.response.hallBlockList.length; i++) {
								datas += getOneRow(
										data.response.hallBlockList[i].id,
										data.response.hallBlockList[i].status,
										data.response.hallBlockList[i].name);
							}
							$('#HallBlockList').html(datas);
							$("#hallBlockTable").DataTable();
							$("#hallBlockTable").DataTable().init();
						} else {
							alert(data.errorDesc);
						}
					}
				});
	}

	$(document).ready(function() {
		reloadHallBlocks();
		$('#updateHallBlockButton').hide();
		$('#cancelUpdateButtonBlock').hide();
	});
	$('#saveHallBlockButton').on(
			'click',
			function(e) {
				var hallBlockName = $('#hallBlockName').val();
				var venueId = $('#venueId').val();
				var hallId = $('#hallId').val()
				if (hallBlockName == "") {
					alert("Lütfen tüm alanları doldurunuz!");
					return false;
				}
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/createHallBlock/?name="
							+ hallBlockName + "&venueId=" + venueId
							+ "&hallId=" + hallId,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#hallBlockName').val("");
							reloadHallBlocks();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})

	$('#updateHallBlockButton').on(
			'click',
			function(e) {
				var name = $('#hallBlockName').val();
				var id = $('#hallBlockId').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateHallBlock/?id=" + id
							+ "&name=" + name,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#hallBlockName').val("");
							$('#hallBlockId').val("");
							$('#updateHallBlockButton').hide();
							$('#saveHallBlockButton').show();
							$('#cancelUpdateButtonBlock').hide();
							reloadHallBlocks();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButtonBlock').on('click', function(e) {
		$('#hallBlockName').val("");
		$('#hallBlockId').val("");
		$('#updateHallBlockButton').hide();
		$('#saveHallBlockButton').show();
		$('#cancelUpdateButtonBlock').hide();
	})
</script>