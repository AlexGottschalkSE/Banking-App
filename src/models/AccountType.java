package models;

public class AccountType {

    private int accountTypeID;
    private String name;
    private String description;
    private String code;

    public AccountType() {

    }

    public AccountType(String name, String desc, String code) {
        this.name = name;
        this.description = desc;
        this.code = code;
    }

    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
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
