package frames;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ChangePassword extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent me) {
        JDialog jd = new JDialog(CreateFrame.jf, "Change Password", true);
        jd.setLayout(new GridBagLayout());
        jd.setSize(600, 300);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 300;
        center.y -= 150;
        jd.setLocation(center);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        Color color = new Color(200, 0, 200);
        Font f = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 18);
        JLabel old = new JLabel("Old Password:");
        old.setForeground(color);
        old.setFont(f);
        JLabel news = new JLabel("New Password:");
        news.setForeground(color);
        news.setFont(f);
        JLabel confirm = new JLabel("Confirm Password:");
        confirm.setForeground(color);
        confirm.setFont(f);
        JPasswordField oldPass = new JPasswordField(12);
        oldPass.addKeyListener(new CheckPassword(oldPass, 1, jd, null, null));
        JPasswordField newPass = new JPasswordField(12);
        JPasswordField confirmPass = new JPasswordField(12);
        JButton change = new JButton("Change");
        change.addActionListener(new SavePassword(jd, oldPass, newPass, confirmPass));
        confirmPass.addKeyListener(new CheckPassword(newPass, 2, jd, confirmPass, change));
        change.setEnabled(false);
        Insets in = new Insets(15, 20, 15, 20);
        gbc.insets = in;
        gbc.anchor = GridBagConstraints.EAST;
        jd.add(old, gbc);
        gbc.gridx = 1;
        jd.add(oldPass, gbc);
        gbc.gridy = 1;
        jd.add(newPass, gbc);
        gbc.gridx = 0;
        jd.add(news, gbc);
        gbc.gridy = 2;
        jd.add(confirm, gbc);
        gbc.gridx = 1;
        jd.add(confirmPass, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        jd.add(change, gbc);
        jd.setVisible(true);
    }
}