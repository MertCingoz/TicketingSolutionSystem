<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BWT | Ana sayfa</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/font-awesome.min.css">
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/ionicons.min.css">
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/_all-skins.min.css">
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/dataTables.bootstrap.css">

<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/jquery-ui.css">
<script
	src="/TicketingSolutionManager/plugin/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="/TicketingSolutionManager/plugin/jQuery/jquery-ui.js"></script>
<script
	src="/TicketingSolutionManager/plugin/js/bootstrap-colorpicker.min.js"></script>
<script src="/TicketingSolutionManager/plugin/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="/TicketingSolutionManager/plugin/css/bootstrap-colorpicker.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%
			session.setAttribute("activePage", "PriceLists.jsp");
		%>
		<jsp:include page="/includes/pagedesign/Header.jsp"></jsp:include>
		<jsp:include page="/includes/pagedesign/Left.jsp"></jsp:include>

		<jsp:include page="PriceListsInc.jsp"></jsp:include>

		<jsp:include page="/includes/pagedesign/Footer.jsp"></jsp:include>
	</div>

	<script src="/TicketingSolutionManager/plugin/js/app.min.js"></script>
	<script src="/TicketingSolutionManager/plugin/js/bootstrap.min.js"></script>
	<script src="/TicketingSolutionManager/plugin/js/select2.full.min.js"></script>
	<script src="/TicketingSolutionManager/plugin/js/jquery.inputmask.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
	<script src="/TicketingSolutionManager/plugin/js/daterangepicker.js"></script>
	<script src="/TicketingSolutionManager/plugin/js/icheck.min.js"></script>

	<script
		src="/TicketingSolutionManager/plugin/js/jquery.dataTables.min.js"></script>
	<script
		src="/TicketingSolutionManager/plugin/js/dataTables.bootstrap.min.js"></script>


</body>
</html>
