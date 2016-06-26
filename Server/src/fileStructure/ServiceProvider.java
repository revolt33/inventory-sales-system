package fileStructure;
import server.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
public class ServiceProvider extends UnicastRemoteObject implements FileIntf, Serializable{
    public static ArrayList<ClientIntf> cl = new ArrayList<>();
    public static ArrayList<String> id = new ArrayList<>();
    String constructor, store, check, storeUser, fetch, fetchItem, newStore, oldStore, editStock, deleteItem, removeCategory, viewUser, delete, getPassword, change, getPrice, debit, name, transaction, getIds, reportTransactions;
    public ServiceProvider() throws RemoteException{
        File file = new File("Errors");
        if (!file.isDirectory()) {
            file.mkdir();
        }
        file = new File("User");
        constructor = "Errors\\constructor";
        store = "Errors\\store.txt";
        check = "Errors\\check.txt";
        storeUser = "Errors\\storeUser.txt";
        fetch = "Errors\\fetch.txt";
        fetchItem = "Errors\\fetchItem.txt";
        newStore = "Errors\\newStore.txt";
        oldStore = "Errors\\oldStore.txt";
        editStock = "Errors\\editStock.txt";
        deleteItem = "Errors\\deleteItem.txt";
        removeCategory = "Errors\\removeCategory.txt";
        viewUser = "Errors\\viewUser.txt";
        delete = "Errors\\delete.txt";
        getPassword = "Errors\\getPassword.txt";
        change = "Errors\\change.txt";
        getPrice = "Errors\\getPrice.txt";
        debit = "Errors\\debit.txt";
        name = "Errors\\name.txt";
        transaction = "Errors\\transaction.txt";
        getIds = "Errors\\getIds.txt";
        reportTransactions = "Errors\\reportTransactions.txt";
        if (!file.isFile()) {
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream("User");
                NewFile write = new NewFile(fos);
                User u = new User("Ayush", "12345", "Manager", "12345");
                write.writeObject(u);
            } catch (FileNotFoundException ex) {
                exceptions(constructor, ex);
            } catch (IOException ex) {
                exceptions(constructor, ex);
            }
        }
        file = new File("Transactions");
        if(!file.isDirectory()) {
            Calendar date = Calendar.getInstance();
            String str = "Transactions\\"+date.get(Calendar.YEAR)+"\\"+date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)+"\\"+date.get(Calendar.DAY_OF_MONTH);
            file = new File(str);
            file.mkdirs();
            file = new File("Transactions\\Users");
            try {
                file.createNewFile();
            } catch (IOException ex) {
                exceptions(constructor, ex);
            }
        }
    }
    @Override
    public synchronized void logout(String id) throws RemoteException {
        ServiceProvider.id.remove(id);
    }
    final void exceptions(String dest, Throwable ex) {
        PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream(dest, true));
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace(pw);
            }
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
    }
    @Override
    public synchronized boolean store(StockObject so) throws RemoteException{
        boolean b;
        File stock = new File("Stock");
        b = stock.isDirectory();
        try
        {
            if(!b)
            {
                stock.mkdir();
            }
            FileInputStream fis = new FileInputStream("Stock\\Stock");
            ReadFile read = new ReadFile(fis);
            ArrayList<StockObject> obj = new ArrayList<>();
            b = true;
            try{
                while(b) {
                StockObject stockObject = (StockObject) read.readObject();
                obj.add(stockObject);
            }
            }
            catch (EOFException eofe) {
                b = false;
                fis.close();
            } catch (ClassNotFoundException ex) {
                exceptions(store, ex);
            }
            ListIterator<StockObject> it = obj.listIterator();
            while (it.hasNext()) {
                StockObject stockObject = it.next();
                if(stockObject.code.equals(so.code))
                    return b;
            }
            FileOutputStream fos = new FileOutputStream("Stock\\Stock",true);
            NewFile ob = new NewFile(fos);
            ob.writeObject(so);
            File file  = new File("Stock\\"+so.code);
            file.createNewFile();
            ob.close();
            b = true;
        }
        catch(IOException ioe)
        {
            b = false;
            exceptions(store, ioe);
        }
        return b;
    }
    @Override
    public ArrayList<StockObject> fetch() throws RemoteException
    {
        ArrayList<StockObject> arr = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Stock\\Stock");
            ReadFile read = new ReadFile(fis);
            while (true) {
                StockObject stock = (StockObject)read.readObject();
                arr.add(stock);
            }
        } catch(EOFException eofe) {
        } 
        catch (FileNotFoundException ex) {
            exceptions(fetch, ex);
        } catch (IOException ex) {
            exceptions(fetch, ex);
        } catch (ClassNotFoundException ex) {
            PrintWriter pw = null;
            exceptions(fetch, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(fetch, ex);
            }
        }
        return arr;
    }
    @Override
    public ArrayList<StockItem> fetchItem(String str) throws RemoteException{
        ArrayList<StockItem> arr = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Stock\\"+str);
            ReadFile read = new ReadFile(fis);
            while (true) {                
                StockItem item = (StockItem)read.readObject();
                arr.add(item);
            }
        }
        catch(EOFException eofe) {
            
        }
        catch (FileNotFoundException ex) {
            exceptions(fetchItem, ex);
        }
        catch (IOException ex) {
            exceptions(fetchItem, ex);
        }
        catch (ClassNotFoundException ex) {
            exceptions(fetchItem, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(fetchItem, ex);
            }
        }
        return arr;
    }
    @Override
    public synchronized boolean newStore(String cat, String code, String name, int qty, double price) throws RemoteException{
        ArrayList<StockItem> arr = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Stock\\"+cat);
            ReadFile read = new ReadFile(fis);
            while (true) {                
                StockItem item = (StockItem) read.readObject();
                arr.add(item);
            }
        } catch (EOFException ex){
            try {
                fis.close();
            } catch (IOException ex1) {
                exceptions(newStore, ex);
            }
        } catch (FileNotFoundException ex) {
           exceptions(newStore, ex);
        } catch (IOException ex) {
            exceptions(newStore, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(newStore, ex);
        }
        ListIterator<StockItem> it = arr.listIterator();
        while (it.hasNext()) {
            StockItem stockItem = it.next();
            if (stockItem.code.equals(code))
                return false;
        }
        FileOutputStream fos = null;
        StockItem item = new StockItem(name, code, qty, price);
        try {
            fos = new FileOutputStream("Stock\\"+cat, true);
            NewFile write = new NewFile(fos);
            write.writeObject(item);
        } catch (FileNotFoundException ex) {
            exceptions(newStore, ex);
        } catch (IOException ex) {
            exceptions(newStore, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                exceptions(newStore, ex);
        }
        return true;
    }
    }
    @Override
    public synchronized boolean oldStore(String cat, String code, int qty) throws RemoteException{
        FileInputStream fis = null;
        ArrayList<StockItem> arr = new ArrayList<>();
        try {
            fis = new FileInputStream("Stock\\"+cat);
            ReadFile read = new ReadFile(fis);
            while (true) {                
                StockItem stock = (StockItem)read.readObject();
                arr.add(stock);
            }
        }
        catch(EOFException eofe) {
            
        }
        catch (FileNotFoundException ex) {
            exceptions(oldStore, ex);
        }
        catch (IOException ex) {
            exceptions(oldStore, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(oldStore, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(oldStore, ex);
            }
        }
        ListIterator<StockItem> it1 = arr.listIterator();
        boolean b1 = it1.hasNext();
        while (b1) {
            StockItem item1 = it1.next();
            if(item1.code.equals(code)) {
                item1.qty+=qty;
                it1.set(item1);
                break;
            }
                b1 = it1.hasNext();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Stock\\"+cat);
            NewFile write = new NewFile(fos);
            ListIterator<StockItem> it = arr.listIterator();
            boolean b = it.hasNext();
            while (b) {                
                StockItem stock = (StockItem)it.next();
                write.writeObject(stock);
                b = it.hasNext();
            }
            fos.close();
        } catch (FileNotFoundException ex) {
            exceptions(oldStore, ex);
        } catch (IOException ex) {
            exceptions(oldStore, ex);
        }
        return true;
    
    }
    @Override
    public synchronized boolean store(User u) throws RemoteException{
        ArrayList<User> arr = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("User");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                User user = (User) read.readObject();
                arr.add(user);
            }
        } catch (EOFException ex) {
            
        } catch (FileNotFoundException ex) {
            exceptions(storeUser, ex);
        } catch (IOException ex) {
            exceptions(storeUser, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(storeUser, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(storeUser, ex);
            }
        }
        ListIterator<User> it = arr.listIterator();
        while (it.hasNext()) {
            User user = it.next();
            if(user.id.equals(u.id))
                return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("User", true);
            NewFile write = new NewFile(fos);
            write.writeObject(u);
            write.close();
        }
        catch (FileNotFoundException ex) {
            exceptions(storeUser, ex);
        } catch (IOException ex) {
            exceptions(storeUser, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                exceptions(storeUser, ex);
            }
        }
        return true;
    }
    @Override
    public String check(String id, String pass) throws RemoteException{
        FileInputStream fis = null;
        ReadFile read = null;
        ArrayList<User> arr = new ArrayList<>();
        String str = null;
        try {
            fis = new FileInputStream("User");
            read = new ReadFile(fis);
            File file = new File("Stock");
            boolean b = file.isDirectory();
            if(b==false){
               file.mkdir();
               File stock = new File("Stock\\Stock");
               stock.createNewFile();
            }
            while (true) {
                User obj = (User) read.readObject();
                arr.add(obj);
            }
        } catch (FileNotFoundException ex) {
            exceptions(check, ex);
        } catch (EOFException eofe) {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(check, ex);
            }
        } catch (IOException ex) {
            exceptions(check, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(check, ex);
        }
        boolean b;
        ListIterator<String> ids = ServiceProvider.id.listIterator();
        while (ids.hasNext()) {
            String string = ids.next();
            if(id.equals(string))
                return "In Use";
        }
        ListIterator<User> it = arr.listIterator();
        b = it.hasNext();
        l1:
        while (b) {
            User getUser = (User)it.next();
            if (id.equals(getUser.id)) {
                if (pass.equals(getUser.pwd)) {
                    str = getUser.type;
                    ServiceProvider.id.add(id);
                    break l1;
                } else {
                    str = "Invalid Password";
                    break l1;
                }
            }
            b = it.hasNext();
            if (b == false)
                str = "Invalid User";
        }
        return str;
    }
    @Override
    public synchronized boolean editStock(String cat, String code, String name, int qty, double price) throws RemoteException{
        FileInputStream fis = null;
        ArrayList<StockItem> arr = new ArrayList<>();
        try {
            fis = new FileInputStream("Stock\\"+cat);
            ReadFile read =  new ReadFile(fis);
            while(true) {
                StockItem item = (StockItem)read.readObject();
                arr.add(item);
            }
        } catch (FileNotFoundException ex) {
            exceptions(editStock, ex);
        } catch (EOFException eofe) {
            
        } catch (IOException ex) {
            exceptions(editStock, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(editStock, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(editStock, ex);
            }
        }
        ListIterator<StockItem> it = arr.listIterator();
        boolean b = it.hasNext();
        while(b) {
            StockItem item = it.next();
            if(item.code.equals(code)) {
                item.name = name;
                item.price = price;
                item.qty = qty;
                break;
            }
            b = it.hasNext();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Stock\\"+cat);
            NewFile write = new NewFile(fos);
            ListIterator<StockItem> it1 = arr.listIterator();
            b = it1.hasNext();
            while(b) {
                StockItem item = it1.next();
                write.writeObject(item);
                b = it1.hasNext();
            }
        } catch (FileNotFoundException ex) {
            exceptions(editStock, ex);
        } catch (IOException ex) {
            exceptions(editStock, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                exceptions(editStock, ex);
            }
        }
        return true;
    }
    @Override
    public synchronized boolean deleteItem(String cat, String code) throws RemoteException{
        ArrayList<StockItem> arr = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis  = new FileInputStream("Stock\\"+cat);
            ReadFile read = new ReadFile(fis);
            while(true) {
                StockItem item = (StockItem)read.readObject();
                arr.add(item);
            }
        } catch(EOFException eofe) {
        } catch (FileNotFoundException ex) {
            exceptions(deleteItem, ex);
        } catch (IOException ex) {
            exceptions(deleteItem, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(deleteItem, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(deleteItem, ex);
            }
        }
        ListIterator<StockItem> it = arr.listIterator();
        boolean b = it.hasNext();
        while (b) {            
            StockItem item = it.next();
            if(item.code.equals(code)) {
                arr.remove(item);
                break;
            }
            b = it.hasNext();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Stock\\"+cat);
            NewFile write = new NewFile(fos);
            ListIterator<StockItem> it1 = arr.listIterator();
            b = it1.hasNext();
            while (b) {                
                StockItem item = it1.next();
                write.writeObject(item);
                b = it1.hasNext();
            }
        } catch (FileNotFoundException ex) {
            exceptions(deleteItem, ex);
        } catch (IOException ex) {
            exceptions(deleteItem, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                exceptions(deleteItem, ex);
            }
        }
        return true;
    }
    @Override
    public synchronized boolean removeCategory(String cat) throws RemoteException {
        ArrayList<StockObject> arr = new ArrayList<>();
        boolean ret = false;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Stock\\Stock");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                StockObject stock = (StockObject)read.readObject();
                arr.add(stock);
            }
        } catch (EOFException eofe) {
        } catch (FileNotFoundException ex) {
            exceptions(removeCategory, ex);
        } catch (IOException ex) {
            exceptions(removeCategory, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(removeCategory, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(removeCategory, ex);
            }
        }
        ListIterator<StockObject> it = arr.listIterator();
        boolean b = it.hasNext();
        while (b) {            
            StockObject stock = it.next();
            if(stock.code.equals(cat)) {
                File file = new File("Stock\\"+cat);
                ret = file.delete();
                if(ret)
                    arr.remove(stock);
                break;
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Stock\\Stock");
            NewFile write = new NewFile(fos);
            it = arr.listIterator();
            b = it.hasNext();
            while (b) {                
                StockObject stock = it.next();
                write.writeObject(stock);
                b = it.hasNext();
            }
        } catch (FileNotFoundException ex) {
            exceptions(removeCategory, ex);
        } catch (IOException ex) {
            exceptions(removeCategory, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                exceptions(removeCategory, ex);
            }
        }
        return ret;
    }
    @Override
    public ArrayList<User> viewUser() throws RemoteException {
        ArrayList<User> arr = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("User");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                User user = (User)read.readObject();
                arr.add(user);
            }
        } catch(EOFException ex) {
        } catch (FileNotFoundException ex) {
            exceptions(viewUser, ex);
        } catch (IOException ex) {
            exceptions(viewUser, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(viewUser, ex);
        }
        return arr;
    }
    @Override
    public synchronized boolean delete(String id) throws RemoteException{
        ListIterator<String> ids = ServiceProvider.id.listIterator();
        while (ids.hasNext()) {
            String string = ids.next();
            if(string.equals(id))
                return false;
        }
        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("User");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                User user = (User)read.readObject();
                users.add(user);
            }
        } catch(EOFException ex) {
        } catch (FileNotFoundException ex) {
            exceptions(delete, ex);
        } catch (IOException ex) {
            exceptions(delete, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(delete, ex);
        }
        ListIterator<User> it = users.listIterator();
        while (it.hasNext()) {            
            User user = it.next();
            if(user.id.equals(id)) {
                users.remove(user);
                break;
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream("User");
            NewFile write = new NewFile(fos);
            it = users.listIterator();
            while (it.hasNext()) {
                User user = it.next();
                write.writeObject(user);
            }
        } catch (FileNotFoundException ex) {
            exceptions(delete, ex);
        } catch (IOException ex) {
            exceptions(delete, ex);
        }
        return true;
    }
    @Override
    public String getPassword(String id) throws RemoteException {
        String pass = "";
        try {
            FileInputStream fis = new FileInputStream("User");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                User user = (User)read.readObject();
                if(user.id.equals(id)) {
                    pass = user.pwd;
                    break;
                }
            }
        } catch (EOFException eofe) {
        }
        catch (FileNotFoundException ex) {
            exceptions(getPassword, ex);
        } catch (IOException ex) {
            exceptions(getPassword, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(getPassword, ex);
        }
        return pass;
    }
    @Override
    public synchronized void change(String id, String pass) throws RemoteException{
        ArrayList<User> arr = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("User");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                User user =(User)read.readObject();
                arr.add(user);
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            exceptions(change, ex);
        } catch (IOException ex) {
            exceptions(change, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(change, ex);
        }
        ListIterator<User>  it = arr.listIterator();
        while (it.hasNext()) {
            User user = it.next();
            if(user.id.equals(id)) {
                user.pwd = pass;
                break;
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream("User");
            NewFile write = new NewFile(fos);
            it = arr.listIterator();
            while (it.hasNext())
                write.writeObject(it.next());
        } catch (FileNotFoundException ex) {
            exceptions(change, ex);
        } catch (IOException ex) {
            exceptions(change, ex);
        }
    }
    @Override
    public double getPrice (String cat, String code) throws RemoteException {
        ArrayList<StockItem> item = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Stock\\"+cat);
            ReadFile read = new ReadFile(fis);
            while (true) {                
                StockItem obj = (StockItem) read.readObject();
                item.add(obj);
            }
        } catch (EOFException eofe) {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(getPrice, ex);
            }
        } catch (FileNotFoundException ex) {
            exceptions(getPrice, ex);
        } catch (IOException ex) {
            exceptions(getPrice, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(getPrice, ex);
        }
        ListIterator<StockItem> it =item.listIterator();
        while (it.hasNext()) {
            StockItem stockItem = it.next();
            if (stockItem.code.equals(code)) {
                return stockItem.price;
            }

        }
        return 0.0;
    }
    @Override
    public synchronized void registry (ClientIntf client) throws RemoteException{
        cl.add(client);
        int size = cl.size();
        StartupWindow.no.setText(""+size);
    }
    @Override
    public synchronized void remove(ClientIntf client) throws RemoteException {
        cl.remove(client);
        int size = cl.size();
        StartupWindow.no.setText(""+size);
    }
    @Override
    public synchronized boolean debit (String[] cat, String[] item, int[] qty) throws RemoteException {
        ArrayList arr[] = new ArrayList[cat.length];
        for (int i = 0; i < cat.length; i++) {
            arr[i] = new ArrayList();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("Stock\\" + cat[i]);
                ReadFile read = new ReadFile(fis);
                while (true) {
                    StockItem obj = (StockItem) read.readObject();
                    arr[i].add(obj);
                }
            } catch (EOFException ex) {
                try {
                    fis.close();
                } catch (IOException ex1) {
                    exceptions(debit, ex1);
                }
            } catch (FileNotFoundException ex) {
                exceptions(debit, ex);
            } catch (IOException ex) {
                exceptions(debit, ex);
            } catch (ClassNotFoundException ex) {
                exceptions(debit, ex);
            }
            ListIterator<StockItem> it = arr[i].listIterator();
            while (it.hasNext()) {
                StockItem stockItem = it.next();
                if (stockItem.code.equals(item[i])) {
                    int temp = stockItem.qty;
                    if ((temp -= qty[i]) < 0) {
                        return false;
                    }
                }
            }
        }
        arr = new ArrayList[cat.length];
        for (int i = 0; i < cat.length; i++) {
            arr[i] = new ArrayList();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("Stock\\" + cat[i]);
                ReadFile read = new ReadFile(fis);
                while (true) {
                    StockItem obj = (StockItem) read.readObject();
                    arr[i].add(obj);
                }
            } catch (EOFException ex) {
                try {
                    fis.close();
                } catch (IOException ex1) {
                    exceptions(debit, ex1);
                }
            } catch (FileNotFoundException ex) {
                exceptions(debit, ex);
            } catch (IOException ex) {
                exceptions(debit, ex);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            ListIterator<StockItem> it = arr[i].listIterator();
            while (it.hasNext()) {
                StockItem stockItem = it.next();
                if (stockItem.code.equals(item[i])) {
                    stockItem.qty -= qty[i];
                    break;
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream("Stock\\" + cat[i]);
                NewFile write = new NewFile(fos);
                it = arr[i].listIterator();
                while (it.hasNext()) {
                    StockItem stockItem = it.next();
                    write.writeObject(stockItem);
                }
                fos.close();
            } catch (FileNotFoundException ex) {
                exceptions(debit, ex);
            } catch (IOException ex) {
                exceptions(debit, ex);
            }
        }
        return true;
    }
    @Override
    public String name(String id) throws RemoteException{
        String name = "";
        try {
            FileInputStream fis = new FileInputStream("User");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                User user = (User) read.readObject();
                if(user.id.equals(id)) {
                    name = user.name;
                    break;
                }
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            exceptions(this.name, ex);
        } catch (IOException ex) {
            exceptions(this.name, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(this.name, ex);
        }
        return name;
    }
    @Override
    public void transaction(TransactionDetails details) throws RemoteException{
        Calendar date = Calendar.getInstance();
        String year, month, day;
        year = "" + date.get(Calendar.YEAR);
        month = "" + date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        day = "" + date.get(Calendar.DAY_OF_MONTH);
        File file = new File("Transactions\\"+year+"\\"+month+"\\"+day);
        if(!file.isDirectory())
            file.mkdirs();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Transactions\\"+year+"\\"+month+"\\"+day+"\\"+details.id, true);
            NewFile write = new NewFile(fos);
            write.writeObject(details);
        } catch (FileNotFoundException ex) {
            exceptions(transaction, ex);
        } catch (IOException ex) {
            exceptions(transaction, ex);
        }
        finally {
            try {
                fos.close();
            } catch (IOException ex) {
                exceptions(transaction, ex);
            }
        }
        byte flag = 0;
        FileInputStream fis = null;
        ArrayList<String> arr = new ArrayList<>();
        try {
            file = new File("Transactions\\Users");
            if(!file.isFile())
                file.createNewFile();
            fis = new FileInputStream("Transactions\\Users");
            ReadFile read = new ReadFile(fis);
            while (true) {                
                String user = (String)read.readObject();
                arr.add(user);
            }
        } catch (EOFException eo) {
        } catch (FileNotFoundException ex) {
            exceptions(transaction, ex);
        } catch (IOException ex) {
            exceptions(transaction, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(transaction, ex);
        }
        finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(transaction, ex);
            }
        }
        ListIterator<String> it = arr.listIterator();
        while (it.hasNext()) {
            String string = it.next();
            if(details.id.equals(string)){
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            try {
                fos = new FileOutputStream("Transactions\\Users", true);
                NewFile write = new NewFile(fos);
                write.writeObject(details.id);
            } catch (FileNotFoundException ex) {
                exceptions(transaction, ex);
            } catch (IOException ex) {
                exceptions(transaction, ex);
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    exceptions(transaction, ex);
                }
            }
        }
    }
    @Override
    public ArrayList<String> getIds() throws RemoteException{
        FileInputStream fis = null;
        ArrayList<String> ids = new ArrayList<>();
        try {
            fis = new FileInputStream("Transactions\\Users");
            ReadFile read = new ReadFile(fis);
            while (true) {
                String id = (String) read.readObject();
                ids.add(id);
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            exceptions(getIds, ex);
        } catch (IOException ex) {
            exceptions(getIds, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(getIds, ex);
        }
        finally {
            try {
                fis.close();
            } catch (IOException ex) {
                exceptions(getIds, ex);
            }
        }
        return ids;
    }
    @Override
    public String[] directory(String dir) throws RemoteException {
        File file = new File(dir);
        String[] list = file.list();
        ArrayList<String> check = new ArrayList<>();
        for (String s: list) {
            check.add(dir+"\\"+s);
        }
        ListIterator<String> it = check.listIterator();
        ArrayList<String> del = new ArrayList<>();
        while (it.hasNext()) {
            String string = it.next();
            if(new File(string).isFile())
                del.add(string);
        }
        it = del.listIterator();
        while (it.hasNext()) {
            String string = it.next();
            check.remove(string);
        }
        String[] lists = new String[check.size()];
        it = check.listIterator();
        int i = 0;
        while (it.hasNext()) {
            String string = it.next();
            lists[i++] = new File(string).getName();
        }
        return lists;
    }
    @Override
    public ArrayList<TransactionDetails> reportTransactions(String path) throws RemoteException{
        File file = new File(path);
        ArrayList<TransactionDetails> transact = new ArrayList<>();
        if(!file.isFile())
            return null;
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ReadFile read = new ReadFile(fis);
            while (true) {
                TransactionDetails trans = (TransactionDetails) read.readObject();
                transact.add(trans);
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            exceptions(reportTransactions, ex);
        } catch (IOException ex) {
            exceptions(reportTransactions, ex);
        } catch (ClassNotFoundException ex) {
            exceptions(reportTransactions, ex);
        }
        return transact;
    }
}