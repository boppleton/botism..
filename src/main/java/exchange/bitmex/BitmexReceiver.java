package exchange.bitmex;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Orders;
import data.SingleOrder;
import exchange.bitmex.BitmexJSON.BitmexOrder;
import exchange.bitmex.BitmexJSON.BitmexOrders;
import gui.GUI;
import utils.Broadcaster;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BitmexReceiver implements Broadcaster.BroadcastListener {

    private ObjectMapper mapper = new ObjectMapper();

    public BitmexReceiver() {

        Broadcaster.register(this);

    }

    @Override
    public void receiveBroadcast(String message) throws InterruptedException, IOException {
//        System.out.println(message);

//        logArea.insert(message + "\n", 0);

        if (message.contains("\"table\":\"trade\"")) {
//            tradeMessage(message);
        } else if (message.contains("\"table\":\"quote\"")) {
//            quoteMessage(message);
        } else if (message.contains("\"table\":\"order\"")) {
            orderMessage(message);

        } else if (message.contains("\"table\":\"position\"")) {
//            positionMessage(message);
        }
    }

    private void orderMessage(String message) throws IOException {

        //for testing
        System.out.println(message);
        GUI.getInstance().log(message);

        //parse
        BitmexOrders orders = mapper.readValue(message, BitmexOrders.class);

        //get array of orders (if multiple)
        List<BitmexOrder> orderList = orders.getData();

        //for each order in new incoming batch (will usually just be 1, but if its a scaled order comes in a batch)
        for (BitmexOrder incomingOrder : orderList) {

            //see if it's in orderlist already
            boolean inList = false;
            for (SingleOrder o : Orders.getInstance().getOrdersList()) {
                if (incomingOrder.getOrderID().contains(o.getId())) {

                    //order in list, update stuff
                    if (incomingOrder.getOrdStatus() != null) {
                        o.setStatus(incomingOrder.getOrdStatus());
                    }

                    //update gui stuff wfor updated order bits
                    //

                    inList = true;
                }
            }

            //if not in orderlist, add it
            if (!inList) {
                Orders.getInstance().add(new SingleOrder("bitmex", null, incomingOrder.getAccount().toString(),
                        incomingOrder.getOrderQty(), incomingOrder.getPrice(), incomingOrder.getSide().contains("Buy"),
                        incomingOrder.getOrderID(), incomingOrder.getOrdStatus(), incomingOrder.getExecInst() ));
            }

        }
//
//            if (o.getOrdStatus() != null) {
//                GUI.log("order update (" + o.getOrderID().substring(0, 4) + ") status: " + o.getOrdStatus() + " " + o.getOrdRejReason() + " size: " + o.getOrderQty() + " price: " + o.getPrice());
//            }
//
////            System.out.println("chaselist " + LimitChaseContainer.getScaledChaseList().size());
//
//            for (Iterator<LimitChaseSingle> iterator = LimitChaseContainer.getSingleChaseList().iterator(); iterator.hasNext(); ) {
//                LimitChaseSingle value = iterator.next();
////                System.out.println("value id- " + value.getId());
//                if (o.getOrdStatus() != null && value != null && o.getOrderID().contains(value.getId())) {
//                    if (o.getOrdStatus().contains("Filled")) {
//                        GUI.log("chase filled!");
//                        iterator.remove();
//
//                        GUI.removeLimitChaseSingleBar(o.getOrderID());
//
//                    } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
//                        GUI.log("chase canceled/rejected - re place order");
//                        System.out.println("chase canceled/rejected, re place");
//
//                        value.setUpdatable(false);
//
//                        GUI.removeLimitChaseSingleBar(o.getOrderID());
//
//                        value.initialOrder(value.getAmt(), value.getSide());
//                    } else if (o.getOrdStatus().contains("Partial")) {
//                        GUI.log("chase partially filled, remaining " + o.getLeavesQty());
//                        value.setAmt(o.getLeavesQty());
//                    }
//                }
//
//            }
//
//
//            for (Iterator<LimitChaseScaled> iterator = LimitChaseContainer.getScaledChaseList().iterator(); iterator.hasNext(); ) {
//                LimitChaseScaled value = iterator.next();
//
//                ArrayList<BitmexPrivateOrder> olistt = value.getOrderlist();
//
//                for (int i = 0; i < olistt.size(); i++) {
//
//                    if (o.getOrdStatus() != null && value != null && o.getOrderID().contains(olistt.get(i).getId())){
//                        if (o.getOrdStatus().contains("Filled")) {
//                            System.out.println("order filled; o.id- " + o.getOrderID());
//
//                            value.pause();
//
////                            value.removeOrder(o.getOrderID(), o.getCumQty());
//
////                            value.cancelAllRestart();
//
//                        } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
//                            GUI.log("chase canceled/rejected - re place order");
//
//                            System.out.println("order canceled/rejected, restart all? " + o.getOrdRejReason());
//
//                        }
//
//
//                    }
//
//                }
//
////                if (o.getOrdStatus() != null && value != null && o.getOrderID().contains(value.getId())) {
////                    if (o.getOrdStatus().contains("Filled")) {
////                        GUI.log("chase filled!");
////                        iterator.remove();
////                    } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
////                        GUI.log("chase canceled/rejected - re place order");
////                        value.initialOrder(value.getAmt(), value.getSide());
////                    } else if (o.getOrdStatus().contains("Partial")) {
////                        GUI.log("chase partially filled, remaining " + o.getLeavesQty());
////                        value.setAmt(o.getLeavesQty());
////                    }
////                }
//
////            }
//
//
//
//
//
////
////            for (LimitChaseSingle chase : LimitChaseContainer.getSingleChaseList()) {
////                if (o.getOrderID().contains(chase.getId()) && o.getOrdStatus() != null) {
////                    if (o.getOrdStatus().contains("Filled")) {
////                        GUI.log("chase filled!");
////                        LimitChaseContainer.removeChaseSingle(chase);
////                    } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
////                        GUI.log("chase canceled/rejected - re place order");
////                        chase.initialOrder(chase.getAmt(), chase.getSide());
////                    } else if (o.getOrdStatus().contains("Partial")) {
////                        GUI.log("chase partially filled, remaining " + o.getLeavesQty());
////                        chase.setAmt(o.getLeavesQty());
////                    }
////                }
////            }
//        }
//
//


    }

//    private void tradeMessage(String message) {
//
//        BitmexTrades trades;
//
//        double bid;
//        double ask;
//
//        double currentBidS;
//        double currentAskS;
//
//
//        try {
//            trades = mapper.readValue(message, BitmexTrades.class);
//            List<BitmexTrade> tradeData = trades.getData();
//
//            BitmexTrade trade1 = tradeData.get(0);
//
//            if (trade1.getSymbol().contains("XBT")) {
//                bid = currentBidxbt;
//                ask = currentAskxbt;
//
//                currentBidS = currentBidxbt;
//                currentAskS = currentAskxbt;
//            } else {
//                bid = currentBideth;
//                ask = currentAsketh;
//
//                currentBidS = currentBideth;
//                currentAskS = currentAsketh;
//            }
//
//            //ez
//            int total = 0;
//            for (int i = 0; i < tradeData.size(); i++) {
//                total += tradeData.get(i).getSize();
//            }
//            double firstPrice = trade1.getPrice();
//            double lasttPrice = tradeData.get(tradeData.size() - 1).getPrice();
//
//
//
//
//            if (trade1.getSide().contains("Buy")) {
//                if (lasttPrice > currentAskS || currentAskS == -1) {
//
//                    currentAskS = lasttPrice;
//                    currentBidS = lasttPrice - (trade1.getSymbol().contains("XBT")?0.5:.05);
//
//
//
//                }
//            } else if (trade1.getSide().contains("Sell")) {
//                if (lasttPrice < currentBidS || currentBidS == -1) {
//                    currentBidS = lasttPrice;
//                    currentAskS = lasttPrice + (trade1.getSymbol().contains("XBT")?0.5:.05);
//
//
//                }
//            }
//
//            if (trade1.getSymbol().contains("XBT")) {
////                currentBidS = Formatter.getpoint5round(currentBidS).doubleValue();
////                currentAskS = Formatter.getpoint5round(currentAskS).doubleValue();
//            } else {
//                currentBidS = new BigDecimal(currentBidS).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
//                currentAskS = new BigDecimal(currentAskS).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
//            }
//
//            ZonedDateTime time = ZonedDateTime.parse(trade1.getTimestamp());
//
////            log("trade: " + trade1.getSide() + " " + total + " from " + firstPrice + " to " + lasttPrice + " time: " + time);
//
////            System.out.println("bid: " + bid + " currentBid: " + currentBid);
//
//            if (bid != currentBidS || ask != currentAskS || bid == -1) {
//
//                if (trade1.getSymbol().contains("XBT")) {
//                    currentBidxbt = currentBidS;
//                    currentAskxbt = currentAskS;
//                } else if (trade1.getSymbol().contains("ETH")) {
//                    currentBideth = currentBidS;
//                    currentAsketh = currentAskS;
//                }
//
//                System.out.println("bid-" + bid + " currentbidS-" + currentBidS);
//
//
//                for (SingleTimeSeries s : TimeSeriesContainer.getAllSeries()) {
//
//                    System.out.println(s.getSymbol() + " " + trade1.getSymbol().toLowerCase());
//
//
//                    if (s.getSymbol().contains(trade1.getSymbol().toLowerCase())) {
//                        s.addTrade(time, total, lasttPrice);
//                    }
//
//                }
//
////                System.out.println("price changed. bid " + currentBid + " ask " + currentAsk);
//
////                System.out.println("chasecount: " + LimitChaseContainer.getSingleChaseList().size());
//
////                log("bid-" + currentBid + " ask-" + currentAsk);
////                bidLabel.setText(" bid: " + currentBid);
////                askLabel.setText(" ask: " + currentAsk);
//
//
//                BidAsk.setBidAsk(trade1.getSymbol().replaceAll("/","").toLowerCase(), currentBidS, currentAskS);
//
//                for (LimitChaseSingle chaseSingle : LimitChaseContainer.getSingleChaseList()) {
//                    if (chaseSingle.isUpdatable()) {
//                        System.out.println("UPDATING CHASESINGLE going int omethod..");
//                        chaseSingle.updatePrice(trade1.getSymbol().replace("/", "").toLowerCase(), currentBidS, currentAskS);
//                    }
//                }
//                for (LimitChaseScaled chaseScaled : LimitChaseContainer.getScaledChaseList()) {
//                    if (!chaseScaled.isPause()) {
//
//                        if (chaseScaled.getSymbol().contains(trade1.getSymbol().replaceAll("/","").toLowerCase())){
//                            System.out.println("updating price!!");
//                            chaseScaled.updatePrice(trade1.getSymbol().replace("/","").toLowerCase(), currentBidS, currentAskS);
//                        }
//
//
//
//                    }
//                }
//
//
//
//
//
////                for (LimitChase c : openLimitChases) {
////                    c.updateLimitChase(currentBid, currentAsk);
////                }
//
//
//            }
//
//
//        } catch (Exception ee) {
//            ee.printStackTrace();
//        }
//    }

//    private void positionMessage(String message) throws InterruptedException {
//
//        BitmexPositionMessage positionMessage = new BitmexPositionMessage(message);
//
//        int msgQty = positionMessage.getCurrentQty();
//
//        if (positionMessage.getEntry() > 0) {
//            System.out.println("updating entry to " + positionMessage.getEntry());
//            currentPositionEntry = positionMessage.getEntry();
//        }
//
//
//        if (currentPosition != msgQty && msgQty != 0) {
//
////            log(positionMessage.getSymbol() + " updated position size: " + msgQty);
//
//            currentPosition = msgQty;
//
////            resetClose(currentPosition);
//
//        }
//
//        if (msgQty == 0) {
//            currentPosition = 0;
//        }
//    }


//    private void orderMessage(String message) throws InterruptedException, IOException {
//        System.out.println(message);
//
//        orders = mapper.readValue(message, BitmexOrders.class);
//        List<BitmexOrder> orderList = orders.getData();
//
//        for (BitmexOrder o : orderList) {
//
//            if (o.getOrdStatus() != null) {
//                GUI.log("order update (" + o.getOrderID().substring(0, 4) + ") status: " + o.getOrdStatus() + " " + o.getOrdRejReason() + " size: " + o.getOrderQty() + " price: " + o.getPrice());
//            }
//
////            System.out.println("chaselist " + LimitChaseContainer.getScaledChaseList().size());
//
//            for (Iterator<LimitChaseSingle> iterator = LimitChaseContainer.getSingleChaseList().iterator(); iterator.hasNext(); ) {
//                LimitChaseSingle value = iterator.next();
////                System.out.println("value id- " + value.getId());
//                if (o.getOrdStatus() != null && value != null && o.getOrderID().contains(value.getId())) {
//                    if (o.getOrdStatus().contains("Filled")) {
//                        GUI.log("chase filled!");
//                        iterator.remove();
//
//                        GUI.removeLimitChaseSingleBar(o.getOrderID());
//
//                    } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
//                        GUI.log("chase canceled/rejected - re place order");
//                        System.out.println("chase canceled/rejected, re place");
//
//                        value.setUpdatable(false);
//
//                        GUI.removeLimitChaseSingleBar(o.getOrderID());
//
//                        value.initialOrder(value.getAmt(), value.getSide());
//                    } else if (o.getOrdStatus().contains("Partial")) {
//                        GUI.log("chase partially filled, remaining " + o.getLeavesQty());
//                        value.setAmt(o.getLeavesQty());
//                    }
//                }
//
//            }
//
//
//            for (Iterator<LimitChaseScaled> iterator = LimitChaseContainer.getScaledChaseList().iterator(); iterator.hasNext(); ) {
//                LimitChaseScaled value = iterator.next();
//
//                ArrayList<BitmexPrivateOrder> olistt = value.getOrderlist();
//
//                for (int i = 0; i < olistt.size(); i++) {
//
//                    if (o.getOrdStatus() != null && value != null && o.getOrderID().contains(olistt.get(i).getId())){
//                        if (o.getOrdStatus().contains("Filled")) {
//                            System.out.println("order filled; o.id- " + o.getOrderID());
//
//                            value.pause();
//
////                            value.removeOrder(o.getOrderID(), o.getCumQty());
//
////                            value.cancelAllRestart();
//
//                        } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
//                            GUI.log("chase canceled/rejected - re place order");
//
//                            System.out.println("order canceled/rejected, restart all? " + o.getOrdRejReason());
//
//                        }
//
//
//                    }
//
//                }
//
////                if (o.getOrdStatus() != null && value != null && o.getOrderID().contains(value.getId())) {
////                    if (o.getOrdStatus().contains("Filled")) {
////                        GUI.log("chase filled!");
////                        iterator.remove();
////                    } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
////                        GUI.log("chase canceled/rejected - re place order");
////                        value.initialOrder(value.getAmt(), value.getSide());
////                    } else if (o.getOrdStatus().contains("Partial")) {
////                        GUI.log("chase partially filled, remaining " + o.getLeavesQty());
////                        value.setAmt(o.getLeavesQty());
////                    }
////                }
//
//            }
//
//
//
//
//
////
////            for (LimitChaseSingle chase : LimitChaseContainer.getSingleChaseList()) {
////                if (o.getOrderID().contains(chase.getId()) && o.getOrdStatus() != null) {
////                    if (o.getOrdStatus().contains("Filled")) {
////                        GUI.log("chase filled!");
////                        LimitChaseContainer.removeChaseSingle(chase);
////                    } else if (o.getOrdStatus().contains("Canceled") || o.getOrdStatus().contains("Rejected")) {
////                        GUI.log("chase canceled/rejected - re place order");
////                        chase.initialOrder(chase.getAmt(), chase.getSide());
////                    } else if (o.getOrdStatus().contains("Partial")) {
////                        GUI.log("chase partially filled, remaining " + o.getLeavesQty());
////                        chase.setAmt(o.getLeavesQty());
////                    }
////                }
////            }
//        }
//
//    }


    public static void log(String msg) {

        System.out.println("logging " + msg);

//        GUI.log(msg);

    }

//    public void getInitialPosition() {
//
//        log("trying to update current position..");
//
//        BitmexPosition pos = null;
//        try {
//            pos = BitmexRest.getPosition("XBTUSD");
//
//            System.out.println(pos.toString());
//
//            log("position pull - " + pos.getCurrentQty() + " entry " + pos.getAvgEntryPrice());
//        } catch (Exception e) {
//            e.printStackTrace();
//            log("error getting position: " + e.getMessage());
//        }
//
//        if (pos != null) {
////            currentPositionLabel.setText(" position: " + pos.getCurrentQty() + " entry: " + pos.getAvgEntryPrice());
//        } else {
////            currentPositionLabel.setText("posititon null");
//        }
//
//
//    }

}
