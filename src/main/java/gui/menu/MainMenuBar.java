package gui.menu;

import data.Accounts;
import gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//
// setup the top menu file bar
//

public class MainMenuBar extends JMenuBar {

    // holds the main window frame
    private JFrame frame;

    private JMenu account;

    public MainMenuBar(JFrame frame) {

        this.frame = frame;

        file();

        account();

        connections();

        pair();


    }


    private void file() {

        JMenu file = new JMenu("file");
        add(file);

        JMenuItem settings = new JMenuItem("settings");
        file.add(settings);

    }


    // makes main account tab and fills saved accts
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

    // fills each exchange with accounts from accounts.txt
    private void addExchange(String name) {

        // main exchange name menu tab
        JMenu menu = new JMenu(name);
        account.add(menu);

        // add new tab, will be only thing in there if no accounts
        JMenuItem addNew = new JMenuItem("add new");
        addNew.setName(name);
        menu.add(addNew);


        // add current accounts from file
        String fileName = "accounts.txt";
        //
        // read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            // get all lines from accounts.txt into an array
            Object[] lines = stream.toArray();
            //                                                         account name v                      private key v
            // scroll through the lines, they are in format         bitmex<name>mexacct2<key>82fj20f^2f<sec>f20fjfff39f4.f34ffsaaa<end>
            //                                               exchange ^                  api key ^
            for (Object line : lines) {


                String accountLine = line.toString();

                if (accountLine.length() != 0) {

                    String exchange = accountLine.substring(0, accountLine.indexOf("<name>"));
                    if (exchange.contains(name)) {

                        String accountName = accountLine.substring(accountLine.indexOf("<name>") + 6, accountLine.indexOf("<key>"));

                        JMenuItem acctItem = new JMenuItem(accountName);
                        menu.add(acctItem);

                        // when you click on an account, connect
                        acctItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                Thread t = new Thread(() -> {
                                    try {

                                        System.out.println(accountLine);
                                        Accounts.getInstance().connectToAccount(accountLine);
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }

                                    GUI.getInstance().onAccountChange();

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


    private void pair() {




    }




    private void connections() {
    }


}
