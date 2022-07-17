package services;

import database.backend;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class validateRegisterDetails {

    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[a-zA-Z]+");
    }

    public static boolean validateLastName(String lastName) {
        return lastName.matches("[a-zA-Z]+");
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }

    public static boolean validateIDNumber(String id) {
        return (id.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") || id.length() == 13);
    }
    
    public static boolean checkEmailExists(String email){
        boolean result = false;
        try {
            result = backend.checkRegistrationEmailExists(email);
        } catch (SQLException ex) {
            Logger.getLogger(validateRegisterDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }
}
