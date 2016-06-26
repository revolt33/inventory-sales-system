package frames;
import java.awt.event.*;
import javax.swing.*;
import business.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.rmi.*;
public class SavePassword implements ActionListener{
    JDialog jd;
    JPasswordField oldPass, newPass, confirmPass;
    public SavePassword(JDialog jd, JPasswordField... pass) {
        this.jd = jd;
        oldPass = pass[0];
        newPass = pass[1];
        confirmPass = pass[2];
    }
    @Override
    public void actionPerformed (ActionEvent ae) {
        String id = CreateFrame.id;
        String pass = null;
        try {
            pass = Visual.c.obj.getPassword(id);
        } catch (RemoteException ex) {
            AddPanel.exceptions("savePassword.txt", ex);
        }
        if (pass.equals(new String(oldPass.getPassword())))
            if ((new String(newPass.getPassword())).equals(new String(confirmPass.getPassword()))) {
                try {
                    Visual.c.obj.change(id, new String(confirmPass.getPassword()));
                    jd.dispose();
                } catch (RemoteException ex) {
                    AddPanel.exceptions("savePassword.txt", ex);
                }
            } else {
                JOptionPane.showMessageDialog(CreateFrame.jf, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        else {
            JOptionPane.showMessageDialog(CreateFrame.jf, "Incorrect Password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
