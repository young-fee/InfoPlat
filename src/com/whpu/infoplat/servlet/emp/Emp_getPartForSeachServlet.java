package com.whpu.infoplat.servlet.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

/**
 * 查询部门用于搜索
 * @author young
 *
 */
@WebServlet("/Emp_getPartForSeachServlet")
public class Emp_getPartForSeachServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TPart> list = new ArrayList<>();
		Connection  conn = DBHelper.getConn();
		String sql = "select * from t_part ";
		ResultSet rs = DBHelper.executeQuery(conn, sql);
		try {
			while(rs.next()) {
				TPart part = new TPart();
				part.setPId(rs.getInt("p_id"));
				part.setPName(rs.getString("p_name"));
				list.add(part);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
	}

}
