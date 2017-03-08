<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Fiyat Listeleri <small>Sistem üzerinde kullanılacak olan
				fiyat listelerini oluşturabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="priceListForm">
			<div class="box box-info" style="width: 400px">
				<div class="box-header">
					<h3 class="box-title">Yeni Fiyat Listesi Oluşturma</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<input type="hidden" id="priceListId"
							class="form-control my-colorpicker"> <label>Fiyat
							Listesi Adı :</label> <input type="text" id="priceListName"
							class="form-control" placeholder="Fiyat listesi adını giriniz...">
					</div>

					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deletePriceListButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="showDetailModalButton">Detay</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updatePriceListButton">Güncelle</button>
						<button type="button" class="btn btn-primary"
							id="savePriceListButton">Kaydet</button>
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
									<th>Fiyat Listesi Adı</th>
								</tr>
							</thead>
							<tbody id="priceListList">

							</tbody>
							<tfoot>
								<tr>
									<th>Fiyat Listesi Adı</th>
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
<div id="priceListDetailModal" title="Fiyat Listesi Detay"></div>

<script>
	function getPriceListById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getPriceListById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#priceListId').val(data.response.id);
					$('#priceListName').val(data.response.name);
					$('#updatePriceListButton').show();
					$('#savePriceListButton').hide();
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
		rowString += "<tr onclick=getPriceListById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadPriceLists() {
		var datas = "";
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllPriceLists/",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							for (var i = 0; i < data.response.priceListList.length; i++) {
								datas += getOneRow(data.response.priceListList[i].id,
										data.response.priceListList[i].status,
										data.response.priceListList[i].name);
							}
							$('#priceListList').html(datas);
							$("#example1").DataTable();
							$("#example1").DataTable().init();
						} else {
							alert(data.errorDesc);
						}
					}
				});
	}

	$(document).ready(function() {
		reloadPriceLists();
		$('#updatePriceListButton').hide();
		$('#cancelUpdateButton').hide();
		$('#showDetailModalButton').hide();

	});
	$('#savePriceListButton')
			.on(
					'click',
					function(e) {
						var priceListName = $('#priceListName').val();
						if (priceListName == "") {
							alert("Lütfen tüm alanları doldurunuz!");
							return false;
						}
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/createPriceList/?name="
											+ priceListName,
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											$('#priceListName').val("");
											reloadPriceLists();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})

	$('#updatePriceListButton').on(
			'click',
			function(e) {
				var name = $('#priceListName').val();
				var id = $('#priceListId').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updatePriceList/?id="
							+ id + "&name=" + name,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#priceListName').val("");
							$('#priceListId').val("");
							$('#updatePriceListButton').hide();
							$('#savePriceListButton').show();
							$('#cancelUpdateButton').hide();
							$('#showDetailModalButton').hide();
							reloadPriceLists();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		$('#priceListName').val("");
		$('#priceListId').val("");
		$('#updatePriceListButton').hide();
		$('#savePriceListButton').show();
		$('#cancelUpdateButton').hide();
		$('#showDetailModalButton').hide();
	})

	$('#showDetailModalButton').on(
			'click',
			function(e) {
				var priceListId = $('#priceListId').val();
				$("#priceListDetailModal").load(
						"PriceListsDetail.jsp?id=" + priceListId).dialog({
					height : 450,
					width : 750,
					modal : true,
					buttons : {
						Kapat : function() {
							$("#priceListDetailModal").dialog("close");
						}
					},
					close : function() {
					}
				});
				$("#priceListDetailModal").css("overflow", "hidden");
			})

	function savePriceListDetail() {
		alert("save price list detail");
	}
</script>