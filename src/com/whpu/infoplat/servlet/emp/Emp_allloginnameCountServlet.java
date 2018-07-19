package com.whpu.infoplat.servlet.emp;

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
 * 当前的是否用户名是否存在
 * @author young
 *
 */
@WebServlet("/Emp_allloginnameCountServlet")
public class Emp_allloginnameCountServlet extends HttpServlet{

	/**
	 * 先判断当前的是否用户名是否存在
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eloginname = req.getParameter("eloginname");
		int count = 0;
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_emp where e_loginname = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, eloginname);
		try {
			while(rs.next()) {
				count = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//传值
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		//关闭数据库
		DBHelper.closeConn(conn);
	}

}
