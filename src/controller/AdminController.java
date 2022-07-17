package controller;

import database.backend;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AccountType;
import models.TransactionType;

public class AdminController {

    public static ArrayList getAccountTypes() {
        ArrayList accountTypeList;
        try {
            accountTypeList = backend.getAccountTypes();
        } catch (Exception exc) {
            return null;
        }
        return accountTypeList;
    }

    public static ArrayList getTransactionTypes() {
        ArrayList transactionTypes;
        try {
            transactionTypes = backend.getTransactionTypes();
        } catch (Exception exc) {
            return null;
        }
        return transactionTypes;
    }

    public static void createNewAccountType(AccountType type) {
        try {
            backend.createNewAccountType(type);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void changeAccountType(AccountType type) {
        try {
            backend.changeAccountType(type);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteAccountType(String code) {
        try {
            backend.deleteAccountType(code);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createNewTransactionType(TransactionType type) {
        try {
            backend.createNewTransactionType(type);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void changeTransactionType(TransactionType type) {
        try {
            backend.changeTransactionType(type);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteTransactionType(String code) {
        try {
            backend.deleteTransactionType(code);
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }
}
