<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="bwt.tools.ticketgenerator.BWTTicketGenerator"%>
<%
	BWTTicketGenerator tg = BWTTicketGenerator.getInstance();
	tg.createTicket("1234567891203", request.getParameter("detail"));
%>