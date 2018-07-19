package com.whpu.infoplat.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

/**
 * 根据用户名进行查询，是否已经存在这个用户名
 * @author young
 *
 */
@WebServlet("/Login_getENameCountServlet")
public class Login_getENameCountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName =  req.getParameter("username");
		Connection conn = DBHelper.getConn();
		String sql = "Select * from t_emp where e_loginname = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, userName);
		int count = 0;
		try {
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("count", count);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		resp.setCharacterEncoding("UTF-8");  
		resp.setContentType("application/json");  
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
}
