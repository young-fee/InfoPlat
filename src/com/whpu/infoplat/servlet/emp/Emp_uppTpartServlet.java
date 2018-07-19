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
 * 双击修改员工姓名
 * @author young
 *
 */
@WebServlet("/Emp_uppTpartServlet")
public class Emp_uppTpartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String eloginname = req.getParameter("eloginname");
		String eid = req.getParameter("eid");
		String epsw = req.getParameter("epsw");
		String etruename = req.getParameter("etruename");
		String esex = req.getParameter("esex");

		Connection conn = DBHelper.getConn();
		String sql = "";
		int count = 0;
		if(null!=eloginname && null != eid) {
			sql = "update t_emp set e_loginname = ? where e_id = ?";
			count = DBHelper.executeUpdate(conn, sql, eloginname ,eid);
		}else if(null!=epsw && null != eid) {
			sql = "update t_emp set e_psw = ? where e_id = ?";
			count = DBHelper.executeUpdate(conn, sql, epsw ,eid);
		}else if(null!=etruename && null != eid) {
			sql = "update t_emp set e_truename = ? where e_id = ?";
			count = DBHelper.executeUpdate(conn, sql, etruename ,eid);
		}else {
			sql = "update t_emp set e_sex = ? where e_id = ?";
			count = DBHelper.executeUpdate(conn, sql, esex,eid);
		}
		
		// 传值
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);

		// 关闭数据库
		DBHelper.closeConn(conn);

	}
}
