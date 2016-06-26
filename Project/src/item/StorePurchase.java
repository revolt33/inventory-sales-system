package item;
import business.*;
import fileStructure.*;
import frames.CreateFrame;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.rmi.*;
public class StorePurchase implements ActionListener
{
	JPanel panel;
	int i;
        ArrayList<StockObject> catCode;
	StorePurchase(JPanel panel, int i, ArrayList<StockObject> catCode)
	{
		this.panel = panel;
                this.i = i;
                this.catCode = catCode;
	}
        @Override
	public void actionPerformed(ActionEvent ae)
	{
            if(i==1)//Old Item
            {
                ArrayList<StockObject> arr = catCode;
                Component[] c = panel.getComponents();
                JComboBox com1 = (JComboBox)c[4];
                JComboBox com2 = (JComboBox)c[5];
                String item = (String)com2.getSelectedItem();
                JSpinner qty = (JSpinner)c[7];
                int qtyno = (int)qty.getValue();
                int serial = com1.getSelectedIndex();
                String cat;
                if (serial == 0) {
                    negative(3);
                    return;
                } else
                    cat = ((StockObject)(catCode.get(serial-1))).code;
                try {
                    boolean b = Visual.c.obj.oldStore(cat, item, qtyno);
                } catch (RemoteException ex) {
                    PurchaseRegister.exceptions("storePurchase.txt", ex);
                }
            }
            else if(i==2)//New Item
                {
                    Component[] c = panel.getComponents();
                    JComboBox com1 = (JComboBox) c[5];
                    int serial = com1.getSelectedIndex();
                    JTextField code = (JTextField) c[6];
                    String codes = (String) code.getText();
                    JTextField name = (JTextField) c[7];
                    String names = (String) name.getText();
                    JSpinner qty = (JSpinner) c[8];
                    int qtys = (int) qty.getValue();
                    JSpinner price = (JSpinner) c[9];
                    double prices = (double) price.getValue();
                    name.setText(null);
                    code.setText(null);
                    qty.setValue(1);
                    price.setValue(0.0);
                    String cat;
                    if (serial == 0) {
                        negative(3);
                        return;
                    } else if (codes.length() != 10) {
                        negative(1);
                        return;
                    } else if (names.equals("")) {
                        negative(2);
                        return;
                    } else 
                         cat = ((StockObject)(catCode.get(serial-1))).code;
                    boolean b = false;
                    try {
                        b = Visual.c.obj.newStore(cat, codes, names, qtys, prices);
                    }
                    catch (RemoteException ex) {
                        PurchaseRegister.exceptions("storePurchase.txt", ex);
                    }
                    if(!b)
                        negative(0);
                }
	}
       void negative(int mode) {
          String str = "";
        if (mode == 0) {
            str = "An Item with this id already exists!";
        } else if (mode == 1) {
            str = "Item Code must be of 10 characters";
        } else if (mode == 2) {
            str = "Item Name Cannot be left blank!";
        } else if (mode == 3) {
            str = "Please select a valid Category";
        }
        JOptionPane.showMessageDialog(CreateFrame.jf, str, "Error", JOptionPane.ERROR_MESSAGE);
    }
}