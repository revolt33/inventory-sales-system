package stock;
import item.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
public class Category {
    public static JPanel display()
    {
        JPanel parent = new JPanel(new BorderLayout());
        JPanel north = new JPanel(new FlowLayout());
        north.setBackground(new Color(15, 0, 255));
        north.setOpaque(true);
        JLabel header = new JLabel("View Stock");
        header.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        header.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.setForeground(Color.yellow);
        header.addMouseListener(new ChangePanel(parent, 2));
        north.add(header);
        parent.add(north, BorderLayout.NORTH);
        JPanel center = new JPanel(new GridBagLayout());
        JPanel centerChild = new JPanel(new GridBagLayout());
        LineBorder lb = new LineBorder(Color.red, 3, true);
        new ColorTitle(lb, centerChild, "Add a Category");
        Font f = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15);
        JLabel name = new JLabel("Category Name:");
        name.setFont(f);
        JLabel code = new JLabel("Category Code:");
        code.setFont(f);
        JTextField tname = new JTextField(10);
        JTextField tcode = new JTextField(10);
        JButton sub = new JButton("Add");
        sub.addActionListener(new AddCategory(centerChild));
        Insets in = new Insets(10,20,10,20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = in;
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerChild.add(name, gbc);
        gbc.gridy  = 1;
        centerChild.add(code, gbc);
        gbc.gridx = 1;
        centerChild.add(tcode, gbc);
        gbc.gridy = 0;
        centerChild.add(tname, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerChild.add(sub, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        center.add(centerChild, gbc);
        parent.add(center, BorderLayout.CENTER);
        return parent;
    }
    
}
