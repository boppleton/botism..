package gui.menu;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddAccountDialog extends JDialog {



    private boolean succeeded;

    public AddAccountDialog(String name, JFrame frame) {
        super(frame, "new " + name + " account", true);

        //start main panel
        JPanel panel = new JPanel(new GridBagLayout());
        add(panel);
        GridBagConstraints gbc = new GridBagConstraints();

        // acct name
        JPanel namePanel = new JPanel(new BorderLayout());
        JTextField nameField = new JTextField("acct #1");
        namePanel.add(nameField, BorderLayout.CENTER);
        namePanel.setBorder(BorderFactory.createTitledBorder("account name"));
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,10,5,10);
        gbc.ipadx = 5;
        gbc.ipady = 5;
        panel.add(namePanel, gbc);

        // key
        JPanel keyPanel = new JPanel(new BorderLayout());
        JTextField keyField = new JTextField();
        keyPanel.add(keyField);
        keyPanel.setBorder(BorderFactory.createTitledBorder("API key"));
        gbc.gridy = 1;
        panel.add(keyPanel, gbc);

        // secret
        JPanel secretPanel = new JPanel(new BorderLayout());
        JTextField secretField = new JTextField();
        secretPanel.add(secretField);
        secretPanel.setBorder(BorderFactory.createTitledBorder("API secret"));
        gbc.gridy = 2;
        panel.add(secretPanel, gbc);

        //buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));


        JButton btnLogin = new JButton("add");
        JButton btnCancel = new JButton("cancel");

        buttonsPanel.add(btnLogin);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        buttonsPanel.add(btnCancel);

        gbc.gridy = 3;
        panel.add(buttonsPanel, gbc);

        btnLogin.addActionListener(e -> {
            if (keyField.getText().length() > 0 && secretField.getText().length() > 0) { //test fields here
                JOptionPane.showMessageDialog(AddAccountDialog.this,
                        "added " + nameField.getText(),
                        "success",
                        JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;

                try {
                    addAccountToFile(name, nameField.getText(), keyField.getText(), secretField.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(AddAccountDialog.this,
                        "error connecting: errormessagehere",
                        "fail",
                        JOptionPane.ERROR_MESSAGE);
                // reset username and password
                nameField.setText("");
                succeeded = false;

            }
        });

        btnCancel.addActionListener(e -> dispose());


        pack();
        setResizable(false);
        setLocationRelativeTo(frame);

    }

    private void addAccountToFile(String exchange, String name, String key, String sec) throws IOException {

        String str = exchange + "<name>" + name + "<key>" + key + "<sec>" + sec + "<end>";
        BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
        writer.append(str);
        writer.append('\n');

        writer.close();

        GUI.getInstance().updateMenuBar();

    }


    public boolean isSucceeded() {
        return succeeded;
    }

}
