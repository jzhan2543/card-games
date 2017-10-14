import java.awt.Frame;
import java.awt.Panel;
import java.awt.*;
import javax.swing.*;

public class Cardgame extends Frame {

    public static void createAndShowGUI() {
        // Frame frame = new Frame();
        // Panel pnl = new Panel();          // Panel is a container
        // frame.add(pnl);
        // Button btn = new Button("Press"); // Button is a component
        // pnl.add(btn);                     // The Panel container adds a Button component
        // frame.setVisible(true);

        // Create the frame.
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel inside the fram.
        JPanel panel = new JPanel(new GridLayout(4,4,4,4));

        //Create components and put them in the frame.
        JLabel label = new JLabel("Card Game:");
        panel.add(label, BorderLayout.CENTER);
        Button btn = new Button("Press");
        btn.setBounds(20, 40, 20, 40);
        panel.add(btn);

        frame.setContentPane(panel);

        //sizes the frame so that all its contents are at or above their
        //preferred sizes
        frame.pack();
        frame.setMinimumSize(new Dimension(500,500));
        //frame.setSize(250, 100);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /**
         * Swing is not thread safe. All Swing components and related classes,
         * unless otherwise documented, must be accessed on the event
         * dispatching thread.
         */
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}