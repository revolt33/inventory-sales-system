package fileStructure;
import java.io.*;
public class User implements Serializable{
    public String name;
    public String id;
    public String pwd;
    public String type;

    public User(String name, String id, String type, String pwd) {
        this.name = name;
        this.id =id;
        this.pwd = pwd;
        this.type = type;
    }
    
}
