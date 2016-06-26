package business;
import javax.swing.*;
import java.awt.*;
public class Verification extends JFrame
{
    public Verification() {
        super("Verification");
    }

    public void display() {
        this.setSize(400, 200);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowClose());
        ImageIcon ico = new ImageIcon("Icon.jpg");
        Image icon = ico.getImage();
        this.setIconImage(icon);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 200;
        center.y -= 100;
        this.setLocation(center);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel user = new JLabel("User name");
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(user, gbc);
        JTextField username = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(username, gbc);
        JLabel pass = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(pass, gbc);
        JPasswordField password = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(password, gbc);
        JButton sub = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JRootPane jrp = this.getRootPane();
        jrp.setDefaultButton(sub);
        sub.addActionListener(new Routing(this, username, password));
        this.add(sub, gbc);
        this.setVisible(true);
    }
}