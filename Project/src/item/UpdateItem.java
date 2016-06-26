package item;
import business.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import fileStructure.*;
import java.rmi.*;
public class UpdateItem implements ActionListener{
    public JComboBox jcb1, jcb2;
    public ArrayList<StockObject> stock;
    public UpdateItemName up;
    public UpdateItem(JComboBox jcb1,JComboBox jcb2, ArrayList<StockObject> stock, UpdateItemName up)
    {
        this.jcb1 = jcb1;
        this.jcb2 = jcb2;
        this.stock = stock;
        this.up = up;
    }
    @Override
    public void actionPerformed(ActionEvent fe)
    {
        String cat = null;
        ArrayList<StockItem> al = null;
        int temp = 0;
        
        if(jcb1.getSelectedIndex()!=0)
        {
            jcb2.removeAllItems();
            int index = jcb1.getSelectedIndex();
            cat = ((StockObject)stock.get(index-1)).code;
            try {
                al = Visual.c.obj.fetchItem(cat);
                up.item = al;
            } catch (RemoteException ex) {
                PurchaseRegister.exceptions("updateItem.txt", ex);
            }
            ListIterator<StockItem> it = al.listIterator();
            boolean b;
            b = it.hasNext();
            while (b) {
                jcb2.addItem(((StockItem)it.next()).code);
                b = it.hasNext();
            }
        }
    }
}