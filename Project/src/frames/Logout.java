package frames;
import business.*;
import java.awt.event.*;
import java.rmi.RemoteException;
public class Logout extends MouseAdapter{
    CreateFrame jf;
    public Logout(CreateFrame jf1) {
        jf = jf1;
    }
    
    @Override
    public void mouseClicked(MouseEvent me)      
    {
        try {
            Visual.c.obj.logout(CreateFrame.id);
        } catch (RemoteException ex) {
            AddPanel.exceptions("logout.txt", ex);
        }
        CreateFrame.id = "";
        jf.dispose();
        new Verification().display();
    }
    
}
