package com.whpu.infoplat.servlet.part;

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

/**
 * 查询所有的部门信息
 * @author young
 *
 */
@WebServlet("/Part_allTpartServlet")
public class Part_allTpartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 根据p_is（删除没删除）查询所有部门(分页)
		int current = Integer.parseInt(req.getParameter("current"));
		int pis = Integer.parseInt(req.getParameter("pis"));
		int pages = 100;
		int allcount = 0;
		int allpages = 0;
		int up = 0;
		int next = 0;
		current = current < 0 ? 0 : current;
		current = current > allpages ? allpages : current;

		List<Object> list = new ArrayList<>();

		// 连接数据库查询所有的部门信息
		Connection conn = DBHelper.getConn();
		String sql = "select * from t_part where p_is = ? limit ? , ? ";
		ResultSet rs = DBHelper.executeQuery(conn, sql, pis, current, pages);

		try {
			while (rs.next()) {
				TPart part = new TPart();
				part.setPId(rs.getInt("p_id"));
				part.setPName(rs.getString("p_name"));
				part.setPRemark(rs.getString("p_remark"));
				part.setPIs(rs.getInt("p_is"));
				list.add(part);
			}

			allcount = rs.getRow();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		allpages = allcount / 10 + 1;
		up = current - 1;
		next = current + 1;

		Map<String, Integer> map = new HashMap<>();
		map.put("current", current+1);
		map.put("allcount", allcount);
		map.put("allpages", allpages);
		map.put("up", up);
		map.put("next", next);

		list.add(map);
		// 向页面传json
		Gson gson = new Gson();
		String json = gson.toJson(list);
// 		String json2 = gson.toJson(map);

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
// 		resp.getWriter().write(json2);

		DBHelper.closeConn(conn);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/*
	 * 获取部门总条数
	 */
//	public int getAllCounts() {
//		Connection conn = DBHelper.getConn();
//		// 分页
//		String sql2 = "select count(*) from t_part";
//
//		int count = Integer.parseInt(DBHelper.executeQuery(conn, sql2).toString());
//		DBHelper.closeConn(conn);
//		return count;
//	}

	
	
}
