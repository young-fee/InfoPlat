package com.whpu.infoplat.servlet.client;

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
import com.whpu.infoplat.model.TPart;
import com.whpu.infoplat.util.DBHelper;

@WebServlet("/Part_allTpartByPagesServlet")
public class Part_allTpartByPagesServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		Connection  conn = DBHelper.getConn();
		String sql = "select * from t_part ";
		ResultSet rs = DBHelper.executeQuery(conn, sql);
		try {
			while(rs.next()) {
				TPart part = new TPart();
				part.setPId(rs.getInt("p_id"));
				part.setPName(rs.getString("p_name"));
				list.add(part);
			}
			allcount = rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		DBHelper.closeConn(conn);
	}
}
