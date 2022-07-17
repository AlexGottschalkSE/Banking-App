package controller;

import database.backend;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.User;
import models.UserDTO;

public class UserController {

    public static void createUser(User user) {
        try {
            backend.RegisterNewUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserDTO LoginUser(String email, String password) {
        // Get user from db
        try {
            User userDetails = backend.GetUserByEmail(email);
            // If the user is found in the database
            if (userDetails != null) {
                // Check Password
                if (validatePassword(password, userDetails.getPassword()) != false) {
                    // If Valid Login
                    UserDTO userDTO = new UserDTO(userDetails);

                    return userDTO;
                } else {
                    // If Password Incorrect
                    return null;
                }
            } else {
                // If email not found
                return null;
            }
        } catch (Exception exc) {
            return null;
        }

    }

    public static void changeUserPassword(String email, String password) {
        try {
            backend.changeUserPassword(email, password);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Check Password
    public static boolean validatePassword(String password, String passwordHashed) {
        return (password.equals(passwordHashed));
    }
}
