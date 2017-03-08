<%@page contentType="text/html;charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<!-- search form -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Ara..."> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" id="sidebar-menu">

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>

<script type="text/javascript">
	function getMainMenuElement(name, href, icon, isMainMenuActive) {
		var tag = "<li class='treeview'>";
		if (isMainMenuActive) {
			tag = "<li class='treeview active'>";
		}
		var menuHtml = tag
				+ "<a href='"+href+"'> <i class='fa "+icon+"'></i> <span>"
				+ name
				+ "</span> <i class='fa fa-angle-left pull-right'></i></a>";
		return menuHtml;
	}
	function getSubMenuElement(name, href, icon, isActive) {
		var tag = "<li>";
		if (isActive) {
			tag = "<li class='active'>";
		}
		var menuHtml = tag + "<a href='"+href+"'><i class='fa "+icon+"'></i> "
				+ name + "</a></li>";
		return menuHtml;
	}

	$(document)
			.ready(
					function() {
						$
								.ajax(
										{
											url : "/TicketingSolutionRest/getMenuTree/?userid=1"
										})
								.then(
										function(data) {
											var menuHtml = "<li class='header'>Ana Menü</li>";
											var activePage ='<%=session.getAttribute("activePage")%>';
											for (var i = 0; i < data.response.length; i++) {
												
												menuBranches = "";
												isMainMenuActive = false;
												for (var k = 0; k < data.response[i].branches.length; k++) {
													var isActive = false;
													if (activePage == data.response[i].branches[k].page) {
														isActive = true;
														isMainMenuActive = true;
													}
													menuBranches += getSubMenuElement(
															data.response[i].branches[k].name,
															data.response[i].branches[k].url,
															data.response[i].branches[k].faicon,
															isActive);
												}
												menuHtml += getMainMenuElement(
														data.response[i].mainMenu.name,
														data.response[i].mainMenu.url,
														data.response[i].mainMenu.faicon,
														isMainMenuActive);
												menuHtml += "<ul class='treeview-menu'>";
												menuHtml += menuBranches;
												menuHtml += "</ul>";
												
											}
											menuHtml += "</li>";
											$("#sidebar-menu").html(menuHtml);

										});
					});
	<!--
//-->
</script>