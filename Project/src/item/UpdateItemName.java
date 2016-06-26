package item;
import fileStructure.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class UpdateItemName implements ActionListener {

    public JTextField jtf;
    public JComboBox jcb;
    public ArrayList<StockItem> item;

    public UpdateItemName(JTextField jtf, JComboBox jcb) {
        this.jtf = jtf;
        this.jcb = jcb;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String temp = (String) jcb.getSelectedItem();
        if (temp != null) {
            if (item != null && !temp.equals("Select an Item")) {
                String str;
                int i = jcb.getSelectedIndex();
                str = ((StockItem) item.get(i)).name;
                jtf.setText(str);
            }
        }

    }
}