package models;


public class Transaction {

    private int transactionID;
    private int recipientAccountNumber;
    private int senderAccountNumber;
    private String transactionTimestamp;
    private Double transactionAmount;
    private int transactionTypeID;
    public TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(int senderID, int accountNumber, String dateTime, double amount, int transactionTypeID) {
        this.recipientAccountNumber = accountNumber;
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

    public int getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public int getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(int recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }
}
