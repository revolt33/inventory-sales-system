package stock;
import java.util.*;
import fileStructure.*;
import javax.swing.table.*;
public class DesignTable extends AbstractTableModel{
    String mode;
    ArrayList<StockItem> arr;
    public ArrayList<User> user;
    public DesignTable(String mode){
        this.mode = mode;
    }
    @Override
    public int getColumnCount() {
        int i = 0;
        if(mode.equals("Stock"))
            i = 5;
        else if(mode.equals("User"))
            i = 4;
        return i;
    }
    @Override
    public int getRowCount() {
        int i = 0;
        if(mode.equals("Stock"))
            i = arr.size();
        else if(mode.equals("User"))
            i = user.size();
        return i;
    }
    @Override
    public Object getValueAt(int row, int col) {
        String str;
        int qty;
        double price;
        if (col == 0) {
            return row+1;
        }
        if (mode.equals("Stock")) {
            if (col == 1) {
                str = arr.get(row).name;
                return str;
            } else if (col == 2) {
                str = arr.get(row).code;
                return str;
            } else if (col == 3) {
                qty = arr.get(row).qty;
                return qty;
            } else if (col == 4) {
                price = arr.get(row).price;
                return price;
            }
        } else if (mode.equals("User")) {
            if (col == 1) {
                return user.get(row).id;
            } else if (col == 2) {
                return user.get(row).name;
            } else if (col == 3) {
                return user.get(row).type;
            }
        }
        return null;
    }
    @Override
    public String getColumnName(int col) {
        if (mode.equals("Stock")) {
            if (col == 0) {
                return "Serial No.";
            } else if (col == 1) {
                return "Item Name";
            } else if (col == 2) {
                return "Item Code";
            } else if (col == 3) {
                return "Quantity";
            } else if (col == 4) {
                return "Price";
            }
        }
        else if(mode.equals("User")) {
            if(col == 0)
                return "Serial No.";
            else if(col == 1)
                return "User ID";
            else if(col == 2)
                return "Name";
            else if(col == 3)
                return "User Type";
        }
        return "";
    }
}