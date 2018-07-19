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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/emp/empupp.js"></script>
		<SCRIPT type="text/javascript"> var $webName="${pageContext.request.contextPath}"; </SCRIPT>
	</head>
	<%
		String eid = request.getParameter("eid");
		Object emp = request.getAttribute("emp");
		List list = (List)session.getAttribute("tpartList");
	%>
	<body>
		<div class="formbody">
			<!-- 图片预览 -->
			<div id="preview">
			    <img id="imghead" border=0 src="${pageContext.request.contextPath}/upfile/default.jpg" width="104" height="104" />
			</div>
			<input type="hidden" name="emp.EId" id="EID" value="${emp.EId }"/>
			<input type="hidden" name="emp.ESex" id="ESex" value="${ emp.ESex}"/>
			<ul class="forminfo1">
				<li>
					<label>真实姓名：</label>
					<input type="text" name="emp.ETruename" class="dfinput1" id="ETruename" value="${emp.ETruename }">
				</li>
				<li>
					<label>登录名称：</label>
					<input type="text" name="emp.ELoginname" class="dfinput1" id="ELoginname"  value="${emp.ELoginname }">
					<input type="hidden" name="oldpname" id="oldpname" />
				</li>
				<li>
					<label>登录密码：</label>
					<input type="password" class="dfinput1" id="EPsw" value="${ emp.EPsw}">
				</li>
				<li>
					<label>所在部门：</label>
					<div class="vocation">
						<select name="emp.TPart.PId" class="selectinput" id="PId">
							<c:forEach var="part" items="<%=list %>">
									<option value="${part.PId}">
										${part.PName}
									</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li>
					<label>员工性别：</label>
					<cite>
						<input name="emp.ESex" type="radio" value="男" id="nan" /> 男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="emp.ESex" type="radio" value="女" id="nv" /> 女
					</cite>
				</li>
				<li>
					<label>员工头像：</label>
					<input type="file" class="dfinput2" style="height: 30px;" name="EImgimg" id="EImgimg" />
					<input type="hidden" name="emp.EImg" id="oldImage" value="${ emp.EImg}">
				</li>
				<li>
					<label>员工备注：</label>
					<input type="text" class="textinput2" name="emp.ERemark" id="ERemark" placeholder="${emp.ERemark}" value="${emp.ERemark}"></textarea>
				</li>
				<li>
					<label>员工类别：</label>
					<div class="vocation">
						<SELECT class="selectinput" id="EFlag" name="emp.EFlag">
							<option value="0">普工</option>
							<option value="1">部门主管</option>
						</SELECT>
						<input type="hidden"name="emp.EFlag" id="EFlagHD" value="${emp.EFlag }"/>
					</div>
				</li>
				<li>
					<label>权限级别：</label>
					<div class="vocation">
						<SELECT class="selectinput" id="EAdmin" name="emp.EAdmin">
							<option value="0">普通操作者</option>
						</SELECT>
					</div>
				</li>
				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="修改员工" />
				</li>
			</ul>
		</div>
		<input type="hidden" id="botao">
		<input type="hidden" id="botao1">
	</body>
</html>
