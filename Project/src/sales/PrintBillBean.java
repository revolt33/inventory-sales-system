package sales;
public class PrintBillBean {
    private String name, qty, price,serial;
    public PrintBillBean (){
        
    }
    public PrintBillBean (String name, String qty, String price, String serial) {
        setName (name);
        setQty (qty);
        setPrice (price);
        setSerial(serial);
    }
    public void setSerial (String serial) {
        this.serial = serial;
    }
    public String getSerial () {
        return serial;
    }
    public String getName() {
        return name;
    }
    public String getQty () {
        return qty;
    }
    public String getPrice () {
        return price;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setQty (String qty) {
        this.qty = qty;
    }
    public void setPrice (String price) {
        this.price = price;
    }
}