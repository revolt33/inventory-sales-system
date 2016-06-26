package manage;
import fileStructure.*;
import stock.*;
import java.awt.*;
import business.*;
import java.rmi.*;
import java.util.*;
import javax.swing.*;

public class ViewUser {
    public static JPanel display() {
        JPanel parent = new JPanel(new BorderLayout());
        JPanel north = new JPanel(new FlowLayout());
        JLabel header = new JLabel("Add User");
        header.addMouseListener(new ChangePanel(parent, 1));
        header.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        header.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.setForeground(new Color(255, 255, 50));
        north.setBackground(new Color(70, 0, 255));
        north.setOpaque(true);
        north.add(header);
        parent.add(north, BorderLayout.NORTH);
        JPanel center = new JPanel(new GridBagLayout());
        ArrayList<User> arr = null;
        try {
            arr = Visual.c.obj.viewUser();
        } catch (RemoteException ex) {
            DeleteUser.exceptions("viewUser", ex);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        DesignTable table = new DesignTable("User");
        table.user = arr;
        JTable addTable = new JTable(table);
        addTable.setPreferredScrollableViewportSize(new Dimension(700, 200));
        addTable.setRowHeight(25);
        addTable.setShowGrid(false);
        addTable.setShowHorizontalLines(false);
        addTable.setShowVerticalLines(true);
        addTable.setSelectionForeground(Color.yellow);
        addTable.setSelectionBackground(new Color(50, 50, 255));
        addTable.setGridColor(new Color(170,255,40));
        JScrollPane jsp = new JScrollPane(addTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        center.add(jsp, gbc);
        JPanel del =  new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        del.setBackground(new Color(30, 100, 250));
        del.setOpaque(true);
        String[] num = new String[arr.size()];
        for(int a = 0; a<arr.size(); a++)
            num[a] = ""+(a+1)+"";
        JLabel select = new JLabel("Select a User");
        select.setForeground(Color.yellow);
        select.setFont(new Font(Font.DIALOG, Font.TYPE1_FONT, 20));
        JComboBox<String> jcb = new JComboBox<>(num);
        JButton delete = new JButton("Delete");
        delete.addActionListener(new DeleteUser(jcb, arr));
        del.add(select);
        del.add(jcb);
        del.add(delete);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        center.add(del, gbc);
        parent.add(center, BorderLayout.CENTER);
        return parent;
    }
}