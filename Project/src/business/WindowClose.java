package business;
import frames.CreateFrame;
import java.awt.event.*;
import java.rmi.RemoteException;
public class WindowClose extends WindowAdapter{
    @Override
    public void windowClosing (WindowEvent we) {
        try {
            if (CreateFrame.id != null) {
                Visual.c.obj.logout(CreateFrame.id);
            }
            Visual.c.obj.remove(Visual.t);
        } catch (RemoteException ex) {
            Business.exceptions("windowClose.txt", ex);
        }
        System.exit(0);
    }
}