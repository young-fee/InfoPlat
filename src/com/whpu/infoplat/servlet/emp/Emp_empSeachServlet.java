package com.whpu.infoplat.servlet.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.model.TEmp;
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Emp_empSeachServlet")
public class Emp_empSeachServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String etruename = req.getParameter("etruename");
		String esex = req.getParameter("esex");
		String pid = req.getParameter("pid");
		int current = Integer.parseInt(req.getParameter("current"));
		
		int pages = 100;
		int allcount = 0;
		int allpages = 0;
		int up = 0;
		int next = 0;
		current = current < 0 ? 0 : current;
		current = current > allpages ? allpages : current;

		
		Map<String, Integer> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		
		//通过员工id获取员工信息
		Connection conn = DBHelper.getConn();
		String sql = "SELECT\r\n" + 
				"    `t_part`.`p_name`\r\n" + 
				"    , `t_emp`.`e_loginname`\r\n" + 
				"    , `t_emp`.`e_psw`\r\n" + 
				"    , `t_emp`.`e_img`\r\n" + 
				"    , `t_emp`.`e_sex`\r\n" + 
				"    , `t_emp`.`e_flag`\r\n" + 
				"    , `t_emp`.`e_admin`\r\n" + 
				"    , `t_emp`.`e_remark`\r\n" + 
				"    , `t_emp`.`e_truename`\r\n" + 
				"    , `t_emp`.`e_id`\r\n" + 
				"    , `t_emp`.`e_is`\r\n" + 
				"FROM\r\n" + 
				"    `jaweb`.`t_emp`\r\n" + 
				"    INNER JOIN `jaweb`.`t_part` \r\n" + 
				"        ON (`t_emp`.`p_id` = `t_part`.`p_id`) where e_truename = ? or e_sex = ? or `t_part`.`p_id` = ?;";
		ResultSet rs = DBHelper.executeQuery(conn, sql,etruename,esex,pid);
		try {
			while(rs.next()) {
				/*int pid = rs.getInt("p_id");
				String e_truename = rs.getString("e_truename");
				String e_loginname = rs.getString("e_loginname");
				String e_remark = rs.getString("e_remark");
				String e_psw = rs.getString("e_psw");
				String e_sex = rs.getString("e_sex");
				String e_img = rs.getString("e_img");
				int e_flag = rs.getInt("e_flag");
				int e_admin = rs.getInt("e_admin");
				int e_is = rs.getInt("e_is");*/
				TEmp emp = new TEmp();
				TPart part = new TPart();
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
				list.add(emp);
			}
			
			allcount = rs.getRow();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		allpages = allcount / 10 + 1;
		up = current - 1;
		next = current + 1;

		map.put("current", current+1);
		map.put("allcount", allcount);
		map.put("allpages", allpages);
		map.put("up", up);
		map.put("next", next);
		list.add(map);
		 
		//将值传入前台
		Gson gson = new Gson();
		String json = gson.toJson(list);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
	}

}
