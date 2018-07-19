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
 * 查询所有部门的个数，是否有存在的部门可以用于添加员工
 * @author young
 *
 */
@WebServlet("/Emp_getAllPartCountServlet")
public class Emp_getAllPartCountServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询部门数量
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_part";
		int count = 0;
		ResultSet rs = DBHelper.executeQuery(conn, sql);
		try {
			while(rs.next()) {
				count = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//转json传值
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		//关闭数据库
		DBHelper.closeConn(conn);
	}
	
	

}
