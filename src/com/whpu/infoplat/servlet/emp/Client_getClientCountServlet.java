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
 * 查询该员工是否还有未交接的客户
 * @author young
 *
 */
@WebServlet("/Client_getClientCountServlet")
public class Client_getClientCountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eid = req.getParameter("eid");
		int count = 0;
		//根据部门id查询该部门下的员工，再通过员工查询是否该员工还有客户未交接
		Connection conn = DBHelper.getConn();
		String sql = "SELECT e_id FROM t_client where e_id = ?";
		ResultSet  rs = DBHelper.executeQuery(conn, sql, eid);
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
