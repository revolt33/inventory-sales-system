package business;
import java.io.*;
import javax.swing.*;
public class Business
{
    static Visual vis;
    public static void main(String[] s) {
        File file = new File("Errors\\business");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
        try {
            //UIManager.setLookAndFeel( UIManager.getInstalledLookAndFeels()[0].getClassName());
            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        } catch (ClassNotFoundException clnfe) {
            exceptions("Business.txt", clnfe);
        } catch (InstantiationException ie) {
            exceptions("Business.txt", ie);
        } catch (IllegalAccessException iae) {
            exceptions("Business.txt", iae);
        } catch (UnsupportedLookAndFeelException ulafe) {
            exceptions("Business.txt", ulafe);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        Connection t1 = new Connection("Connection");
        Visual t2 = new Visual(t1);
        t1.start();
        vis = t2;
    }

    static void exceptions(String dest, Throwable ex) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("Errors\\business\\" + dest, true));
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace(pw);
        }
        ex.printStackTrace(pw);
        pw.flush();
        pw.close();
    }
}