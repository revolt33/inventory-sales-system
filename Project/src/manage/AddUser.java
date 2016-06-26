package manage;
import javax.swing.*;
import java.awt.*;
import item.ColorTitle;
import javax.swing.border.*;
public class AddUser {
    public static JPanel display()
    {
        JPanel parent = new JPanel(new BorderLayout());
        JPanel north = new JPanel(new FlowLayout());
        JLabel header = new JLabel("View User(s)");
        header.addMouseListener(new ChangePanel(parent, 2));
        header.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        header.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.setForeground(new Color(255, 255, 50));
        north.setBackground(new Color(70, 0, 255));
        north.setOpaque(true);
        north.add(header);
        parent.add(north, BorderLayout.NORTH);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        String s1[] = {"Manager", "Stock Employee", "Purchase Employee", "Sales Employee"};
        JPanel child = new JPanel(new GridBagLayout());
        LineBorder lb  = new LineBorder(Color.red, 3, true);
        new ColorTitle(lb, child, "Add a New User");
        Font f1  = new Font(Font.SERIF, Font.CENTER_BASELINE, 18);
        gbc.anchor = GridBagConstraints.EAST;
        JLabel name = new JLabel("Name:");
        name.setFont(f1);
        JLabel id = new JLabel("ID:");
        id.setFont(f1);
        JLabel type = new JLabel("Type:");
        type.setFont(f1);
        JLabel pwd = new JLabel("Password:");
        pwd.setFont(f1);
        JButton sub = new JButton("Add");
        JTextField tname = new JTextField(12);
        JTextField tid = new JTextField(12);
        JComboBox jtype = new JComboBox(s1);
        JTextField tpwd = new JTextField(12);
        sub.addActionListener(new StoreUser(tname, tid, jtype, tpwd));
        Insets in = new Insets(10, 20, 10, 20);
        gbc.insets = in;
        child.add(name,gbc);
        gbc.gridy = 1;
        child.add(id, gbc);
        gbc.gridy = 2;
        child.add(type, gbc);
        gbc.gridy = 3;
        child.add(pwd, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        child.add(tname, gbc);
        gbc.gridy = 1;
        child.add(tid, gbc);
        gbc.gridy = 2;
        child.add(jtype, gbc);
        gbc.gridy = 3;
        child.add(tpwd, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        child.add(sub, gbc);
        parent.add(child, BorderLayout.CENTER);
        return parent;
    }
}