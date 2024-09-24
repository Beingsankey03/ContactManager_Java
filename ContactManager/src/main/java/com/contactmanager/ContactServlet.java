

package com.contactmanager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addContact")
public class ContactServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContactService contactService = new ContactService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Contact contact = new Contact(name, email, phone, address);

        try {
            if (contactService.isContactExist(contact)) {
                request.setAttribute("errorMessage", "Contact already exists.");
            } else {
                contactService.addContact(contact);
                request.setAttribute("successMessage", "Contact added successfully!");
            }
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding contact: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
   
    
    
}
