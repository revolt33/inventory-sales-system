package business;
import fileStructure.*;
public class Connection extends Thread{
    public FileIntf obj;
    public Connection(String str){
        super(str);
    }
    @Override
    public void run(){
        new IpAddress(this).display();
    }
}
