package server;
import javax.swing.*;
import java.net.*;
import java.awt.*;
public class StartupWindow extends JFrame{
    static JFrame jf;
    public static JLabel no;
    public StartupWindow() {
        super("Server");
        jf = this;
    }
    public void display() {
        this.setSize(400,200);
        //this.setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 200;
        center.y -= 100;
        this.setLocation(center);
        ImageIcon ico = new ImageIcon("Icon.png");
        Image icon = ico.getImage();
        this.setIconImage(icon);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JButton start = new JButton("Start Server");
        start.addActionListener(new StartServer(start, 1));
        GridBagConstraints gbc =  new GridBagConstraints();
        Insets in = new Insets(15, 15, 15, 15);
        JLabel label = new JLabel("No. of Connected Users:");
        no = new JLabel("0");
        gbc.insets = in;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel ip = null;
        String ips = null;
        try {
            ip = new JLabel(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        this.add(label, gbc);
        gbc.gridx = 1;
        this.add(no, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel ser = new JLabel("Server's IP:");
        this.add(ser, gbc);
        gbc.gridx = 1;
        this.add(ip, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        this.add(start, gbc);
        JButton stop = new JButton("Stop Server");
        stop.addActionListener(new StartServer(stop, 2));
        gbc.gridx = 1;
        this.addWindowListener(new ShutdownWindow());
        this.add(stop, gbc);
        this.setVisible(true);
    }
}