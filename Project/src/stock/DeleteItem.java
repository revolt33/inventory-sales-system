package stock;
import java.awt.event.*;
import business.Visual;
import fileStructure.*;
import java.util.*;
import javax.swing.*;
import java.rmi.RemoteException;
public class DeleteItem implements ActionListener{
    String cat;
    ArrayList<StockItem> arr;
    JTable table;
    JPanel jp;
    public DeleteItem(JPanel jp,ArrayList<StockItem> arr, JTable table,String cat) {
        this.cat = cat;
        this.table = table;
        this.arr = arr;
        this.jp = jp;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (table.getSelectedRow() != -1) {
            if (arr.size() != 0) {
                String codes = arr.get(table.getSelectedRow()).code;

                try {
                    Visual.c.obj.deleteItem(cat, codes);
                } catch (RemoteException ex) {
                    AddCategory.exceptions("deleteItem.txt", ex);
                }
                new ChangePanel(jp, 2).change();
            }
        }
    }
}