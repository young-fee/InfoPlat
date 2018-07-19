package com.whpu.infoplat.servlet.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whpu.infoplat.model.TClient;
import com.whpu.infoplat.model.TEmp;
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Client_oneClientByCid2Servlet")
public class Client_oneClientByCid2Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cid = Integer.parseInt(req.getParameter("cid"));
		Connection  conn = DBHelper.getConn();
		String sql = "select * FROM\r\n" + 
				"    `jaweb`.`t_client`, \r\n" + 
				"    `jaweb`.`t_emp`\r\n" + 
				"    INNER JOIN `jaweb`.`t_part` \r\n" + 
				"        ON (`t_emp`.`p_id` = `t_part`.`p_id`) where c_id = ?";
		ResultSet rs = DBHelper.executeQuery(conn, sql,cid);
		
		TClient client = new TClient();
		try {
			while(rs.next()) {
				client.setCName(rs.getString("c_name"));
				client.setCAddress(rs.getString("c_address"));
				client.setCTel(rs.getString("c_tel"));
				TEmp emp = new TEmp();
				emp.setEId(rs.getInt("e_id"));
				TPart part = new TPart();
				part.setPId(rs.getInt("p_id"));
				emp.setTPart(part);
				client.setTEmp(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.setCharacterEncoding("utf-8");
		req.setAttribute("client", client);
		req.setAttribute("cid", cid);
		req.getRequestDispatcher("client/recoverClient.jsp").forward(req, resp);
		
		DBHelper.closeConn(conn);
	}
}
