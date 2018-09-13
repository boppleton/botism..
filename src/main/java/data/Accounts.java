package data;

import exchange.WebsocketClient;
import exchange.binance.BinanceAPI;
import exchange.bitmex.BitmexSetup;
import exchange.bitmex.BitmexWebsocketClient;
import gui.GUI;
import gui.HomePanel;
import org.knowm.xchange.Exchange;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Accounts {

    private static Accounts accounts;

    private static ArrayList<Exchange> acountList = new ArrayList<>();

    public void connectToAccount(String accountLine) throws IOException, URISyntaxException, InterruptedException {

        System.out.println("connect to " + accountLine);

        String exchange = accountLine.substring(0, accountLine.indexOf("<name>"));
        String accountName = accountLine.substring(accountLine.indexOf("<name>") + 6, accountLine.indexOf("<key>"));
        String key = accountLine.substring(accountLine.indexOf("<key>") + 5, accountLine.indexOf("<sec>"));
        String sec = accountLine.substring(accountLine.indexOf("<sec>") + 5, accountLine.indexOf("<end>"));

        // bitmex ///////////////////////////////////////////////////////
        if (exchange.contains("bitmex")) {

            System.out.println("connecting to bitmex account " + accountName + ", key-" + key);

            GUI.getInstance().updateTopToolbarText("connecting to " + exchange + " account (" + accountName + ")..");

            GUI.getInstance().log("connecting to REST.. " + (exchange));
            Exchange bitmexExchange = new BitmexSetup().createExchange(key, sec);
            addAccount(bitmexExchange);


            try {
                //check if account connects
                GUI.getInstance().log("getting acct info..");
                String acctInfo = bitmexExchange.getAccountService().getAccountInfo().toString();
                GUI.getInstance().log(acctInfo);

                GUI.getInstance().setCurrentExchangeAccount(exchange, accountName);
                GUI.getInstance().updateTopToolbarText("ready");
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getMessage().contains("401")) {
                    GUI.getInstance().updateTopToolbarText("failed connection to bitmex (" + accountName + ") - invalid/unauthorized keys");

                }
            }

            // connect to websocket
            boolean connectedAlready = false;
            System.out.println(Websockets.getInstance().getWebsocketList());
            for (WebsocketClient ws : Websockets.getInstance().getWebsocketList()) {
                if (ws.getAccountname().contains(accountName) && ws.getExchange().contains(exchange)) {
                    connectedAlready = ws.isOpen();
                }
            }

            if (!connectedAlready) {
                GUI.getInstance().log("connecting to websocket - " + exchange + ", " + accountName);
                Websockets.getInstance().add(BitmexWebsocketClient.coonectBitmexWebsocket(accountName, key, sec));
            } else {
                GUI.getInstance().log("websocket already open for: " + exchange + ", " + accountName);
            }





        } else if (exchange.contains("binance")) {
            System.out.println("connecting to " + exchange + " account " + accountName + ", key-" + key);

            GUI.getInstance().updateTopToolbarText("connecting to " + exchange + " account (" + accountName + ")..");

            Exchange binanceExchange = BinanceAPI.connect(key, sec);
            addAccount(binanceExchange);

            try {
                //check if account connects
                String acctInfo = binanceExchange.getAccountService().getAccountInfo().toString();

                GUI.getInstance().updateTopToolbarText("ready");
                GUI.getInstance().setCurrentExchangeAccount(exchange, accountName);
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getMessage().contains("401") || e.getMessage().contains("2014")) {
                    GUI.getInstance().updateTopToolbarText("failed connection to binance (" + accountName + ") - invalid/unauthorized keys");

                } else {
                    GUI.getInstance().updateTopToolbarText("failed connection to binance (" + accountName + ")   :<");

                }
            }
        }


        // update gui stuff
        HomePanel.setExchangeAccountName();


    }

    public void addAccount(Exchange acct) {
        acountList.add(acct);
    }

    public Accounts() {
        accounts = this;
    }

    public static Accounts getInstance() {
        return accounts;
    }



}
