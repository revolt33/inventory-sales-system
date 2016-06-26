package business;
import java.rmi.*;
import fileStructure.*;
import java.rmi.server.*;
import java.io.*;
public class Terminate extends UnicastRemoteObject implements ClientIntf, Serializable{
    public Terminate () throws RemoteException{
        
    }
    @Override
    public void stopClient (int sec) throws RemoteException{
        Countdown cd = new Countdown();
        cd.con = sec;
        cd.start();
    }
}