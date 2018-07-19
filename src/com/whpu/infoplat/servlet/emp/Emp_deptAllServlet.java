package com.whpu.infoplat.servlet.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Emp_deptAllServlet")
public class Emp_deptAllServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eid = req.getParameter("eid");
		
		List<TPart> list = new ArrayList<>();
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_part where p_is = 1";
		ResultSet rs = DBHelper.executeQuery(conn, sql);
		try {
			while(rs.next()) {
				TPart part = new TPart();
				part.setPId(rs.getInt("p_id"));
				part.setPName(rs.getString("p_name"));
				list.add(part);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<select onchange=libin('" + eid + "',this);>");
		for (int i = 0; i < list.size(); i++) {
			TPart tPart = list.get(i);
			String tname = tPart.getPName();
			int tids = tPart.getPId();
			out.print("<option value='" + tids + "' >" + tname
						+ "</option>");
		}
		out.print("</select>");
		out.flush();
		out.close();
		
		DBHelper.closeConn(conn);
	}

}
