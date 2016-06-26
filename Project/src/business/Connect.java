package business;
import javax.swing.*;
import fileStructure.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.awt.event.*;
public class Connect implements ActionListener{
    JTextField ip;
    IpAddress jf;
    FileIntf obj;
    public Connect(JTextField ip, IpAddress jf) {
        this.ip = ip;
        this.jf = jf;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(System.getSecurityManager()==null)
            System.setSecurityManager(new RMISecurityManager());
        try {
            obj = (FileIntf) Naming.lookup("rmi://"+ip.getText()+":1022/files");
        } catch (NotBoundException ex) {
            Business.exceptions("connect.txt", ex);
            return;
        } catch (MalformedURLException ex) {
            showDialog();
            return;
        } catch (RemoteException ex) {
            showDialog();
            return;
        }
        jf.in.obj = obj;
        jf.dispose();
        Business.vis.start();
    }
    void showDialog() {
        JOptionPane.showMessageDialog(IpAddress.obj, "Connection Unsuccessful:\n Either you have provided an incorrect address, or server is offline.","Conection Error",JOptionPane.ERROR_MESSAGE);
    }
}