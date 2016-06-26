package server;
import javax.swing.*;
public class ManageFiles {

    public static void main(String[] s) {
        System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
        try {
            try {
                try {
                    try {

                        //UIManager.setLookAndFeel( UIManager.getInstalledLookAndFeels()[0].getClassName());
                        UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
                    } catch (ClassNotFoundException clnfe) {
                    }
                } catch (InstantiationException ie) {
                }
            } catch (IllegalAccessException iae) {
            }
        } catch (UnsupportedLookAndFeelException ulafe) {
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        new StartupWindow().display();
    }
}