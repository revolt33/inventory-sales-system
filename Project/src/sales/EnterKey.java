package sales;
import java.awt.event.*;
import javax.swing.*;
public class EnterKey extends KeyAdapter{
    JPanel jp;
    JSpinner jsp;
    public EnterKey(JPanel jp,JSpinner jsp) {
        this.jp = jp;
        this.jsp = jsp;
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        int i = ke.getKeyCode();
        if(i == 10)
            new ShowBill(jp, jsp).show();
    }
}