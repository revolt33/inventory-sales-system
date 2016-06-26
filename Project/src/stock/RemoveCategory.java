package stock;
import business.Visual;
import frames.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import javax.swing.*;
import java.awt.*;
public class RemoveCategory extends MouseAdapter{
    String cat;
    JPanel jp;
    public RemoveCategory(String cat, JPanel jp) {
        this.cat = cat;
        this.jp = jp;
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        boolean b = false;
        try {
            int option = JOptionPane.showOptionDialog(CreateFrame.jf, "Are you sure to remove this category?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.YES_OPTION) {
                b = Visual.c.obj.removeCategory(cat);
                if (!b) {
                    JOptionPane.showMessageDialog(CreateFrame.jf, "Requested category could not be removed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                new ChangePanel(jp, 2).change();
            }
        } catch (RemoteException ex) {
            AddCategory.exceptions("removeCategory.txt", ex);
        }

    }
}