package server;
import fileStructure.*;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.*;
public class Terminator extends Thread{
    int[] sp = new int[2];
    JLabel label;
    Terminator (JLabel label, int... sp) {
        this.sp[0] = sp[0];
        this.sp[1] = sp[1];
        this.label = label;
    }
    @Override
    public void run() {
       loop(); 
    }
    public void loop () {
        int val;
        val = sp[0]*60 + sp[1];
        ListIterator<ClientIntf> it = ServiceProvider.cl.listIterator();
        while (it.hasNext()) {
            ClientIntf clientIntf = it.next();
            try {
                clientIntf.stopClient(val);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        String str;
        for (int i = val; i >= 0 ; i--) {
            str = "Remaining Time:  "+i/60+" : "+i%60;
            label.setText(str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (i == 0)
                System.exit(0);
        }
    }
}
