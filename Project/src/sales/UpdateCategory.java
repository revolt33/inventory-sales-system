package sales;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import fileStructure.*;
public class UpdateCategory implements ActionListener{
    JTextField field;
    ArrayList<StockObject> stock;
    JComboBox<String> com;
    public UpdateCategory (JTextField field, ArrayList<StockObject> stock, JComboBox<String> com) {
        this.field = field;
        this.stock = stock;
        this.com = com;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String temp = (String) com.getSelectedItem();

        if (!temp.equals("Select a Category")) {
            String str;
            int i = com.getSelectedIndex();
            str = ((StockObject) stock.get(i-1)).name;
            field.setText(str);

        }
    }
}
