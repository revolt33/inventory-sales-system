package frames;
import java.util.*;
import fileStructure.*;
import javax.swing.table.*;
public class DesignTable extends AbstractTableModel{
    ArrayList<Transaction> arr = new ArrayList<>();
    public DesignTable (TreeSet<Transaction> set) {
        Iterator<Transaction> it = set.iterator();
        while (it.hasNext()) {
            Transaction transaction = it.next();
            arr.add(transaction);
        }
    }
    @Override
    public int getRowCount() {
        return arr.size();
    }
    @Override
    public Object getValueAt(int row, int col) {
        Transaction trans = arr.get(row);
        switch(col) {
            case 0:
                return trans.catCode;
            case 1:
                return trans.catName;
            case 2:
                return trans.itemCode;
            case 3:
                return trans.itemName;
            case 4:
                return trans.qty;
            default:
                return "";
        }
    }
    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public String getColumnName(int col) {
        switch(col) {
            case 0:
                return "Category Code";
            case 1:
                return "Category Name";
            case 2:
                return "Item Code";
            case 3:
                return "Item Name";
            case 4:
                return "Quantity";
            default:
                return "";
        }
    }
}