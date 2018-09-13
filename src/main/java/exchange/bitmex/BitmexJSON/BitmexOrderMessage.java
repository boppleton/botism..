package exchange.bitmex.BitmexJSON;

import java.util.Objects;

public class BitmexOrderMessage extends BitmexMessage {

    private String symbol;
    private String action;
    private String id;
    private String execInst;
    private String status;
    private String ordRejReason;
    private String side;
    private int orderQty;
    private double price;

    public BitmexOrderMessage(String message) {

        this.message = message;

        symbol = getField("symbol", true);
        action = getField("action", true);
        id = getField("orderID", true);
        execInst = getField("execInst", true);
        status = getField("ordStatus", true);
        ordRejReason = getField("ordRejReason", true);

        side = getField("side", true);

        if (message.contains("\"orderQty\"")) {
            orderQty = Integer.parseInt(Objects.requireNonNull(getField("orderQty", false)));
        }

        if (message.contains("\"price\"")) {
            price = Double.parseDouble(Objects.requireNonNull(getField("price", false)));
        }


    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecInst() {
        return execInst;
    }

    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdRejReason() {
        return ordRejReason;
    }

    public void setOrdRejReason(String ordRejReason) {
        this.ordRejReason = ordRejReason;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
