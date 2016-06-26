package manage;
import javax.swing.*;
import java.awt.event.*;
public class ChangePanel extends MouseAdapter{
    static JPanel parent;
    JPanel child;
    int i;
    public ChangePanel(JPanel child, int i) {
        this.child = child;
        this.i = i;
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        change();
    }
    void change() {
        parent.remove(child);
        if(i == 1)
            parent.add(AddUser.display());
        else if(i == 2)
            parent.add(ViewUser.display());
        parent.validate();
    }
}
