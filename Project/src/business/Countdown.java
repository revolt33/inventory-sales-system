package business;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.*;

public class Countdown extends Thread{
    int con;
    @Override
    public void run() {
       count(con); 
    }
    void count (int con) {
        JFrame jf = new JFrame("Alert");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 250;
        jf.setLocation(center.x, 50);
        jf.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        String str = "";
        JLabel hurry = new JLabel("<html><body><h1>Hurry up Server is going to shutdown.</h1><br /><h3>Remaining Time: "+str+"</h3></body></html>");
        jf.add(hurry);
        jf.setSize(500, 200);
        jf.setVisible(true);
        for(int i = con; i >= 0; i--) {
            str = ""+i;
            hurry.setText("<html><body><h1>Hurry up Server is going to shutdown.</h1><br /><h3>Remaining Time: "+str+" sec(s)</h3></body></html>");
            if(i == 0 )
                System.exit(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Business.exceptions("countdown.txt", ex);
            }
        }
    }
}