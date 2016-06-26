package fileStructure;
import java.io.*;
public class StockItem implements Serializable{
    public String name;
    public String code;
    public int qty;
    public double price;
    public StockItem(String name, String code, int qty, double price)
    {
        this.name = name;
        this.code = code;
        this.qty = qty;
        this.price = price;
    }
}
