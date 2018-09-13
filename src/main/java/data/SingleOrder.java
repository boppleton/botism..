package data;

public class SingleOrder {

    String exchange;
    String accountName;
    String accountID;
    double amount;
    double price;
    boolean side;

    String id;
    String status;

    String execInst;

    public SingleOrder(String exchange, String accountName, String accountID, double amount, double price, boolean side, String id, String status, String execInst) {
        this.exchange = exchange;
        this.accountName = accountName;
        this.accountID = accountID;
        this.amount = amount;
        this.price = price;
        this.side = side;
        this.id = id;
        this.status = status;
        this.execInst = execInst;
    }

    public String getExchange() {
        return exchange;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSide() {
        return side;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getExecInst() {
        return execInst;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }
}
