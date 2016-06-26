package manage;
import business.*;
import frames.*;
import javax.swing.*;
import fileStructure.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
public class StoreUser implements ActionListener{
    User u;
    JTextField name;
    JTextField id;
    JComboBox type;
    JTextField pwd;
    public StoreUser(JTextField name, JTextField id, JComboBox type, JTextField pwd)
    {
        this.name = name;
        this.id = id;
        this.type = type;
        this.pwd = pwd;
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(check())
            return;
        u = new User(name.getText(), id.getText(), (String)type.getSelectedItem(), pwd.getText());
        boolean b = false;
        try {
            b = Visual.c.obj.store(u);
        }
        catch (RemoteException ex) {
            DeleteUser.exceptions("storeUser.txt", ex);
        }
        JPanel jp = (JPanel)name.getParent();
        String msg;
        if (b){
            msg = name.getText() + " has been added as " + (String) type.getSelectedItem() + "!";
            JOptionPane.showMessageDialog(CreateFrame.jf, msg , "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            msg = "A user with this ID already exists.";
            JOptionPane.showMessageDialog(CreateFrame.jf, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
        name.setText("");
        id.setText("");
        pwd.setText("");
        jp.validate();
    }
    boolean check() {
        boolean b = false;
        if (name.getText().length() == 0 || id.getText().length() == 0 || pwd.getText().length() == 0) {
            JOptionPane.showMessageDialog(CreateFrame.jf, "All fields are mandatory", "Alert", JOptionPane.ERROR_MESSAGE);
            name.setText("");
            id.setText("");
            pwd.setText("");
            b = true;
        }
        return b;
    }
}