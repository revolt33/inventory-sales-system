package manage;
import java.awt.event.*;
import java.rmi.*;
import frames.*;
import javax.swing.*;
import business.*;
import java.util.*;
import fileStructure.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.io.*;
public class DeleteUser implements ActionListener{
    JComboBox<String> jcb;
    ArrayList<User> arr;
    boolean b = true;
    public DeleteUser(JComboBox<String> jcb, ArrayList<User> arr) {
        this.jcb = jcb;
        this.arr = arr;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        int serial =  jcb.getSelectedIndex();
        String id = arr.get(serial).id;
        if (id != null) {
            if (!id.equals(CreateFrame.id)) {
                try {
                    b = Visual.c.obj.delete(id);
                    if(!b)
                        dialog();
                } catch (RemoteException ex) {
                    exceptions("deleteUser.txt", ex);
                }
            } else {
                dialog();
            }
        }
    }
    void dialog() {
        String str = "You cannot delete yourself!";
        if (!b) {
            str = "A user with this ID is logged on!";
        }
        JOptionPane.showMessageDialog(CreateFrame.jf, str, "Alert", JOptionPane.ERROR_MESSAGE);
    }

    static void exceptions(String dest, Throwable ex) {
        File file = new File("Errors\\manage");
            if(!file.isDirectory())
                file.mkdirs();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream("Errors\\manage\\"+dest, true));
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace(pw);
            }
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
    }
}