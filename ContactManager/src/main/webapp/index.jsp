<%@ page import="java.util.List" %>
<%@ page import="com.contactmanager.Contact" %>
<%@ page import="com.contactmanager.ContactService" %>

<!DOCTYPE html>
<html>
<head>
    <title>Contact Manager</title>
    <meta charset="UTF-8">
    <!-- Include Bootstrap CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <h1 class="mt-5">Contact Manager</h1>

        <!-- Display success/error messages -->
        <%
            String successMessage = (String) request.getAttribute("successMessage");
            String errorMessage = (String) request.getAttribute("errorMessage");
            
            if (successMessage != null) {
        %>
            <div class="alert alert-success" role="alert">
                <%= successMessage %>
            </div>
        <%
            } else if (errorMessage != null) {
        %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
        <%
            }
        %>

        <!-- Form to Add a New Contact -->
        <h3>Add New Contact</h3>
        <form action="addContact" method="post" class="mb-4">
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address:</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>
            <button type="submit" class="btn btn-primary">Add Contact</button>
        </form>

        <!-- Display All Contacts -->
        <h3>Contact List</h3>
        <%
            ContactService contactService = new ContactService();
            List<Contact> contacts = contactService.getAllContacts();
        %>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (contacts != null && !contacts.isEmpty()) {
                    for (Contact contact : contacts) {
            %>
                <tr>
                    <td><%= contact.getId() %></td>
                    <td><%= contact.getName() %></td>
                    <td><%= contact.getEmail() %></td>
                    <td><%= contact.getPhone() %></td>
                    <td><%= contact.getAddress() %></td>
                    <td>
                        <a href="editContact?id=<%= contact.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="deleteContact?id=<%= contact.getId() %>" class="btn btn-danger btn-sm">Delete</a>
                        
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="6" class="text-center">No contacts available</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>

    </div>

    <!-- Include Bootstrap JS for functionality -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
