import data.Accounts;
import data.Orders;
import data.Positions;
import data.Websockets;
import exchange.bitmex.BitmexReceiver;
import gui.GUI;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceSlices;
import org.pushingpixels.substance.api.skin.GraphiteChalkSkin;

import javax.swing.*;

// welcome
//
// main class, entry point of program
//
// set things like size for the main window frame


public class Main {

    private static String title = "botism v0.2";

    private static int height = 800;
    private static int width = 600;

    public static void main(String[] args) {
        startGUI();
        startLists();

        startReceivers();
    }

    private static void startReceivers() {

        BitmexReceiver bitmexReceiver = new BitmexReceiver();
    }

    private static void startGUI() {

        // anything inside of this will run on the Event Dispatch thread, for the java swing UI
        SwingUtilities.invokeLater(() -> {

            // skin stuff
            SubstanceCortex.GlobalScope.setSkin(new GraphiteChalkSkin());
            SubstanceCortex.GlobalScope.setFocusKind(SubstanceSlices.FocusKind.NONE);
            JFrame.setDefaultLookAndFeelDecorated(true);

            // create the main UI frame object
            GUI gui = new GUI(title);

            // initial size
            gui.setSize(width, height);

            // center of screen
            gui.setLocationRelativeTo(null);

            // x button
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // make the main frame visible
            gui.setVisible(true);

        });
    }

    // access the list of active accounts by using Accounts.getInstance()
    private static void startLists() {
        Accounts accountsList = new Accounts();

        Websockets websockets = new Websockets();

        Orders orders = new Orders();

        Positions positions = new Positions();

    }


    private static void startWebsocketsList() {

    }
}
