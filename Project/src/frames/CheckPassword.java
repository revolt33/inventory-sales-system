package frames;
import java.awt.event.*;
import javax.swing.*;
import business.*;
import java.awt.*;
import java.rmi.*;
public class CheckPassword extends KeyAdapter{
    int mode;
    JPasswordField pass;
    JDialog jd;
    String getPass;
    JPasswordField pass1;
    JButton jb;
    public CheckPassword(JPasswordField pass, int mode, JDialog jd, JPasswordField pass1, JButton jb) {
        this.mode = mode;
        this.pass = pass;
        this.jd = jd;
        this.pass1 = pass1;
        this.jb = jb;
        String id = CreateFrame.id;
        try {
            getPass = Visual.c.obj.getPassword(id);
        } catch (RemoteException ex) {
            AddPanel.exceptions("checkPassword.txt", ex);
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        String password = new String(pass.getPassword());
        if (mode == 1) {
            if (password.equals(getPass))
                pass.setBackground(Color.GREEN);
            else
                pass.setBackground(Color.RED);
        } else if (mode == 2) {
            String password2 = new String(pass1.getPassword());
            if (password2.equals(password)) {
                pass1.setBackground(Color.GREEN);
                jb.setEnabled(true);
            }
            else {
                pass1.setBackground(Color.RED);
                jb.setEnabled(false);
            }
                
        }
        jd.validate();
    }
}