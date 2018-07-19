package com.whpu.infoplat.servlet.part;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whpu.infoplat.util.DBHelper;

/**
 * 修改部门名称
 * @author young
 *
 */
@WebServlet("/Part_uppTpartServlet")
public class Part_uppTpartServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pid = req.getParameter("pid");
		String pname = req.getParameter("pname");
		
		//执行修改
		Connection conn = DBHelper.getConn();
		String sql = "UPDATE t_part SET p_name = ? WHERE p_id = ?";
		int count = DBHelper.executeUpdate(conn, sql, pname,pid);
		
		DBHelper.closeConn(conn);
	}
	

}
