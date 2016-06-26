package sales;
import business.Visual;
import frames.*;
import java.awt.*;
import fileStructure.*;
import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.*;
public class Payment implements ActionListener{
    JComboBox<String>[] cat, item;
    JTextField[] itemName, catName;
    JSpinner[] qty;
    int val;
    double amt;
    public Payment(int val, JSpinner[] qty, Object[]... com) {
        this.val = val;
        this.qty = qty;
        this.cat = (JComboBox<String>[])com[0];
        this.item = (JComboBox<String>[])com[1];
        this.catName = (JTextField[])com[2];
        this.itemName = (JTextField[])com[3];
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (check()) {
            show();
            return;
        }
        amt = 0;
        JDialog jd = new JDialog(CreateFrame.jf, "Payment", true);
        jd.setLayout(new BorderLayout());
        JPanel north = new JPanel(new FlowLayout());
        JLabel header = new JLabel("Payment Window");
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 30));
        header.setForeground(Color.yellow);
        north.setBackground(new Color(50, 100, 255));
        north.setOpaque(true);
        north.add(header);
        jd.add(north, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        Insets in = new Insets(15, 25, 15, 25);
        gbc.insets = in;
        JLabel category = new JLabel("Category");
        Font f = new Font(Font.SERIF, Font.BOLD, 20);
        category.setFont(f);
        JLabel code = new JLabel("Item Code");
        code.setFont(f);
        JLabel name = new JLabel("Item Name");
        name.setFont(f);
        JLabel amount = new JLabel("Quantity");
        amount.setFont(f);
        JLabel price = new JLabel("Price");
        price.setFont(f);
        centerPanel.add(category, gbc);
        gbc.gridx = 1;
        centerPanel.add(code, gbc);
        gbc.gridx = 2;
        centerPanel.add(name, gbc);
        gbc.gridx = 3;
        centerPanel.add(amount, gbc);
        gbc.gridx = 4;
        centerPanel.add(price, gbc);
        String str, str1, str2[];
        str2 = new String[val];
        f = new Font(Font.SERIF, Font.BOLD, 15);
        Color color = new Color(255, 50, 20);
        String[] trans = new String[5];
        String[] demo = {"A", "A", "A", "A", "A"};
        TreeSet<Transaction> set = new TreeSet<>(new Transaction(demo));
        PrintBillBean[] bean = new PrintBillBean[val];
        for (int i = 1; i <= val; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            str = "" + catName[i - 1].getText();
            trans[0] = "" + cat[i-1].getSelectedItem();
            trans[1] = str;
            JLabel label1 = new JLabel();
            label1.setFont(f);
            label1.setForeground(color);
            label1.setText(str);
            centerPanel.add(label1, gbc);
            JLabel label2 = new JLabel();
            label2.setFont(f);
            label2.setForeground(color);
            str1 = (String) item[i - 1].getSelectedItem();
            trans[2] = str1;
            label2.setText(str1);
            gbc.gridx = 1;
            centerPanel.add(label2, gbc);
            str = (String) itemName[i - 1].getText();
            trans[3] = str;
            JLabel label3 = new JLabel();
            label3.setFont(f);
            label3.setForeground(color);
            label3.setText(str);
            gbc.gridx = 2;
            centerPanel.add(label3, gbc);
            str = "" + (Integer) qty[i - 1].getValue();
            trans[4] = str;
            JLabel label4 = new JLabel();
            label4.setText(str);
            label4.setFont(f);
            label4.setForeground(color);
            gbc.gridx = 3;
            centerPanel.add(label4, gbc);
            try {
                double temp = (Visual.c.obj.getPrice((String) cat[i - 1].getSelectedItem(), str1)) * (Integer) qty[i - 1].getValue();
                amt += temp;
                str2[i - 1] = "" + temp;
            } catch (RemoteException ex) {
                Debit.exceptions("payment.txt", ex);
            }
            JLabel label5 = new JLabel(str2[i - 1]);
            gbc.gridx = 4;
            label5.setFont(f);
            label5.setForeground(color);
            centerPanel.add(label5, gbc);
            Transaction t = new Transaction(trans);
            bean[i-1] = new PrintBillBean(trans[3], trans[4], str2[i-1], ""+(i));
            set.add(t);
        }
        String[] prep = prepare();
        TransactionDetails transDetails = new TransactionDetails(set, prep);
        JLabel total = new JLabel("Total");
        total.setForeground(color);
        total.setFont(f);
        JLabel totalVal = new JLabel("" + amt);
        totalVal.setForeground(color);
        totalVal.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = val + 1;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(total, gbc);
        gbc.gridx = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(totalVal, gbc);
        JLabel pay = new JLabel("Pay");
        pay.setFont(f);
        pay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pay.addMouseListener(new Debit(qty, jd, transDetails ,bean,""+amt ,prep[1], prep[2], cat, item));
        pay.setForeground(color);
        gbc.gridx = 0;
        gbc.gridy = val + 2;
        gbc.gridwidth = 5;
        centerPanel.add(pay, gbc);
        jd.setSize(1000, 600);
        Dimension dim = jd.getSize();
        JScrollPane jsp = new JScrollPane(centerPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jd.add(jsp, BorderLayout.CENTER);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        center.x -= dim.width / 2;
        center.y -= dim.height / 2;
        jd.setLocation(center);
        jd.setVisible(true);
    }

    boolean check() {
        boolean b = true;
        for (int i = 0; i < cat.length; i++) {
            if (cat[i].getSelectedIndex() == 0) {
                b = true;
                break;
            }
            b = false;
        }
        return b;
    }

    void show() {
        JOptionPane.showMessageDialog(CreateFrame.jf, "Please select a valid Category", "Error!", JOptionPane.ERROR_MESSAGE);
    }
    String[] prepare() {
        String[] str = new String[3];
        str[0] = CreateFrame.id;
        try {
            str[1] = Visual.c.obj.name(str[0]);
        } catch (RemoteException ex) {
            Debit.exceptions("payment.txt", ex);
        }
        String am_pm;
        Calendar date = Calendar.getInstance();
        int h = date.get(Calendar.HOUR);
        if (date.get(Calendar.AM_PM) == 0)
            am_pm = "AM";
        else
            am_pm = "PM";
        if(date.get(Calendar.HOUR) == 0)
                h = 12;
        str[2] = "" + h + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND) + " " + am_pm;
        return str;
    }
}