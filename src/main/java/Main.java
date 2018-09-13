import gui.GUI;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceSlices;
import org.pushingpixels.substance.api.skin.GraphiteChalkSkin;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        startGUI();

    }

    private static void startGUI() {

        SwingUtilities.invokeLater(() -> {

            // skin
            SubstanceCortex.GlobalScope.setSkin(new GraphiteChalkSkin());
            SubstanceCortex.GlobalScope.setFocusKind(SubstanceSlices.FocusKind.NONE);
            JFrame.setDefaultLookAndFeelDecorated(true);

            // create the main frame
            GUI gui = null;

            gui = new GUI("botism v0.2");


            // initial size
            gui.setSize(600, 800);

            // center of screen
            gui.setLocationRelativeTo(null);

            // x button
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // make the main frame visible
            gui.setVisible(true);

        });
    }
}
