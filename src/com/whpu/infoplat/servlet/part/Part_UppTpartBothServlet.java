package com.whpu.infoplat.servlet.part;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.whpu.infoplat.util.DBHelper;

/**
 * 同时更改部门名称和备注
 * @author young
 *
 */
@WebServlet("/Part_UppTpartBothServlet")
public class Part_UppTpartBothServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String PName = req.getParameter("tPart.PName");
		String PRemark = req.getParameter("tPart.PRemark");
		String PId = req.getParameter("tPart.PId");

		//连接数据库更新数据
		Connection conn = DBHelper.getConn();
		String sql = "update t_part set p_name = ?,p_remark = ? where p_id = ?";
		int count = DBHelper.executeUpdate(conn, sql,PName,PRemark,PId);
		
		//将结果传出
		Gson gson = new Gson();
		String json = gson.toJson(count);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().write(json);
		
		//关闭数据库
		DBHelper.closeConn(conn);
	}
	

}
