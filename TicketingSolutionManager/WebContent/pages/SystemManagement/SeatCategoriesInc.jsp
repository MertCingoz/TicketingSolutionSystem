<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Koltuk Kategori Yönetimi <small>Sistem üzerinde kullanılacak
				olan kategorileri oluşturabilirsiniz.</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/Default.jsp"><i class="fa fa-dashboard"></i>
					Anasayfa</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<form id="seatCategoryForm">
			<div class="box box-info" style="width: 400px">
				<div class="box-header">
					<h3 class="box-title">Yeni Koltuk Kategorisi Oluşturma</h3>
				</div>
				<div class="box-body">
					<!-- Color Picker -->
					<div class="form-group">
						<input type="hidden" id="categoryId"
							class="form-control my-colorpicker"> <label>Kategori
							Rengi:</label> <input type="text" id="categoryColor"
							class="form-control my-colorpicker">
					</div>
					<div class="form-group">
						<label>Kategori Adı :</label> <input type="text" id="categoryName"
							class="form-control" placeholder="Kategori adını giriniz...">
					</div>
					<div class="box-footer">
						<button type="button" style="display: none;"
							class="btn btn-primary" id="deleteSeatCategoryButton">Sil</button>
						<button type="button" class="btn btn-primary"
							id="cancelUpdateButton">Vazgeç</button>
						<button type="button" class="btn btn-primary"
							id="updateSeatCategoryButton">Güncelle</button>
						<button type="button" class="btn btn-primary"
							id="saveSeatCategoryButton">Kaydet</button>
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
						<h3 class="box-title">Koltuk Kategorileri</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Kategori Adı</th>
									<th>Kategori Rengi</th>
								</tr>
							</thead>
							<tbody id="categoryList">

							</tbody>
							<tfoot>
								<tr>
									<th>Kategori Adı</th>
									<th>Kategori Rengi</th>
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
	function getSeatCategoryById(id) {
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getSeatCategoryById/?id="
					+ id,
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					$('#categoryId').val(data.response.id);
					$('#categoryColor').val("#" + data.response.color);
					$('#categoryName').val(data.response.name);
					$('#updateSeatCategoryButton').show();
					$('#saveSeatCategoryButton').hide();
					$('#cancelUpdateButton').show();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	function getOneRow(id, status, name, color) {
		var rowString = "";
		rowString += "<tr onclick=getSeatCategoryById('" + id + "')>";
		rowString += "<td>" + name + "</td>";
		rowString += "<td bgcolor='"+color+"'>" + color + "</td>";
		rowString += "</tr>";
		return rowString;
	}

	function reloadSeatCategories() {
		var datas = "";
		$.ajax({
			type : 'GET',
			url : "/TicketingSolutionRest/getAllSeatCategories/",
			dataType : "json", // data type of response
			beforeSend : function() {
				$("#LoadingImage").show();
			},
			complete : function() {
				$("#LoadingImage").hide();
			},
			success : function(data) {
				if (data.errorCode == "0") {
					for (var i = 0; i < data.response.seatCategoryList.length; i++) {
						datas += getOneRow(data.response.seatCategoryList[i].id,
								data.response.seatCategoryList[i].status, data.response.seatCategoryList[i].name,
								data.response.seatCategoryList[i].color);
					}
					$('#categoryList').html(datas);
					$("#example1").DataTable();
					$("#example1").DataTable().init();
				} else {
					alert(data.errorDesc);
				}
			}
		});
	}

	$(document).ready(function() {
		reloadSeatCategories();
		$('#updateSeatCategoryButton').hide();
		$('#cancelUpdateButton').hide();

	});
	$('#saveSeatCategoryButton')
			.on(
					'click',
					function(e) {
						var color = $('#categoryColor').val().substring(1, 7);
						var categoryName = $('#categoryName').val();
						if (color == "" || categoryName == "") {
							alert("Lütfen tüm alanları doldurunuz!");
							return false;
						}
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/createSeatCategory/?name="
											+ categoryName + "&color=" + color,
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											$('#categoryColor').val("");
											$('#categoryName').val("");
											reloadSeatCategories();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})

	$('#updateSeatCategoryButton')
			.on(
					'click',
					function(e) {
						var color = $('#categoryColor').val().substring(1, 7);
						var categoryName = $('#categoryName').val();
						var id = $('#categoryId').val();
						$
								.ajax({
									type : 'GET',
									url : "/TicketingSolutionRest/updateSeatCategory/?id="
											+ id
											+ "&name="
											+ categoryName
											+ "&color=" + color,
									dataType : "json", // data type of response
									beforeSend : function() {
										$("#LoadingImage").show();
									},
									complete : function() {
										$("#LoadingImage").hide();
									},
									success : function(data) {
										if (data.errorCode == "0") {
											$('#categoryColor').val("");
											$('#categoryName').val("");
											$('#categoryId').val("");
											$('#updateSeatCategoryButton')
													.hide();
											$('#saveSeatCategoryButton').show();
											$('#cancelUpdateButton').hide();
											reloadSeatCategories();
										} else {
											alert(data.errorDesc);
										}
									}
								});
					})
	$('#cancelUpdateButton').on('click', function(e) {
		$('#categoryColor').val("");
		$('#categoryName').val("");
		$('#categoryId').val("");
		$('#updateSeatCategoryButton').hide();
		$('#saveSeatCategoryButton').show();
		$('#cancelUpdateButton').hide();
	})
</script>