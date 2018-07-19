package com.whpu.infoplat.servlet.client;

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
import com.whpu.infoplat.model.TEmp;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Emp_ajaxGetEmpServlet")
public class Emp_ajaxGetEmpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pid = Integer.parseInt(req.getParameter("pid"));
		List<TEmp> list = new ArrayList<>();
		Connection  conn = DBHelper.getConn();
		String sql = "select * from t_emp where p_id = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql,req.getParameter("pid"));
		try {
			while(rs.next()) {
				TEmp emp = new TEmp();
				emp.setEId(rs.getInt("e_id"));
				emp.setETruename(rs.getString("e_truename"));
				list.add(emp);
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
