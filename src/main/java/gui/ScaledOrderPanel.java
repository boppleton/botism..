package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScaledOrderPanel extends JPanel {

    private JPanel mainPanel;

    GridBagConstraints gbc = new GridBagConstraints();

    public ScaledOrderPanel() {

        setLayout(new GridBagLayout());

//        setBorder(BorderFactory.createTitledBorder("scaled order"));

        mainPanel = new JPanel(new GridBagLayout());
//        mainPanel.setBorder(BorderFactory.createTitledBorder("mainpanel"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(mainPanel, gbc);


        leftBuildPanel();

        rightPreviewPanel();

        // spacers
        JPanel bottomspacer = new JPanel();
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(bottomspacer, gbc);

        JPanel rightspacer = new JPanel();
        gbc.gridx++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(rightspacer, gbc);


    }

    private void rightPreviewPanel() {

        // right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("preview"));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        mainPanel.add(rightPanel, gbc);


        JPanel topPanel = new JPanel(new GridBagLayout());

        rightPanel.add(topPanel);


        // start button
        JButton startButton = new JButton("place orders");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2,5,2,5);
        topPanel.add(startButton, gbc);


        JLabel exchangeLabel = new JLabel("deribit");

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        topPanel.add(exchangeLabel, gbc);

        JLabel instrumentLabel = new JLabel("XBT/USD");

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        topPanel.add(instrumentLabel, gbc);


        JTextArea ordersArea = new JTextArea(16, 1);

        ordersArea.setText("1000 @ 1500\n1000 @ 1600\n1000 @ 1700\n1000 @ 1800\n1000 @ 1900\n1000 @ 1500\n1000 @ 1600\n1000 @ 1700\n1000 @ 1800\n1000 @ 1900\n1000 @ 1500\n1000 @ 1600\n1000 @ 1700\n1000 @ 1800\n1000 @ 1900\n1000 @ 1500\n1000 @ 1600\n1000 @ 1700\n1000 @ 1800\n1000 @ 1900\n1000 @ 1500\n1000 @ 1600\n1000 @ 1700\n1000 @ 1800\n1000 @ 1900\n1000 @ 1500\n1000 @ 1600\n1000 @ 1700\n1000 @ 1800\n1000 @ 1900\n");

        JScrollPane ordersScrollpane = new JScrollPane(ordersArea);

        rightPanel.add(ordersScrollpane);




    }

    private void leftBuildPanel() {

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("build"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(leftPanel, gbc);

        // buyorsell radios
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), "side"));

        JRadioButton bidsButton = new JRadioButton("buy");
        bidsButton.setSelected(true);
        buttonsPanel.add(bidsButton);
        buttonsPanel.add(Box.createHorizontalStrut(40));

        bidsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), "side"));
            }
        });

        buttonsPanel.add(Box.createHorizontalStrut(10));

        JRadioButton asksButton = new JRadioButton("sell");
        buttonsPanel.add(asksButton);

        asksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "side"));
            }
        });

        ButtonGroup buysellGroup = new ButtonGroup();
        buysellGroup.add(bidsButton);
        buysellGroup.add(asksButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        leftPanel.add(buttonsPanel, gbc);


        // total contracts
        JPanel totalContractsPanel = new JPanel(new BorderLayout());
        totalContractsPanel.setBorder(BorderFactory.createTitledBorder("total amount"));
        JSpinner totalContractsField = new JSpinner(new SpinnerNumberModel(5000, .001,10000000,1000));
        totalContractsPanel.add(totalContractsField, BorderLayout.CENTER);
        gbc.weightx = 1;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(totalContractsPanel, gbc);


        // # of orders
        JPanel numOrdersPanel = new JPanel(new BorderLayout());
        numOrdersPanel.setBorder(BorderFactory.createTitledBorder("# of orders"));
        JSpinner numOrdersSpinner = new JSpinner(new SpinnerNumberModel(10,2,200,1));
        numOrdersPanel.add(numOrdersSpinner, BorderLayout.CENTER);
        gbc.gridy++;
        leftPanel.add(numOrdersPanel, gbc);

        // upper price
        JPanel upperPricePanel = new JPanel(new BorderLayout());
        upperPricePanel.setBorder(BorderFactory.createTitledBorder("upper price"));
        JSpinner upperPriceSpinner = new JSpinner(new SpinnerNumberModel(2000,1,1000000,10));
        upperPricePanel.add(upperPriceSpinner, BorderLayout.CENTER);
        gbc.gridy++;
        leftPanel.add(upperPricePanel, gbc);

        // lower price
        JPanel lowerPricePanel = new JPanel(new BorderLayout());
        lowerPricePanel.setBorder(BorderFactory.createTitledBorder("lower price"));
        JSpinner lowerPriceSpinner = new JSpinner(new SpinnerNumberModel(1000,1,1000000,10));
        lowerPricePanel.add(lowerPriceSpinner, BorderLayout.CENTER);
        gbc.gridy++;
        leftPanel.add(lowerPricePanel, gbc);

        // distribution
        JPanel distributionPanel = new JPanel(new GridBagLayout());
        distributionPanel.setBorder(BorderFactory.createTitledBorder("distribution"));

        JComboBox<String> distributionCombo = new JComboBox<>();
        distributionCombo.addItem("flat");
        distributionCombo.addItem("up");
        distributionCombo.addItem("down");
        distributionCombo.addItem("martingale");
        distributionCombo.addItem("random");

        distributionCombo.setSelectedItem("up");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        distributionPanel.add(distributionCombo, gbc);



        JSlider weightSlider = new JSlider(1,10,5);
        weightSlider.setPreferredSize(new Dimension(150, 30));

        JPanel sliderPane = new JPanel(new GridBagLayout());

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        sliderPane.add(weightSlider, gbc);

        gbc.gridy++;
        distributionPanel.add(sliderPane, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        leftPanel.add(distributionPanel, gbc);

    }


}
