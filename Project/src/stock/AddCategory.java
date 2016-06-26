package stock;
import business.*;
import fileStructure.*;
import frames.CreateFrame;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.rmi.*;
public class AddCategory implements ActionListener
{
    JPanel parent;
    JPanel child;
    public AddCategory(JPanel child)
    {
        this.child = child;
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Component[] c = (Component[])child.getComponents();
        JTextField tcode = (JTextField)c[2];
        JTextField tname = (JTextField)c[3];
        StockObject so = new StockObject();
        so.code = tcode.getText();
        so.name = tname.getText();
        boolean b = false;
        if (so.code.length() == 10) {
            try {
                b = Visual.c.obj.store(so);
            } catch (RemoteException ex) {
                exceptions("addCategory.txt", ex);
            }
        }
        tcode.setText(null);
        tname.setText(null);
        String msg;
        if (so.code.length() == 10) {
            if (b) {
                msg = "A new Category as \"" + so.name + "\" has been Added!";
                JOptionPane.showMessageDialog(CreateFrame.jf, msg, "Success!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                msg = "A Category with this id already exists.";
                JOptionPane.showMessageDialog(CreateFrame.jf, msg, "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            msg = "Category code cannot be less than 10 Characters.";
            JOptionPane.showMessageDialog(CreateFrame.jf, msg, "Error!", JOptionPane.ERROR_MESSAGE);
        }
        CreateFrame.jf.validate();
    }
    static void exceptions(String dest, Throwable ex) {
        File file = new File("Errors\\manage");
            if(!file.isDirectory())
                file.mkdirs();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream("Errors\\stock\\"+dest, true));
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace(pw);
            }
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
    }
}