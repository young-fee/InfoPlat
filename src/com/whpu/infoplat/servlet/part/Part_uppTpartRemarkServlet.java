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
 * 更改部门备注
 * @author young
 *
 */
@WebServlet("/Part_uppTpartRemarkServlet")
public class Part_uppTpartRemarkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		String premark = req.getParameter("premark");
		
		//执行修改
		Connection conn = DBHelper.getConn();
		String sql = "UPDATE t_part SET p_remark = ? WHERE p_id = ?";
		int count = DBHelper.executeUpdate(conn, sql, premark,pid);
		
		DBHelper.closeConn(conn);
	}


}
