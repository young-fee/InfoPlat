package com.whpu.infoplat.servlet.emp;

import java.io.IOException;
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

import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Emp_getNameByLikeServlet")
public class Emp_getNameByLikeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String etruename = req.getParameter("etruename");
		List<String> list = new ArrayList<>();
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_emp where e_truename like ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, "%"+etruename+"%");
		try {
			while(rs.next()) {
				list.add(rs.getString("e_truename"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i) + "-";
		}
		
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write(result);
		
		DBHelper.closeConn(conn);
		
	}

}
