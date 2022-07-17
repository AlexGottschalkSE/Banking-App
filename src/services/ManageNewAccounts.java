/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Random;

/**
 *
 * @author alexg
 */
public class ManageNewAccounts {

    public Double createNewSavingsAccountBalance() {
        Random r = new Random();
        int low = 4000;
        int high = 150000;
        Double result = Double.valueOf(r.nextInt(high - low) + low);
        return result;
    }

    public int createNewSavingsAccountNumber() {
        Random r = new Random();
        int low = 10000000;
        int high = 99999999;
        int result = r.nextInt(high - low) + low;
        return result;
    }
}
