package com.contactmanager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editContact")

public class EditContactServlet extends HttpServlet {
	@Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	    request.getRequestDispatcher("/editContact.jsp").forward(request, response);
}
	@Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		int id = Integer.parseInt((request.getParameter("id")));
		 String name = request.getParameter("name");
	     String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        String address = request.getParameter("address");

	        Contact contact = new Contact(id,name, email, phone, address);
	        ContactService contactService = new ContactService();
	try {
		contactService.updateContact(contact);
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


