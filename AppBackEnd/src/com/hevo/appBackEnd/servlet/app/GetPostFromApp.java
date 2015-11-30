package com.hevo.appBackEnd.servlet.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetPostFromApp extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String secretCode = (String) req.getParameter("secretCode");
		
		System.out.println("Get msg from App, secretCode["+secretCode+"]");
		
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String personName = req.getParameter("personName");
		String personPhone = req.getParameter("personPhone");
		
		System.out.println("Post msg from App, personName["+personName+"], personPhone["+personPhone+"]");
		super.doPost(req, resp);
	}
	
}
