package frames;
import business.Visual;
import java.awt.BorderLayout;
import fileStructure.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.rmi.RemoteException;
import javax.swing.*;
public class ViewTransaction extends MouseAdapter{
    @Override
    public void mouseClicked (MouseEvent me) {
        JDialog jd = new JDialog(CreateFrame.jf, "Transactions", true);
        jd.setSize(900, 600);
        jd.setLayout(new BorderLayout());
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= 450;
        center.y -= 300;
        Font f = new Font(Font.SANS_SERIF, Font.ITALIC|Font.BOLD, 15);
        Color color = new Color(255, 255, 20);
        ListIterator it = null;
        int size = 0;
        HashMap<String, String> map = new HashMap<>();
        UpdateTransaction.map = map;
        try {
            size = Visual.c.obj.getIds().size();
            it = Visual.c.obj.getIds().listIterator();
        } catch (RemoteException ex) {
            AddPanel.exceptions("viewTransactions.txt", ex);
        }
        String[] ids = new String[size];
        int i =0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                map.put(str, Visual.c.obj.name(str));
            } catch (RemoteException ex) {
                AddPanel.exceptions("viewTransactions.txt", ex);
            }
            ids[i++] = str;
        }
        JPanel north = new JPanel(new GridBagLayout());
        String[] years = null, months = null, days = null;
        try {
            years = Visual.c.obj.directory("Transactions");
        } catch (RemoteException ex) {
            AddPanel.exceptions("viewTransactions.txt", ex);
        }
        JComboBox<String> id = new JComboBox<>(ids);
        id.addActionListener(new UpdateTransaction(north, 0));
        JTextField name = new JTextField(10);
        name.setText(map.get((String)id.getSelectedItem()));
        name.setEditable(false);
        JComboBox<String> year = new JComboBox<>(years);
        try {
            months = Visual.c.obj.directory("Transactions\\" + year.getSelectedItem());
        } catch (RemoteException ex) {
            AddPanel.exceptions("viewTransactions.txt", ex);
        }
        JComboBox<String> month = new JComboBox<>(months);
        year.addActionListener(new UpdateTransaction(north, 1));
        month.addActionListener(new UpdateTransaction(north, 2));
        try {
            days = Visual.c.obj.directory("Transactions\\"+ year.getSelectedItem() +"\\"+ month.getSelectedItem());
        } catch (RemoteException ex) {
            AddPanel.exceptions("viewTransactions.txt", ex);
        }
        JComboBox<String> day = new JComboBox<>(days);
        JLabel idLabel = new JLabel("ID");
        idLabel.setForeground(color);
        idLabel.setFont(f);
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(color);
        nameLabel.setFont(f);
        JLabel yearLabel = new JLabel("Year");
        yearLabel.setForeground(color);
        yearLabel.setFont(f);
        JLabel monthLabel = new JLabel("Month");
        monthLabel.setForeground(color);
        monthLabel.setFont(f);
        JLabel dayLabel = new JLabel("Day");
        dayLabel.setForeground(color);
        dayLabel.setFont(f);
        GridBagConstraints gbc = new GridBagConstraints();
        Insets in = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = in;
        north.add(idLabel, gbc);
        gbc.gridx = 1;
        north.add(nameLabel, gbc);
        gbc.gridx = 2;
        north.add(yearLabel, gbc);
        gbc.gridx = 3;
        north.add(monthLabel, gbc);
        gbc.gridx = 4;
        north.add(dayLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        north.add(id, gbc);
        gbc.gridx = 1;
        north.add(name, gbc);
        gbc.gridx = 2;
        north.add(year, gbc);
        gbc.gridx = 3;
        north.add(month, gbc);
        gbc.gridx = 4;
        north.add(day, gbc);
        JButton view = new JButton("View");
        JPanel centerPanel = new JPanel(new GridBagLayout());
        view.addActionListener(new ShowTransactions(north, centerPanel));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        north.add(view, gbc);
        JLabel label = new JLabel("No Transactions to display!");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(label, gbc);
        north.setBackground(new Color(50, 100, 255));
        north.setOpaque(true);
        jd.add(north, BorderLayout.NORTH);
        jd.add(centerPanel, BorderLayout.CENTER);
        jd.setLocation(center);
        jd.setVisible(true);
    }
}