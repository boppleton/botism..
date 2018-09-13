package gui.menu;

import data.Accounts;
import exchange.bitmex.BitmexSetup;
import gui.GUI;
import org.knowm.xchange.Exchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainMenuBar extends JMenuBar {

    private JFrame frame;

    private JMenu account;


    public MainMenuBar(JFrame frame) {

        this.frame = frame;

        file();

        account();

        connections();


    }


    private void file() {

        JMenu file = new JMenu("file");
        add(file);

        JMenuItem settings = new JMenuItem("settings");
        file.add(settings);

    }


    private void account() {

        // main menu (account)
        account = new JMenu("account");
        add(account);

        addExchange("bitmex");

        addExchange("deribit");

        addExchange("1broker");

        addExchange("binance");

        addExchange("bitfinex");


    }

    private void addExchange(String name) {
        JMenu menu = new JMenu(name);
        JMenuItem addNew = new JMenuItem("add new");
        addNew.setName(name);
        menu.add(addNew);
        account.add(menu);

        // add current accounts from file
        String fileName = "accounts.txt";
        Object[] lines = null;
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {


            lines = stream.toArray();

            for (Object line : lines) {

                String accountLine = line.toString();

                if (accountLine.length() != 0) {

                    String exchange = accountLine.substring(0, accountLine.indexOf("<name>"));

                    if (exchange.contains(name)) {


                        String accountName = accountLine.substring(accountLine.indexOf("<name>") + 6, accountLine.indexOf("<key>"));

                        JMenuItem acctItem = new JMenuItem(accountName);
                        menu.add(acctItem);

                        acctItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                Thread t = new Thread(() -> {
                                    try {
                                        System.out.println(accountLine);
                                        Accounts.getInstance().connectToAccount(accountLine);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                });
                                t.start();

                            }
                        });

                    }


                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        addNew.addActionListener(e -> {
            System.out.println("add account > " + addNew.getName());


            AddAccountDialog addAccountDialog = new AddAccountDialog(addNew.getName(), frame);
            addAccountDialog.setVisible(true);

            if (addAccountDialog.isSucceeded()) {
                System.out.println("login siccess! " + addAccountDialog.isSucceeded());
            } else {
                System.out.println(addAccountDialog.isSucceeded());
            }
        });

    }




    private void connections() {
    }


}
