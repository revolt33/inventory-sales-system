package stock;
import javax.swing.*;
import java.awt.event.*;
public class ChangePanel extends MouseAdapter{
    static JPanel parent;
    JPanel child;
    int i;
    public ChangePanel(JPanel child, int change)
    {
        this.child = child;
        i = change;
    }
    @Override
    public void mouseClicked(MouseEvent me)
    {
        change();
    }
    public void change() {
        parent.remove(child);
        if(i==1)
        {
            parent.add(Category.display());
        }
        else if(i==2)
        {
            ViewStock.display(parent);
        }
        
        parent.validate();
    }
}