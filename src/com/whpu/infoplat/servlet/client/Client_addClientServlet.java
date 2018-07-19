package com.whpu.infoplat.servlet.client;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Client_addClientServlet")
public class Client_addClientServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection  conn = DBHelper.getConn();
		String sql = "insert into t_client values(null,?,?,?,?,1) ";
		int count = DBHelper.executeUpdate(conn, sql,req.getParameter("client.TEmp.EId"),req.getParameter("client.CName"),req.getParameter("client.CTel"),req.getParameter("client.CAddress"));
		
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
	}

}
