package com.contactmanager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteContact")

public class DeleteContactServlet extends HttpServlet {
	@Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	
	int id = Integer.parseInt((request.getParameter("id")));
    ContactService contactService = new ContactService();
	try {
		contactService.deleteContact(id);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    request.getRequestDispatcher("/index.jsp").forward(request, response);

}

}
