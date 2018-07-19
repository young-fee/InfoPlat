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
 * 根据输入的部门名称获取部门信息，是否存在想同的部门名称
 * @author young
 *
 */
@WebServlet("/Part_allPnameCountServlet")
public class Part_allPnameCountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String partName = req.getParameter("name");
		int count = 0;
		
		//根据输入的部门名称获取部门信息，是否存在想同的部门名称
		Connection conn = DBHelper.getConn();
		String sql = "select count(*) from t_part where  p_name = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, partName);
		try {
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将count转换成json字符串传到前台
		
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		
		//关闭数据库
		DBHelper.closeConn(conn);
	}

}
