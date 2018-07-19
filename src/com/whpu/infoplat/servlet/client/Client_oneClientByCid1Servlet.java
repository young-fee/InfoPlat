package com.whpu.infoplat.servlet.client;

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
import com.whpu.infoplat.model.TClient;
import com.whpu.infoplat.model.TEmp;
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Client_oneClientByCid1Servlet")
public class Client_oneClientByCid1Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cid = Integer.parseInt(req.getParameter("cid"));
		List<TClient> list = new ArrayList<>();
		Connection  conn = DBHelper.getConn();
		String sql = "select * from t_emp  where e_id= (select e_id from t_client where c_id = ? and c_is = 0)";
		ResultSet rs = DBHelper.executeQuery(conn, sql,cid);
		
		TClient client = new TClient();
		TEmp emp = new TEmp();
		try {
			while(rs.next()) {
				emp.setEId(rs.getInt("e_id"));
				emp.setEIs(rs.getInt("e_is"));
				client.setTEmp(emp);
//				list.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(client);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
	}
}
