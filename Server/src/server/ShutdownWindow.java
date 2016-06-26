package server;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ShutdownWindow extends WindowAdapter{
    @Override
    public void windowClosing (WindowEvent we) {
        stopServer();
    }
    public void stopServer () {
        JDialog jd = new JDialog(StartupWindow.jf, "Alert", true);
        jd.setSize(400, 250);
        jd.setBackground(new Color(0,0,160));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 200;
        center.y -= 125;
        jd.setLocation(center);
        jd.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets in = new Insets(10, 10, 10, 0);
        gbc.gridx = 0;
        gbc.insets = in;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JLabel label = new JLabel("Enter the remaining time.");
        label.setForeground(Color.red);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        JSpinner min = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
        JLabel minText = new JLabel("Minute(s)");
        JSpinner sec = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        JLabel secText = new JLabel("Second(s)");
        jd.add(label, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        jd.add(min, gbc);
        gbc.gridx = 1;
        jd.add(minText, gbc);
        gbc.gridx = 2;
        jd.add(sec, gbc);
        gbc.gridx = 3;
        jd.add(secText, gbc);
        JButton set = new JButton("Set");
        set.addActionListener(new Timer(jd, min, sec));
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 2;
        jd.add(set, gbc);
        jd.setVisible(true);
    }
}