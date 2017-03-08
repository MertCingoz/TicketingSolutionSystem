<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/TicketPreview.js"></script>
<style>
.labelClass {
	width: 100px;
	font-size: 12px;
	font-family: Arial;
}

.labelClassForIndex {
	width: 100px;
	font-size: 14px;
	font-family: Arial;
}

.inputTextClass {
	width: 150px;
	font-size: 12px;
	font-family: Arial;
}

#containment-wrapper {
	width: 915px;
	height: 284px;
	border: 1px solid #ccc;
	padding: 0px;
}

h3 {
	clear: left;
}
</style>
</head>
<body>
	<div id="containment-wrapper"></div>
	<div id="LoadingImage"
		style="display: none; position: fixed; top: 50%; left: 50%; -webkit-transform: translate(-50%, -50%); transform: translate(-50%, -50%);">
		<img src="/TicketingSolutionManager/plugin/images/ajax-loader.gif" />
	</div>
</body>
<script>
	$(document).ready(function() {
		var ticketDesignId =
<%=request.getParameter("ticketDesignId")%>
	;
		p = $("#containment-wrapper");
		position = p.position();
		console.log(position);
		containerX = position.left;
		containerY = position.top;
		getTicketDesign(ticketDesignId);
	});
</script>
</html>
