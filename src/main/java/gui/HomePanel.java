package gui;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    GridBagConstraints gbc = new GridBagConstraints();

    private static JPanel openPositionsPanel;
    private static JPanel openOrdersPanel;

    public HomePanel() {

        setBorder(BorderFactory.createSoftBevelBorder(1));

        setLayout(new GridBagLayout());


        //spacer
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        add(new JPanel(), gbc);

        openPositionsPanel();

        openOrdersPanel();



        setExchangeAccountName();

    }

    private void openPositionsPanel() {

        openPositionsPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        add(openPositionsPanel, gbc);

//        openPositionsPanel.add(new JLabel("position 1"));



    }

    private void openOrdersPanel() {

        openOrdersPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        add(openOrdersPanel, gbc);

        openOrdersPanel.add(new JLabel("ordeer 1"));

    }

    public static void setExchangeAccountName() {

        // anything inside of this will run on the Event Dispatch thread, for the java swing UI
        SwingUtilities.invokeLater(() -> {


            openPositionsPanel.setBorder(BorderFactory.createTitledBorder(GUI.getInstance().getCurrentExchange() + " positions - " + GUI.getInstance().getCurrentAccount()));

            openOrdersPanel.setBorder(BorderFactory.createTitledBorder(GUI.getInstance().getCurrentExchange() + " orders - " + GUI.getInstance().getCurrentAccount()));



        });


    }

}
