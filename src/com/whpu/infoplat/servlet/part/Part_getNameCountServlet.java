package com.whpu.infoplat.servlet.part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

/**
 * 查询部门名称是否已经存在
 * @author young
 *
 */
@WebServlet("/Part_getNameCountServlet")
public class Part_getNameCountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int count = 0;
		
		//查询该名称是否已经存在
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_part where p_name = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, name);
		try {
			while(rs.next()) {
				count = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将数量count传回去
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		//关闭数据库
		DBHelper.closeConn(conn);
	}
	

}
