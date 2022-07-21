package controller;

import database.backend;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;
import models.Account;
import models.AccountType;
import models.Transaction;
import models.UserDTO;

public class MainMenuController {

    public static void displayAccounts(UserDTO details, JTextArea area1, JTextArea area2) {
        int i = 0;
        ArrayList<JTextArea> areas = new ArrayList<>(Arrays.asList(area1, area2));
        try {

            for (Account account : details.accounts) {
                AccountType accountType;
                accountType = account.getAccountType();
                areas.get(i).setText((accountType.getName() + " Account\n" + account.getAccountNumber() + "\n\nR" + account.getBalance() + "\nBalance"));
                i++;
            }

        } catch (Exception exc) {
            System.out.println(exc);
        }

    }
    
    public static boolean transferAmount(UserDTO details, Transaction transaction, Account senderAccount){
        try{
            return backend.transferAmount(details, transaction, senderAccount);
        }catch(Exception exc){
            return false;
        }
    }
}
