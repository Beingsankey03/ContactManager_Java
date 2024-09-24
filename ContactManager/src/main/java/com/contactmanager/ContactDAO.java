package com.contactmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    // Method to add a new contact
    public void addContact(Contact contact) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO contacts (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getEmail());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.executeUpdate();
        }
    }

    // Method to retrieve all contacts
    public List<Contact> getAllContacts() throws SQLException, ClassNotFoundException {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contacts";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setEmail(resultSet.getString("email"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setAddress(resultSet.getString("address"));
                contacts.add(contact);
            }
        }
        return contacts;
    }

    // Method to update an existing contact
    public void updateContact(Contact contact) throws SQLException, ClassNotFoundException {
        String query = "UPDATE contacts SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getEmail());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getId());
            preparedStatement.executeUpdate();
        }
    }

    // Method to delete a contact by ID
    public void deleteContact(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM contacts WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    
    
    //NEW EDIT 8.2
    public Contact getContactById(int id) throws SQLException, ClassNotFoundException {
        Contact contact = null;
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setEmail(resultSet.getString("email"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setAddress(resultSet.getString("address"));
            }
        }
        return contact;
    }



}
