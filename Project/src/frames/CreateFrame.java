package frames;
import business.WindowClose;
import javax.swing.*;
import java.awt.*;

public class CreateFrame extends JFrame
{
    public static CreateFrame jf;
    public static String id;
    public CreateFrame(String s)
	{
		super(s);
                jf = this;
	}
	   public void showFrame(String s) {
        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        d1.height -= 40;
        this.setSize(d1);
        ImageIcon ico = new ImageIcon("Icon.jpg");
        Image icon = ico.getImage();
        this.setIconImage(icon);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowClose());
        JPanel jp1 = new JPanel(new BorderLayout());
        JPanel jp3 = new JPanel(new FlowLayout());
        JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        jp3.setBackground(new Color(50, 100, 255));
        jp4.setBackground(new Color(50, 100, 255));
        jp3.setOpaque(true);
        jp4.setOpaque(true);
        this.add(jp1, BorderLayout.NORTH);
        JLabel l1 = new JLabel();
        l1.setForeground(new Color(255, 255, 20));
        l1.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        jp3.add(l1);
        JLabel jl2 = new JLabel("Logout");
        JLabel change = new JLabel("Change Password");
        change.addMouseListener(new ChangePassword());
        change.setForeground(new Color(255, 255, 20));
        change.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        change.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jl2.setForeground(new Color(255, 255, 20));
        jl2.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        jl2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jl2.addMouseListener(new Logout(this));
        if(s.equals("Manager")) {
            JLabel trans = new JLabel("Transactions");
            trans.setForeground(new Color(255, 255, 20));
            trans.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
            trans.setCursor(new Cursor(Cursor.HAND_CURSOR));
            trans.addMouseListener(new ViewTransaction());
            jp4.add(trans);
        }
        jp4.add(change);
        jp4.add(jl2);
        jp1.add(jp3, BorderLayout.NORTH);
        jp1.add(jp4, BorderLayout.CENTER);
        JComponent jp2 = AddPanel.add(s, l1);
        this.add(jp2, BorderLayout.CENTER);
        JPanel jp6 = new JPanel(new FlowLayout());
        jp6.setBackground(Color.RED);
        jp6.setOpaque(true);
        this.add(jp6, BorderLayout.SOUTH);
        JLabel l9 = new JLabel("Developed by: Ayush");
        l9.setFont(new Font(Font.MONOSPACED, Font.ROMAN_BASELINE, 20));
        l9.setForeground(new Color(255, 255, 0));
        jp6.add(l9);
        this.setVisible(true);
    }
}