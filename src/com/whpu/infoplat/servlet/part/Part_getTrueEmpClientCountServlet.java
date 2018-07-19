package com.whpu.infoplat.servlet.part;

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

import com.google.gson.Gson;
import com.whpu.infoplat.model.TEmp;
import com.whpu.infoplat.util.DBHelper;

/**
 * 查询该员工是否还有客户未交接
 * @author young
 *
 */
@WebServlet("/Part_getTrueEmpClientCountServlet")
public class Part_getTrueEmpClientCountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		List<TEmp> list = new ArrayList<>();
		//根据部门id查询该部门下的员工，再通过员工查询是否该员工还有客户未交接
		Connection conn = DBHelper.getConn();
		String sql = "SELECT e_truename FROM t_emp WHERE p_id =  ? AND e_id IN (SELECT e_id FROM t_client)";
		
		ResultSet  rs = DBHelper.executeQuery(conn, sql, pid);
		try {
			while(rs.next()) {
				TEmp emp = new TEmp();
				emp.setETruename(rs.getString("e_truename"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//传值
		Gson gson = new Gson();
		String json = gson.toJson(list);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		//关闭数据库
		DBHelper.closeConn(conn);
		
	}
	
	

}
