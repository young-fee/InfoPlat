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

import com.whpu.infoplat.model.TEmp;
import com.whpu.infoplat.util.DBHelper;

/**
 * 恢复员工
 * @author young
 *
 */
@WebServlet("/Emp_toEmpUppServlet")
public class Emp_toEmpUppServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eid = req.getParameter("eid");
		TEmp emp = new TEmp();
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_emp where e_id = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, eid);
		try {
			while(rs.next()) {
				emp.setETruename(rs.getString("e_truename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBHelper.closeConn(conn);
		req.setAttribute("ETruename", emp.getETruename());
		req.getRequestDispatcher("emp/back.jsp").forward(req, resp);
		
	}

}
