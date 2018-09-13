package data;

import exchange.bitmex.BitmexSetup;
import gui.GUI;
import org.knowm.xchange.Exchange;

import java.io.IOException;
import java.util.ArrayList;

public class Accounts {

    private static Accounts accounts;

    private static ArrayList<Exchange> acountList = new ArrayList<>();



    public void addAccount(Exchange acct) {
        acountList.add(acct);
    }

    public Accounts() {
        accounts = this;
    }

    public static Accounts getInstance() {
        return accounts;
    }


    public void connectToAccount(String accountLine) throws IOException {

        System.out.println("connect to " + accountLine);

        String exchange = accountLine.substring(0, accountLine.indexOf("<name>"));
        String accountName = accountLine.substring(accountLine.indexOf("<name>") + 6, accountLine.indexOf("<key>"));
        String key = accountLine.substring(accountLine.indexOf("<key>") + 5, accountLine.indexOf("<sec>"));
        String sec = accountLine.substring(accountLine.indexOf("<sec>") + 5, accountLine.indexOf("<end>"));

        if (exchange.contains("bitmex")) {

            System.out.println("connecting to bitmex account " + accountName + ", key-" + key);


            GUI.getInstance().updateTopToolbarText("connecting to " + exchange + " account (" + accountName + ")..");

            Exchange bitmexExchange = new BitmexSetup().createExchange(key, sec);
            addAccount(bitmexExchange);


            try {
                //check if account connects
                String acctInfo = bitmexExchange.getAccountService().getAccountInfo().toString();

                GUI.getInstance().updateTopToolbarText("% [" + exchange + "] (" + accountName + ") |> ready");
                GUI.getInstance().setCurrentExchangeAccount(exchange, accountName);
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getMessage().contains("401")) {
                    GUI.getInstance().updateTopToolbarText("failed connection to bitmex (" + accountName + ") - invalid/unauthorized keys");

                }
            }


        }


    }
}
