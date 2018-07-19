<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
         uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css"></link>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/emp/empback.js"></script>
	</head>
	<%
		String eid = request.getParameter("eid");
		String ETruename = request.getAttribute("ETruename").toString();
		List list = (List)session.getAttribute("tpartList");
	%>
	<body>
		<div class="formbody" style="width: 250px;">
			<input type="hidden" name="emp.EId" id="EID" value="<%=eid %>" />
			<ul class="forminfo1">
				<li>
					<label>员工姓名：</label>
					<input type="text" name="emp.ETruename" class="dfinput1" id="ETruename" value="<%=ETruename %>" disabled="disabled" placeholder="<%=ETruename %>"/>
				</li>
				<li>
					<label>重新分配到：</label>
					<div class="vocation">
						<select name="pid" class="selectinput1" id="pid">
							<c:forEach var="part" items="<%=list %>">
									<option value="${part.PId}">
										${part.PName}
									</option>
							</c:forEach>
						</select>
					</div>
				</li>

				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="确定复职" style="margin-left: -30px; margin-top: 10px;" />
				</li>
			</ul>
		</div>
		<input type="hidden" id="botao">
	</body>
</html>
