package com.whpu.infoplat.servlet.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

/**
 * 执行修改部门
 * @author young
 *
 */
@WebServlet("/Emp_upppartEmpServlet")
public class Emp_upppartEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pid = req.getParameter("pid");
		String eid = req.getParameter("eid");
		Connection conn = DBHelper.getConn();
		String sql = "update t_emp set p_id = ? where e_id = ?";
		int count = DBHelper.executeUpdate(conn, sql,pid,eid);
		
		//将值传入前台
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
		
	}

}
