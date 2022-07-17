package controller;

import database.backend;
import java.util.ArrayList;
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
        backend.createNewAccountType(type);
    }

    public static void changeAccountType(AccountType type) {
        backend.changeAccountType(type);
    }

    public static void deleteAccountType(String code) {
        backend.deleteAccountType(code);
    }

    public static void createNewTransactionType(TransactionType type) {
        backend.createNewTransactionType(type);
    }

    public static void changeTransactionType(TransactionType type) {
        backend.changeTransactionType(type);
    }

    public static void deleteTransactionType(String code) {
        backend.deleteTransactionType(code);
    }
}
