<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Bilet Tasarımı <small>Sistem üzerinde kullanılacak olan bilet
				tasarımlarını oluşturabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="TicketDesignForm">
			<div class="box box-info" style="width: 400px">
				<div class="box-header">
					<h3 class="box-title">Yeni Bilet Tasarımı Oluşturma</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<input type="hidden" id="ticketDesignId"
							class="form-control my-colorpicker"> <label>Bilet
							Tasarım Adı :</label> <input type="text" id="ticketDesignName"
							class="form-control" placeholder="Bilet tasarım adını giriniz...">
					</div>
					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deleteTicketDesignButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updateTicketDesignButton">Güncelle</button>
						<button type="button" class="btn btn-primary"
							id="openTicketDesignButton">Tasarımı Aç</button>
						<button type="button" class="btn btn-primary"
							id="saveTicketDesignButton">Kaydet</button>
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
						<h3 class="box-title">Bilet Tasarımları</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Bilet Tasarımı</th>
								</tr>
							</thead>
							<tbody id="ticketDesignList">

							</tbody>
							<tfoot>
								<tr>
									<th>Bilet Tasarımı</th>
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
<script>
	function getTicketDesignById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getTicketDesignById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#ticketDesignId').val(data.response.id);
					$('#ticketDesignName').val(data.response.name);
					$('#updateTicketDesignButton').show();
					$('#openTicketDesignButton').show();
					$('#saveTicketDesignButton').hide();
					$('#cancelUpdateButton').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name) {
		var rowString = "";
		rowString += "<tr onclick=getTicketDesignById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadTicketDesigns() {
		var datas = "";
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllTicketDesigns/",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							for (var i = 0; i < data.response.ticketDesignList.length; i++) {
								datas += getOneRow(
										data.response.ticketDesignList[i].id,
										data.response.ticketDesignList[i].status,
										data.response.ticketDesignList[i].name);
							}
							$('#ticketDesignList').html(datas);
							$("#example1").DataTable();
							$("#example1").DataTable().init();
						} else {
							alert(data.errorDesc);
						}
					}
				});
	}

	$(document).ready(function() {
		reloadTicketDesigns();
		$('#updateTicketDesignButton').hide();
		$('#openTicketDesignButton').hide();
		$('#cancelUpdateButton').hide();

	});
	$('#saveTicketDesignButton')
			.on(
					'click',
					function(e) {
						var ticketDesignName = $('#ticketDesignName').val();
						if (ticketDesignName == "") {
							alert("Lütfen tüm alanları doldurunuz!");
							return false;
						}
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/createTicketDesign/?backgroundImage=&name="
											+ ticketDesignName,
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											$('#ticketDesignName').val("");
											reloadTicketDesigns();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})

	$('#updateTicketDesignButton').on(
			'click',
			function(e) {
				var name = $('#ticketDesignName').val();
				var id = $('#ticketDesignId').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateTicketDesign/?id=" + id
							+ "&backgroundImage=&name=" + name,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#ticketDesignName').val("");
							$('#ticketDesignId').val("");
							$('#updateTicketDesignButton').hide();
							$('#openTicketDesignButton').hide();
							$('#saveTicketDesignButton').show();
							$('#cancelUpdateButton').hide();
							reloadTicketDesigns();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		$('#showType').val("");
		$('#showTypeId').val("");
		$('#updateTicketDesignButton').hide();
		$('#openTicketDesignButton').hide();
		$('#saveTicketDesignButton').show();
		$('#cancelUpdateButton').hide();
	})
	$('#openTicketDesignButton').on('click', function(e) {
		var ticketDesignId = $('#ticketDesignId').val();
		window.open("/BWTTicketDesigner/?ticketDesignId=" + ticketDesignId);
	})
</script>