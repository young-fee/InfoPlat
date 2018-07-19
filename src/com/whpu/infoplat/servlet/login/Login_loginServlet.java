package com.whpu.infoplat.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

/**
 *登录验证
 * @author young
 *
 */
@WebServlet("/Login_loginServlet")
public class Login_loginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ELoginname = req.getParameter("emp.ELoginname");
		String EPsw = req.getParameter("emp.EPsw");
		
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_emp where e_loginname = ? and e_psw = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql, ELoginname,EPsw);
		int e_is = 0;
		int userlevel = 0;
		int e_id  = 0;
		int pid = 0;
		try {
			while(rs.next()) {
				String name = rs.getString("e_loginname");
				String psw = rs.getString("e_psw");
				e_is = rs.getInt("e_is");
				userlevel = rs.getInt("e_admin");
				e_id = rs.getInt("e_id");
				pid = rs.getInt("p_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//将用户名及用户级别放入session中
		HttpSession session = req.getSession();
		session.setAttribute("userName", ELoginname);
		session.setAttribute("userlevel", userlevel);
		session.setAttribute("eid", e_id);
		session.setAttribute("pid", pid);
		
		Map<String, Object> map = new HashMap<>();
		map.put("e_is", e_is);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");  
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
 	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
