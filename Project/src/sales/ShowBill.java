package sales;
import business.Visual;
import fileStructure.*;
import frames.CreateFrame;
import java.awt.*;
import item.*;
import javax.swing.*;
import java.util.*;
import java.rmi.*;
public class ShowBill {

    JSpinner[] qty;
    JComboBox[] cat;
    JComboBox[] item;
    JPanel jp;
    int k;
    Object i;
    ArrayList<StockObject> al;
    JSpinner jsp;
    String s[];
    String s1[] = {"Select an Item"};
    JTextField itemName[];
    JTextField catName[];

    public ShowBill(JPanel jp1, JSpinner jsp1) {
        jp = jp1;
        jsp = jsp1;
    }

    public void show() {
        jp.removeAll();
        jp.invalidate();
        jp.validate();
        CreateFrame.jf.invalidate();
        CreateFrame.jf.validate();
        i = jsp.getValue();
        k = (int) i;
        if (k > 0) {
            try {
                al = Visual.c.obj.fetch();
            } catch (RemoteException ex) {
                Debit.exceptions("showBill.txt", ex);
            }
            s = new String[al.size() + 1];
            s[0] = "Select a Category";
            for (int a = 1; a <= al.size(); a++) {
                s[a] = al.get(a - 1).code;
            }

            qty = new JSpinner[k];
            cat = new JComboBox[k];
            item = new JComboBox[k];
            itemName = new JTextField[k];
            catName = new JTextField[k];
            Font f = new Font(Font.SERIF, Font.BOLD, 20);
            JLabel category = new JLabel("Category Code");
            category.setFont(f);
            category.setForeground(Color.blue);
            JLabel catName1 = new JLabel("Category Name");
            catName1.setForeground(Color.BLUE);
            catName1.setFont(f);
            JLabel item1 = new JLabel("Item Code");
            item1.setFont(f);
            item1.setForeground(Color.blue);
            JLabel item2 = new JLabel("Item Name");
            item2.setFont(f);
            item2.setForeground(Color.blue);
            JLabel quantity = new JLabel("Quantity");
            quantity.setFont(f);
            quantity.setForeground(Color.BLUE);
            GridBagConstraints gbc = new GridBagConstraints();
            Insets in = new Insets(20, 20, 20, 20);
            gbc.insets = in;
            gbc.gridx = 0;
            gbc.gridy = 0;
            jp.add(category, gbc);
            gbc.gridx = 1;
            jp.add(catName1, gbc);
            gbc.gridx = 2;
            jp.add(item1, gbc);
            gbc.gridx = 3;
            jp.add(item2, gbc);
            gbc.gridx = 4;
            jp.add(quantity, gbc);
            int j;
            for (j = 0; j < k; j++) {
                gbc.gridy = j + 1;
                cat[j] = new JComboBox(s);
                item[j] = new JComboBox(s1);
                catName[j] = new JTextField(12);
                catName[j].setEditable(false);
                itemName[j] = new JTextField(12);
                itemName[j].setEditable(false);
                UpdateItemName up = new UpdateItemName(itemName[j], item[j]);
                cat[j].addActionListener(new UpdateItem(cat[j], item[j], al, up));
                cat[j].addActionListener(new UpdateCategory(catName[j], al, cat[j]));
                item[j].addActionListener(up);
                qty[j] = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
                gbc.gridx = 0;
                jp.add(cat[j], gbc);
                gbc.gridx = 1;
                jp.add(catName[j], gbc);
                gbc.gridx = 2;
                jp.add(item[j], gbc);
                gbc.gridx = 3;
                jp.add(itemName[j], gbc);
                gbc.gridx = 4;
                jp.add(qty[j], gbc);
            }
            JButton jb = new JButton("Submit");
            jb.addActionListener(new Payment(k, qty, cat, item, catName, itemName));
            gbc.gridx = 0;
            gbc.gridy = j + 1;
            gbc.gridwidth = 5;
            jp.add(jb, gbc);
            jp.updateUI();
            CreateFrame.jf.invalidate();
            CreateFrame.jf.validate();
        }
    }
}