package stock;
import fileStructure.*;
import business.*;
import frames.*;
import java.awt.*;
import java.util.*;
import java.rmi.*;
import java.awt.event.*;
import javax.swing.*;
public class EditStockItem implements ActionListener{
    JTable table;
    String cat;
    JPanel jp;
    public EditStockItem(JPanel jp, JTable table , String cat){
        this.table = table;
        this.cat = cat;
        this.jp = jp;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        int index = table.getSelectedRow();
        ArrayList<StockItem> arr = null;
        if (index != -1) {
            try {
                arr = Visual.c.obj.fetchItem(cat);
            } catch (RemoteException ex) {
                AddCategory.exceptions("editStockItem.txt", ex);
            }
            String names = arr.get(index).name;
            String codes = arr.get(index).code;
            int qtys = arr.get(index).qty;
            double prices = arr.get(index).price;
            JDialog jd = new JDialog(CreateFrame.jf, "Stock Editor", true);
            jd.setSize(600, 200);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point center = ge.getCenterPoint();
            center.x -= 300;
            center.y -= 100;
            jd.setLocation(center);
            JTextField code = new JTextField(codes, 10);
            code.setEditable(false);
            JTextField name = new JTextField(names, 13);
            JSpinner qty = new JSpinner(new SpinnerNumberModel(qtys, 0, 100000, 1));
            JSpinner price = new JSpinner(new SpinnerNumberModel(prices, 0.0, 150000.0, 0.1));
            JButton save = new JButton("Save Changes");
            save.addActionListener(new SaveEdit(jp, cat, codes, jd, name, qty, price));
            GridBagConstraints gbc = new GridBagConstraints();
            jd.setLayout(new GridBagLayout());
            Insets in = new Insets(10, 10, 10, 10);
            gbc.insets = in;
            gbc.gridx = 0;
            gbc.gridy = 0;
            jd.add(code, gbc);
            gbc.gridx = 1;
            jd.add(name, gbc);
            gbc.gridx = 2;
            jd.add(qty, gbc);
            gbc.gridx = 3;
            jd.add(price, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 4;
            jd.add(save, gbc);
            jd.setVisible(true);
        }
    }    
}