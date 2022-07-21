package models;


public class Transaction {

    private int transactionID;
    private int accountID;
    private String transactionTimestamp;
    private Double transactionAmount;
    private int transactionTypeID;
    public TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(int senderID, int accountID, String dateTime, double amount, int transactionTypeID) {
        this.accountID = accountID;
        this.transactionTimestamp = dateTime;
        this.transactionAmount = amount;
        this.transactionTypeID = transactionTypeID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(String transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionTypeID() {
        return transactionTypeID;
    }

    public void setTransactionTypeID(int transactionTypeID) {
        this.transactionTypeID = transactionTypeID;
    }
}
