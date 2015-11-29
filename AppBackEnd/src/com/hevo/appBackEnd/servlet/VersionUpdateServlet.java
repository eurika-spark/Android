package com.hevo.appBackEnd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersionUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = -7558240821211748737L;
	
	private static final String APK_FILE_NAME = "AppStudy_1.apk";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		download(req, resp);
	}

	private void download(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String path = getServletContext().getRealPath("/");
			
			File file = new File(path + APK_FILE_NAME);
			if (file.exists()) {
				
				response.reset();
	            response.setContentType("application/x-msdownload");
	            response.addHeader("Content-Disposition", "attachment; filename=\"" + APK_FILE_NAME + "\"");
	            int fileLength = (int) file.length();
	            response.setContentLength(fileLength);
				
	            if (fileLength > 0) {
	                /*创建输入流*/
	                InputStream inStream = new FileInputStream(file);
	                byte[] buf = new byte[1024];
	                /*创建输出流*/
	                ServletOutputStream out = response.getOutputStream();
	                int readLength;
	                while (((readLength = inStream.read(buf)) != -1)) {
	                	out.write(buf, 0, readLength);
	                }
	                inStream.close();
	                out.flush();
	                out.close();
	            }
			} 
			System.out.println(SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(new Date())
					+ ", file of ["+APK_FILE_NAME+"] DOWNLOAD SUCCESSFULLY");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
