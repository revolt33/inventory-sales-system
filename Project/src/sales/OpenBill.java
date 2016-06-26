package sales;
import javax.swing.*;
import java.awt.event.*;
public class OpenBill extends MouseAdapter{
    JPanel jp;
    JSpinner jsp;
    public OpenBill (JPanel jp, JSpinner jsp) {
        this.jsp = jsp;
        this.jp = jp;
    }
    @Override
    public void mouseReleased (MouseEvent me) {
        new ShowBill(jp, jsp).show();
    }
}
