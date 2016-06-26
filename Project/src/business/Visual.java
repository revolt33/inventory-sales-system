package business;

import java.rmi.RemoteException;

public class Visual extends Thread{
    public static Connection c;
    public static Terminate t;
    public Visual(Connection c){
        Visual.c = c;
    }
    @Override
    public void run(){
        new Verification().display();
        try {
            t = new Terminate();
            Visual.c.obj.registry(t);
        } catch (RemoteException ex) {
            Business.exceptions("visual.txt", ex);
        }
    }
}
