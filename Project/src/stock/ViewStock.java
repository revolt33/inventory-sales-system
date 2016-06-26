package stock;
import fileStructure.*;
import java.awt.*;
import java.util.*;
import business.*;
import java.rmi.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class ViewStock {
    public static void display(JPanel jp1)
    {
        JPanel jp = new JPanel(new BorderLayout());
        JPanel north = new JPanel(new FlowLayout());
        north.setBackground(new Color(15, 0, 255));
        north.setOpaque(true);
        JLabel header = new JLabel("Add a Category");
        header.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        header.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.setForeground(Color.yellow);
        ChangePanel.parent = jp1;
        header.addMouseListener(new ChangePanel(jp, 1));
        north.add(header);
        jp.add(north, BorderLayout.NORTH);
        JPanel jp2 = new JPanel(new GridBagLayout());
        ArrayList<StockObject> stock = null;
        ListIterator<StockObject> it = null;
        int i = 0;
        try {
             stock = Visual.c.obj.fetch();
             i = stock.size();
             it = stock.listIterator();
        } catch (RemoteException ex) {
            AddCategory.exceptions("viewStock.txt", ex);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        Insets in = new Insets(40, 0, 40, 0);
        gbc.insets = in;
        boolean b = it.hasNext();
        int k = 0;
        while (b) {
            StockObject cat = it.next();
            JLabel category = new JLabel(cat.name);
            category.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            category.setForeground(new Color(140, 30, 140));
            JPanel ct = new JPanel(new FlowLayout());
            ct.add(category);
            DesignTable table = new DesignTable("Stock");
            try {
                table.arr = Visual.c.obj.fetchItem(cat.code);
            } catch (RemoteException ex) {
                AddCategory.exceptions("viewStock.txt", ex);
            }
            JTable addTable = new JTable(table);
            addTable.setPreferredScrollableViewportSize(new Dimension(700,100));
            addTable.setRowHeight(25);
            addTable.setDragEnabled(true);
            addTable.setShowGrid(false);
            addTable.setShowHorizontalLines(false);
            addTable.setShowVerticalLines(true);
            addTable.setSelectionForeground(Color.yellow);
            addTable.setSelectionBackground(new Color(50,50,255));
            addTable.setGridColor(new Color(170,255,40));
            JScrollPane scroll = new JScrollPane(addTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JPanel inter = new JPanel(new BorderLayout());
            FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 30, 5);
            JPanel bottom = new JPanel(flow);
            bottom.setBackground(new Color(100,0,250));
            bottom.setOpaque(b);
            Font f1 = new Font(Font.SERIF, Font.CENTER_BASELINE,15);
            Color color = new Color(240,250,0);
            JButton jb = new JButton("Edit");
            jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jb.addActionListener(new EditStockItem(jp, addTable, cat.code));
            JButton jb1 = new JButton("Delete");
            jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jb1.addActionListener(new DeleteItem(jp, table.arr, addTable, cat.code));
            JLabel remove = new JLabel("Remove Category");
            remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
            remove.addMouseListener(new RemoveCategory(cat.code, jp));
            remove.setFont(f1);
            remove.setForeground(color);
            bottom.add(jb);
            bottom.add(jb1);
            bottom.add(remove);
            inter.add(ct, BorderLayout.NORTH);
            inter.add(scroll, BorderLayout.CENTER);
            inter.add(bottom, BorderLayout.SOUTH);
            gbc.gridy = k;
            jp2.add(inter, gbc);
            k++;
            b = it.hasNext();
        }
        JScrollPane jsp = new JScrollPane(jp2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        LineBorder lb = new LineBorder(Color.RED, 3, true);
        jsp.setViewportBorder(lb);
        jp.add(jsp, BorderLayout.CENTER);
        jp1.add(jp, BorderLayout.CENTER);
    }
    
}