<%@page contentType="text/html;charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BWT | Ana sayfa</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="/TicketingSolutionManager/plugin/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="/TicketingSolutionManager/plugin/css/AdminLTE.min.css">
<link rel="stylesheet" href="/TicketingSolutionManager/plugin/css/_all-skins.min.css">
<script src="/TicketingSolutionManager/plugin/jQuery/jQuery-2.1.4.min.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
	<%session.setAttribute("activePage", "MonthlyMonitoring.jsp"); %>
		<jsp:include page="/includes/pagedesign/Header.jsp"></jsp:include>
		<jsp:include page="/includes/pagedesign/Left.jsp"></jsp:include>

		<jsp:include page="MonthlyMonitoringInc.jsp"></jsp:include>

		<jsp:include page="/includes/pagedesign/Footer.jsp"></jsp:include>
	</div>

	<script src="/TicketingSolutionManager/plugin/js/app.min.js"></script>
	<script src="/TicketingSolutionManager/plugin/js/bootstrap.min.js"></script>
</body>
</html>
