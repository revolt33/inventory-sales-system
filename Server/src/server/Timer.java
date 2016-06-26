package server;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Timer extends JDialog implements ActionListener{
    JDialog jd;
    JSpinner[] jsp = new JSpinner[2];
    Timer(JDialog jd, JSpinner... sp) {
        super(StartupWindow.jf, "Timer", true);
        this.jd = jd;
        this.jsp[0] = sp[0];
        this.jsp[1] = sp[1];
    }
    @Override
    public void actionPerformed (ActionEvent ae) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        this.setSize(200, 100);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 100;
        center.y -= 50;
        this.setLocation(center);
        JLabel label = new JLabel();
        this.add(label);
        int min = (int)jsp[0].getValue();
        int sec = (int)jsp[1].getValue();
        jd.dispose();
        Thread t1 = new Terminator(label, min, sec);
        t1.start();
        this.setVisible(true);
    }
}
