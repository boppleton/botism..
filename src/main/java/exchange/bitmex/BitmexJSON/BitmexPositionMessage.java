package exchange.bitmex.BitmexJSON;

public class BitmexPositionMessage extends BitmexMessage {

    private String symbol;
    private String action;
    private int currentQty;
    private double entry;


    public BitmexPositionMessage(String message) {

        this.message = message;

        symbol = getField("symbol", true);
        action = getField("action", true);

        try {

            if (message.contains("\"currentQty\"")) {
                currentQty = Integer.parseInt(getField("currentQty", false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (message.contains("\"avgEntryPrice\"")) {
                entry = Double.parseDouble(getField("avgEntryPrice", false));
            }
        } catch (Exception e) {
            entry = -1;
        }


    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public double getEntry() {
        return entry;
    }

    public void setEntry(double entry) {
        this.entry = entry;
    }
}
