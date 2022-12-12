package com.playdeca.blackduckdatafiller;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserGenerator {

    public static void main(String[] args) {
        // Create a new Faker instance
        Faker faker = new Faker();
        List<User> Users;
        Users = new ArrayList<>();
        // Generate 100 random users
        for (int i = 0; i < 100; i++) {
            // Generate a random phone number in the XXXX-XXXX format
            String phoneNumber = faker.numerify("########");

            // Generate a random first and last name
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String fullName = firstName + " " + lastName;

            // Generate a random password
            String password = faker.internet().password();
            // Generate a random date and time

            java.sql.Date dateTimeString = java.sql.Date.valueOf(LocalDate.now());
            
            // Print the user details to the console
            Users.add(new User(phoneNumber, fullName, password, dateTimeString));
        }
        
            UserGenerator userGenerator = new UserGenerator();
            boolean Success = userGenerator.addUsersToDatabase(Users);
            if(Success){
                System.out.println("SE INSERTARON CORRECTAMENTE LOS 100 USUARIOS");
            }
    }

    public boolean addUsersToDatabase(List<User> users) {
        try {
            // Define the connection string, including the database name
            String connectionString = "jdbc:mysql://192.99.35.36/tlp_database";

            // Connect to the database
            Connection conn = DriverManager.getConnection(connectionString, "Proy3", "Asdf@1234");

            // Loop through the list of users
            for (User user : users) {
                // Create a new prepared statement to insert the user data into the database
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (user_telefono, user_name, user_password, user_time, user_rol, user_estado) VALUES (?, ?, ?, ?,'Cliente','Activo')");

                // Set the values of the prepared statement to the user data
                stmt.setInt(1, Integer.parseInt(user.getPhoneNumber()));
                stmt.setString(2, user.getFullName());
                stmt.setString(3, user.getPassword());
                stmt.setDate(4, user.getDateTimeString());

                // Execute the prepared statement
                stmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
            return false;
        }
    }
}
