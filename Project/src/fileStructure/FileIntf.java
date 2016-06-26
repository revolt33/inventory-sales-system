package fileStructure;
import java.rmi.*;
import java.util.ArrayList;
public interface FileIntf extends Remote{
    boolean store(StockObject so) throws RemoteException;
    String check(String id, String pass) throws RemoteException;
    boolean store(User u) throws RemoteException;
    ArrayList<StockObject> fetch() throws RemoteException;
    ArrayList<StockItem> fetchItem(String str) throws RemoteException;
    boolean newStore(String cat, String code, String name, int qty, double price) throws RemoteException;
    boolean oldStore(String cat, String code, int qty) throws RemoteException;
    boolean editStock(String cat, String code, String name, int qty, double price) throws RemoteException;
    boolean deleteItem(String cat, String code) throws RemoteException;
    boolean removeCategory(String cat) throws RemoteException;
    ArrayList<User> viewUser() throws RemoteException;
    boolean delete(String id) throws RemoteException;
    String getPassword(String id) throws RemoteException;
    void change(String id, String pass) throws RemoteException;
    double getPrice(String cat, String code) throws RemoteException;
    void registry(ClientIntf client) throws RemoteException;
    void remove(ClientIntf client) throws RemoteException;
    boolean debit (String[] cat, String[] item, int[] qty) throws RemoteException;
    void logout(String id) throws RemoteException;
    String name(String id) throws RemoteException;
    void transaction(TransactionDetails details) throws RemoteException;
    ArrayList<String> getIds() throws RemoteException;
    String[] directory(String dir) throws RemoteException;
    ArrayList<TransactionDetails> reportTransactions(String path) throws RemoteException;
}