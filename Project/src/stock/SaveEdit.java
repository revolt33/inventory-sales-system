package stock;
import business.*;
import javax.swing.*;
import java.awt.event.*;
import frames.CreateFrame;
import java.rmi.RemoteException;
public class SaveEdit implements ActionListener{
    String cat;
    String code;
    JDialog jd;
    JTextField name;
    JSpinner qty; 
    JSpinner price;
    JPanel jp;
    public SaveEdit(JPanel jp,String cat, String code, JDialog jd, JTextField name, JSpinner qty, JSpinner price){
        this.cat = cat;
        this.code = code;
        this.jd = jd;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.jp = jp;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(!name.getText().equals("")) 
        {
        try {
            Visual.c.obj.editStock(cat, code, name.getText(), (int)qty.getValue(), (double)price.getValue());
            System.out.println(price.getValue());
        } catch (RemoteException ex) {
            AddCategory.exceptions("saveEdit.txt", ex);
        }
        jd.dispose();
        new ChangePanel(jp, 2).change();
        }
        else
        {
            JOptionPane.showMessageDialog(CreateFrame.jf, "Item Name can not be left blank!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}