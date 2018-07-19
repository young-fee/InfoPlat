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

@WebServlet("/Client_backClientServlet")
public class Client_backClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cid = Integer.parseInt(req.getParameter("cid"));
		Connection conn = DBHelper.getConn();
		String sql = "update t_client set c_is = 1 where c_id = ?";
		int count = DBHelper.executeUpdate(conn, sql, cid);

		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);

		DBHelper.closeConn(conn);
	}
}