package exchange.bitmex.BitmexJSON;

public class BitmexQuoteMessage {

    String symbol;
    String bidPrice;
    String askPrice;

    public BitmexQuoteMessage(String message) {

        symbol = message.substring(message.indexOf("symbol\":\"")+9, message.indexOf("\",\"bidSize\""));

        bidPrice = message.substring(message.indexOf("\"bidPrice\":")+11, message.indexOf(",\"askPrice\""));
        askPrice = message.substring(message.indexOf("\"askPrice\":")+11, message.indexOf(",\"askSize\""));


//        System.out.println("quote " + symbol + " bidprice:" + "(" + bidPrice + ")");


    }

    public String getSymbol() {
        return symbol;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public String getAskPrice() {
        return askPrice;
    }
}
