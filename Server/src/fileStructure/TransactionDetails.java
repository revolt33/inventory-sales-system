package fileStructure;
import java.io.*;
import java.util.*;
public class TransactionDetails implements Serializable{
    public String id, time, name;
    public TreeSet<Transaction> set;
    public TransactionDetails (TreeSet<Transaction> set, String[] str) {
        this.set = set;
        id = str[0];
        name = str[1];
        time = str[2];
    }
}