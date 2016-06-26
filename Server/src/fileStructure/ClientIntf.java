package fileStructure;
import java.rmi.*;
public interface ClientIntf extends Remote{
    void stopClient (int sec) throws RemoteException;
}