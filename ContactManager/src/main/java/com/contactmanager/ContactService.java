package com.contactmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactService {

    
    public void addContact(Contact contact) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO contacts (name,email,phone,address) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getEmail());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.executeUpdate();
        }
    }

    public boolean isContactExist(Contact contact) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM contacts WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); 
        }
    }

    public List<Contact> getAllContacts() throws SQLException, ClassNotFoundException {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contacts";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
                );
                contacts.add(contact);
            }
        }
        return contacts;
    }

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

    
    public  void deleteContact(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM contacts WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public Contact getContactById(int id) throws SQLException, ClassNotFoundException {
        Contact contact = null;
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
                );
            }
        }
        return contact;
    }
}
