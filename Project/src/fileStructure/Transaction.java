package fileStructure;
import java.io.*;
import java.util.*;
public class Transaction implements Serializable, Comparator<Object>{
    public String catCode, catName, itemCode, itemName, qty;
    public Transaction (String[] str) {
        catCode = str[0];
        catName = str[1];
        itemCode = str[2];
        itemName = str[3];
        qty = str[4];
    }
    @Override
    public int compare(Object trans1, Object trans2) {
        Transaction transNew = (Transaction)trans1;
        Transaction transOld = (Transaction)trans2;
        int ret = transNew.itemCode.compareTo(transOld.itemCode);
        return ret;
    }
    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
