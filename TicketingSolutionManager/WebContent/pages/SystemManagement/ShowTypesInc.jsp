<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Gösteri Tipleri <small>Sistem üzerinde kullanılacak olan
				gösteri tiplerini oluşturabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="showTypeForm">
			<div class="box box-info" style="width: 400px">
				<div class="box-header">
					<h3 class="box-title">Yeni Gösteri Tipi Oluşturma</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<input type="hidden" id="showTypeId"
							class="form-control my-colorpicker"> <label>Gösteri
							Tipi :</label> <input type="text" id="showType" class="form-control"
							placeholder="Gösteri tipini giriniz...">
					</div>
					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deleteShowTypeButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updateShowTypeButton">Güncelle</button>
						<button type="button" class="btn btn-primary"
							id="saveShowTypeButton">Kaydet</button>
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
						<h3 class="box-title">Gösteri Tipleri</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Gösteri Tipi</th>
								</tr>
							</thead>
							<tbody id="showTypeList">

							</tbody>
							<tfoot>
								<tr>
									<th>Gösteri Tipi</th>
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
	function getShowTypeById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getShowTypeById/?id=" + id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#showTypeId').val(data.response.id);
					$('#showType').val(data.response.name);
					$('#updateShowTypeButton').show();
					$('#saveShowTypeButton').hide();
					$('#cancelUpdateButton').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name) {
		var rowString = "";
		rowString += "<tr onclick=getShowTypeById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadShowTypes() {
		var datas = "";
		$
				.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/getAllShowTypes/",
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							for (var i = 0; i < data.response.showTypeList.length; i++) {
								datas += getOneRow(
										data.response.showTypeList[i].id,
										data.response.showTypeList[i].status,
										data.response.showTypeList[i].name);
							}
							$('#showTypeList').html(datas);
							$("#example1").DataTable();
							$("#example1").DataTable().init();
						} else {
							alert(data.errorDesc);
						}
					}
				});
	}

	$(document).ready(function() {
		reloadShowTypes();
		$('#updateShowTypeButton').hide();
		$('#cancelUpdateButton').hide();

	});
	$('#saveShowTypeButton').on('click', function(e) {
		var showType = $('#showType').val();
		if (showType == "") {
			alert("Lütfen tüm alanları doldurunuz!");
			return false;
		}
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/createShowType/?name=" + showType,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#showType').val("");
					reloadShowTypes();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	})

	$('#updateShowTypeButton').on(
			'click',
			function(e) {
				var name = $('#showType').val();
				var id = $('#showTypeId').val();
				$.ajax({
					type : 'GET',
					url : "/TicketingSolutionRest/updateShowType/?id=" + id
							+ "&name=" + name ,
					dataType : "json", // data type of response
					beforeSend : function() {
						$("#LoadingImage").show();
					},
					complete : function() {
						$("#LoadingImage").hide();
					},
					success : function(data) {
						if (data.errorCode == "0") {
							$('#showType').val("");
							$('#showTypeId').val("");
							$('#updateShowTypeButton').hide();
							$('#saveShowTypeButton').show();
							$('#cancelUpdateButton').hide();
							reloadShowTypes();
						} else {
							alert(data.errorDesc);
						}
					}
				});
			})
	$('#cancelUpdateButton').on('click', function(e) {
		$('#showType').val("");
		$('#showTypeId').val("");
		$('#updateShowTypeButton').hide();
		$('#saveShowTypeButton').show();
		$('#cancelUpdateButton').hide();
	})
</script>