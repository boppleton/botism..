package gui;
import gui.menu.MainMenuBar;

import javax.swing.*;
import java.awt.*;

//
// main window frame
//
// try to keep logic code out of here
//

public class GUI extends JFrame {

    public GUI(String title) {
        super(title);
        getContentPane().setLayout(new BorderLayout());

        // use GUI.getInstance() to access the methods in this thread to update components
        gui = this;

        // starts top file bar
        updateMenuBar();

        // messages display
        statusToolbar();

        middleTabs();

        bottomLog();


    }

    private JTextArea logArea;
    private void bottomLog() {

        logArea = new JTextArea(5, 1);
        logArea.setEditable(false);

        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("log"));

        add(logScrollPane, BorderLayout.SOUTH);
    }

    public void log(String logMessage) {
        logArea.insert(logMessage + "\n", 0);
    }

    private void middleTabs() {

        JTabbedPane tabpane = new JTabbedPane();
        add(tabpane, BorderLayout.CENTER);


        tabpane.add("home", new HomePanel() );

        tabpane.add("scaled order", new ScaledOrderPanel());

        tabpane.add("limit chase", new JPanel());



    }

    // stuff to do when account changes
    public void onAccountChange() {

    }

    // top message bar, displays exchange + account + current message
    private JLabel toolbarStatusLabel = new JLabel();
    private void statusToolbar() {

        JToolBar toolbar = new JToolBar();

        toolbar.setFloatable(true); // drag away
        toolbar.setBorderPainted(true);
        toolbar.setRollover(false); // display borders around buttons
        toolbar.setBackground(new Color(5,5,10));

        toolbarStatusLabel = new JLabel();
        updateTopToolbarText("welcome");
        toolbar.add(toolbarStatusLabel);

        toolbar.add(Box.createHorizontalGlue());

        add(toolbar, BorderLayout.NORTH);

    }

    // ran after an account is added to update the list, or at program start
    public void updateMenuBar() {
        SwingUtilities.invokeLater(() -> {

            // remove last menu bar (if any)
            setJMenuBar(null);

            // build a new menu bar
            setJMenuBar(new MainMenuBar(this));

            //update frame
            revalidate();
        });
    }

    // hold and change the currently connected/active exchange (bitmex, 1broker, etc) and account (custom account name tied to API keypair).
    private String currentExchange = "disconnected";
    private String currentAccount = "no account";
    public String getCurrentExchange() { return currentExchange; }
    public String getCurrentAccount() { return currentAccount; }
    public void setCurrentExchangeAccount(String exchange, String account) {
        currentExchange = exchange;
        currentAccount = account;
    }

    // use this to access the gui methods
    private static GUI gui;
    public static GUI getInstance() {
        return gui;
    }

    //update message bar
    public void updateTopToolbarText(String s) { toolbarStatusLabel.setText("["+currentExchange+"] ["+currentAccount+"] > " + s); }


}
