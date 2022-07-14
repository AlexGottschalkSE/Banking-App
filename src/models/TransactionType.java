package models;

public class TransactionType {

    private int transactionTypeID;
    private String name;
    private String description;
    private String code;

    public TransactionType() {

    }

    public TransactionType(String name, String desc, String code) {
        this.name = name;
        this.description = desc;
        this.code = code;
    }

    public int getTransactionTypeID() {
        return transactionTypeID;
    }

    public void setTransactionTypeID(int transactionTypeID) {
        this.transactionTypeID = transactionTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
