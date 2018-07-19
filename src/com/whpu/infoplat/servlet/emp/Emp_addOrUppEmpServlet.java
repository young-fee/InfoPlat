package com.whpu.infoplat.servlet.emp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whpu.infoplat.util.DBHelper;

/**
 * 添加新员工
 * 
 * @author young
 *
 */
@WebServlet("/Emp_addOrUppEmpServlet")
public class Emp_addOrUppEmpServlet extends HttpServlet {

	public static int mount = 0;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Object> list = new ArrayList<>();
		// 从request当中获取流信息
		InputStream fileSource = req.getInputStream();
		String tempFileName = "D:/tempFile.jpg";
		// tempFile指向临时文件
		File tempFile = new File(tempFileName);
		if (!tempFile.exists()) {
			// 先得到文件的上级目录，并创建上级目录，在创建文件
			tempFile.getParentFile().mkdir();
			try {
				// 创建文件
				tempFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// outputStram文件输出流指向这个临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[2 * 1024];
		int n;
		while ((n = fileSource.read(b)) != -1) {
			outputStream.write(b, 0, n);
		}
		// 关闭输出流、输入流
		outputStream.close();
		fileSource.close();

		// 获取上传文件的名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "rw");
		InputStreamReader read = new InputStreamReader(new FileInputStream(tempFile), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		
		for (int i = 1; i <= 40; i++) {
			String str = reader.readLine();
			if (i % 4 == 0) {
				list.add(str);
			}
		}
		read.close();
		// 重新定位文件指针到文件头
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;
		// 获取文件内容 开始位置
		while ((n = randomFile.readByte()) != -1 && i <= 4) {
			if (n == '\n') {
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		startPosition = randomFile.getFilePointer() - 1;
		// 获取文件内容 结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while (endPosition >= 0 && j <= 2) {
			endPosition--;
			randomFile.seek(endPosition);
			if (randomFile.readByte() == '\n') {
				j++;
			}
		}
		endPosition = endPosition - 1;

		// 设置保存上传文件的路径
		String realPath = getServletContext().getRealPath("/") + "upfile";
		String newName = getMyName(tempFileName);
		File saveFile = new File(realPath, newName);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");

		// 从临时文件当中读取文件内容（根据起止位置获取）
		randomFile.seek(startPosition);
		while (startPosition < endPosition) {
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		// 关闭输入输出流、删除临时文件
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		System.out.println(saveFile.toString());

		//连接数据库进行插入操作
		Connection conn = DBHelper.getConn();
		String image = "upfile/"+newName;
		String sql = "insert into t_emp values( null,?,?,?,?,?,?,?,?,?,?)";
		DBHelper.executeUpdate(conn, sql, list.get(4),list.get(2),list.get(3),image,list.get(5),list.get(7),
				list.get(8),list.get(6),list.get(1),1);
		DBHelper.closeConn(conn);
	}

	// 修改图片名字
	private String getMyName(String myimgFileName) {
		String result = "";
		String lastname = myimgFileName.substring(myimgFileName.lastIndexOf("."));
		Date d = new Date();
		SimpleDateFormat ff = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = ff.format(d);
		Random rad = new Random();
		int num = rad.nextInt(999999);
		result = time + num + lastname;
		return result;
	}

}
