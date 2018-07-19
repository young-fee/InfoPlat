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
 * 增加部门
 * @author young
 *
 */
@WebServlet("/Part_addTpartServlet")
public class Part_addTpartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String PName = req.getParameter("tPart.PName");
		String PRemark = req.getParameter("tPart.PRemark");
		//int PId = Integer.parseInt(req.getParameter("tPart.PId"));
		int PIs = 1;
		
		//连接数据库插入数据
		Connection conn = DBHelper.getConn();
		String sql = "insert into t_part(p_name,p_remark,p_is) values(?,?,?)";
		int count = DBHelper.executeUpdate(conn, sql,PName,PRemark,PIs);
		
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
