package data;

public class SinglePosition {

    String exchange;
    String accountName;

    String symbol;

    double size;

    double entry;

    public SinglePosition(String exchange, String accountName, String symbol, double size, double entry) {
        this.exchange = exchange;
        this.accountName = accountName;
        this.symbol = symbol;
        this.size = size;
        this.entry = entry;
    }

    public String getExchange() {
        return exchange;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getSize() {
        return size;
    }

    public double getEntry() {
        return entry;
    }
}
