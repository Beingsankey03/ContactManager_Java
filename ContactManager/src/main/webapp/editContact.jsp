<%@ page import="com.contactmanager.ContactService" %>
<%@ page import="com.contactmanager.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.SQLException" %>
<%
    ContactService contactService = new ContactService();
    int contactId = Integer.parseInt(request.getParameter("id"));
    Contact contact = contactService.getContactById(contactId);

%>
<html>
<head>
    <title>Edit Contact</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Edit Contact</h2>
    <form action="editContact?id=<%= contact.getId() %>" method="post">
        <div class="form-group">
            <label>Name:</label>
            <input type="text" name="name" class="form-control" value="<%= contact.getName() %>" required>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" class="form-control" value="<%= contact.getEmail() %>" required>
        </div>
        <div class="form-group">
            <label>Phone:</label>
            <input type="text" name="phone" class="form-control" value="<%= contact.getPhone() %>" required>
        </div>
        <div class="form-group">
            <label>Address:</label>
            <input type="text" name="address" class="form-control" value="<%= contact.getAddress() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Contact</button>
    </form>
</div>
</body>
</html>
