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
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Emp_oneEmpServlet")
public class Emp_oneEmpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TEmp emp = new TEmp();
		TPart part = new TPart();
		String eid = req.getParameter("eid");
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_emp INNER JOIN `jaweb`.`t_part` ON (`t_emp`.`p_id` = `t_part`.`p_id`) where e_id = ? ";
		ResultSet rs = DBHelper.executeQuery(conn, sql, eid);
		try {
			while(rs.next()) {
				emp.setELoginname(rs.getString("e_loginname"));
				emp.setETruename(rs.getString("e_truename"));
				emp.setERemark(rs.getString("e_remark"));
				emp.setEPsw(rs.getString("e_psw"));
				emp.setESex(rs.getString("e_sex"));
				emp.setEAdmin(rs.getInt("e_admin"));
				emp.setEId(rs.getInt("e_id"));
				emp.setEImg(rs.getString("e_img"));
				emp.setEFlag(rs.getInt("e_flag"));
				part.setPName(rs.getString("p_name"));
				emp.setTPart(part);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("emp/upp.jsp").forward(req, resp);
	}

}
