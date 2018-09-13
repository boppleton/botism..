package gui;

import gui.menu.MainMenuBar;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private static GUI gui;

    private String currentExchange;
    private String currentAccount;

    public GUI(String title) {
        super(title);
        gui = this;
        getContentPane().setLayout(new BorderLayout());

        updateMenuBar();

        statusToolbar();


    }

    JLabel toolbarStatusLabel = new JLabel();

    private void statusToolbar() {

        JToolBar toolbar = new JToolBar();

        toolbar.setFloatable(false);
        toolbar.setBorderPainted(true);
        toolbar.setRollover(true);
        toolbar.setBackground(new Color(5,5,10));

        toolbarStatusLabel = new JLabel("> disconnected");
        toolbar.add(toolbarStatusLabel);

        toolbar.add(Box.createHorizontalGlue());

        add(toolbar, BorderLayout.NORTH);

    }

    public static GUI getInstance() {
        return gui;
    }

    public void updateMenuBar() {

        SwingUtilities.invokeLater(() -> {

            setJMenuBar(null);

            setJMenuBar(new MainMenuBar(this));

            revalidate();

        });


    }

    public void updateTopToolbarText(String s) {
        toolbarStatusLabel.setText(s);
    }

    public void setCurrentExchangeAccount(String exchange, String account) {

        currentExchange = exchange;
        currentAccount = account;

        System.out.println("exchange now " + exchange + ", account " + account);

    }

}
