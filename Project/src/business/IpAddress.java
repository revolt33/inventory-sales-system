package business;
import javax.swing.*;
import java.awt.*;
public class IpAddress extends JFrame{
    Connection in;
    public static IpAddress obj;
    public IpAddress(Connection in) {
        super("Connection");
        this.in = in;
    }
    public void display() {
        this.setSize(400,200);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 200;
        center.y -= 100;
        this.setLocation(center);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel enter = new JLabel("IP Address of Server:");
        enter.setForeground(new Color(255,50,50));
        enter.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 17));
        JTextField ip = new JTextField(13);
        JButton connect = new JButton("Connect");
        JRootPane jrp =  this.getRootPane();
	jrp.setDefaultButton(connect);
        connect.addActionListener(new Connect(ip, this));
        GridBagConstraints gbc = new GridBagConstraints();
        Insets in = new Insets(10, 15, 10, 15);
        gbc.insets = in;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(enter, gbc);
        gbc.gridx = 1;
        this.add(ip, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(connect, gbc);
        this.setVisible(true);
    }
}