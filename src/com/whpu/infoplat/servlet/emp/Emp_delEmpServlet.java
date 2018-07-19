package com.whpu.infoplat.servlet.emp;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

/**
 * 离职员工
 * @author young
 *
 */
@WebServlet("/Emp_delEmpServlet")
public class Emp_delEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eid= req.getParameter("eid");
		
		Connection conn = DBHelper.getConn();
		String sql = "update t_emp set e_is = 0 where e_id = ?";
		int count = DBHelper.executeUpdate(conn, sql, eid);
		
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
