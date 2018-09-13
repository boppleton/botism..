package utils;

import org.knowm.xchange.dto.Order;

public class SingleTrade {

    public String pair;
    public Order.OrderType side;
    public double amt;
    public double price;

    SingleTrade(String pair, String side, double amt, double price) {
        this.pair = pair;
        this.side = side.contains("Buy")? Order.OrderType.BID: Order.OrderType.ASK;
        this.amt = amt;
        this.price = price;
    }
}
