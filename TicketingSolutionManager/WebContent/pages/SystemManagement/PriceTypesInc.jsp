<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Fiyat Tipleri <small>Sistem  üzerinde kullanılacak olan fiyat
				tiplerini oluşturabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="priceTypeForm">
			<div class="box box-info" style="width: 400px">
				<div class="box-header">
					<h3 class="box-title">Yeni Fiyat Tipi Oluşturma</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<input type="hidden" id="priceTypeId"
							class="form-control my-colorpicker"> <label>Fiyat
							Tipi :</label> <input type="text" id="priceType" class="form-control"
							placeholder="Fiyat tipini giriniz...">
					</div>
					<div class="form-group">
						<label>Kısa Adı :</label> <input type="text" id="reportName"
							class="form-control"
							placeholder="Raporda gözükecek adı giriniz...">
					</div>
					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deletePriceTypeButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updatePriceTypeButton">Güncelle</button>
						<button type="button" class="btn btn-primary"
							id="savePriceTypeButton">Kaydet</button>
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
						<h3 class="box-title">Fiyat Tipleri</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Fiyat Tipi</th>
									<th>Rapor Adı</th>
								</tr>
							</thead>
							<tbody id="priceTypeList">

							</tbody>
							<tfoot>
								<tr>
									<th>Fiyat Tipi</th>
									<th>Rapor Adı</th>
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
	function getPriceTypeById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getPriceTypeById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#priceTypeId').val(data.response.id);
					$('#priceType').val(data.response.name);
					$('#reportName').val(data.response.reportName);
					$('#updatePriceTypeButton').show();
					$('#savePriceTypeButton').hide();
					$('#cancelUpdateButton').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name, reportName) {
		var rowString = "";
		rowString += "<tr onclick=getPriceTypeById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "<td>" + reportName + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadPriceTypes() {
		var datas = "";
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getAllPriceTypes/",
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					for (var i = 0; i < data.response.priceTypeList.length; i++) {
						datas += getOneRow(data.response.priceTypeList[i].id,
								data.response.priceTypeList[i].status, data.response.priceTypeList[i].name,
								data.response.priceTypeList[i].reportName);
					}
					$('#priceTypeList').html(datas);
					$("#example1").DataTable();
					$("#example1").DataTable().init();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	$(document).ready(function() {
		reloadPriceTypes();
		$('#updatePriceTypeButton').hide();
		$('#cancelUpdateButton').hide();

	});
	$('#savePriceTypeButton')
			.on(
					'click',
					function(e) {
						var priceType = $('#priceType').val();
						var reportName = $('#reportName').val();
						if (priceType == "" || reportName == "") {
							alert("L�tfen t�m alanlar� doldurunuz!");
							return false;
						}
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/createPriceType/?name="
											+ priceType
											+ "&reportName="
											+ reportName,
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											$('#priceType').val("");
											$('#reportName').val("");
											reloadPriceTypes();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})

	$('#updatePriceTypeButton').on(
			'click',
			function(e) {
				var name = $('#priceType').val();
				var reportName = $('#reportName').val();
				var id = $('#priceTypeId').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updatePriceType/?id="
							+ id + "&name=" + name + "&reportName="
							+ reportName,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#priceType').val("");
							$('#reportName').val("");
							$('#priceTypeId').val("");
							$('#updatePriceTypeButton').hide();
							$('#savePriceTypeButton').show();
							$('#cancelUpdateButton').hide();
							reloadPriceTypes();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		$('#priceType').val("");
		$('#reportName').val("");
		$('#priceTypeId').val("");
		$('#updatePriceTypeButton').hide();
		$('#savePriceTypeButton').show();
		$('#cancelUpdateButton').hide();
	})
</script>