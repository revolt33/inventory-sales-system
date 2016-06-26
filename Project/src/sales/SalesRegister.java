package sales;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
public class SalesRegister {
    public static JPanel display()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,15));
        northPanel.setBackground(new Color(0,0,170));
        northPanel.setOpaque(true);
        JLabel qty = new JLabel("Quantity:");
        qty.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 20));
        qty.setForeground(Color.yellow);
        JSpinner nos = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JButton sub = new JButton("Open Form");
        northPanel.add(qty);
        northPanel.add(nos);
        northPanel.add(sub);
        JPanel child = new JPanel();
        child.setLayout(new GridBagLayout());
        JScrollPane centerPanel = new JScrollPane(child, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        LineBorder lb = new LineBorder(Color.RED, 3, true);
        centerPanel.setViewportBorder(lb);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        sub.addKeyListener(new EnterKey(child, nos));
        sub.addMouseListener(new OpenBill(child, nos));
        return mainPanel;
    }
}