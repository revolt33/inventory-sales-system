package server;
import fileStructure.*;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.*;
import java.awt.event.*;
import javax.swing.*;
public class StartServer implements ActionListener{
    JButton jb;
    int mode;
    public StartServer(JButton jb, int mode) {
        this.jb = jb;
        this.mode = mode;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (mode == 1) {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            ServiceProvider ser = null;
            try {
                LocateRegistry.createRegistry(1022);
                ser = new ServiceProvider();
                Naming.bind("rmi://localhost:1022/files", ser);
            } catch (AlreadyBoundException | MalformedURLException | RemoteException ex) {
                ex.printStackTrace();
            }
            jb.setEnabled(false);
        } else if (mode == 2) {
            new ShutdownWindow().stopServer();
        }
    }
}
