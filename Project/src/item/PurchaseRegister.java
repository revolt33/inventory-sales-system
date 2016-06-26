package item;
import business.*;
import fileStructure.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;
import java.rmi.RemoteException;
import java.util.*;
import java.io.*;
public class PurchaseRegister
{
	public static JPanel display()
	{
		JPanel parent = new JPanel(new GridBagLayout());
		JPanel child1 = new JPanel(new GridBagLayout());
                child1.setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy());
		LineBorder lb1 = new LineBorder(Color.GREEN, 3, true);
		new ColorTitle(lb1, child1, "In Stock");
		GridBagConstraints gb3 = new GridBagConstraints();
		gb3.gridx = 0;
		gb3.gridy = 0;
		parent.add(child1,gb3);
		GridBagConstraints gb1 = new GridBagConstraints();
		Insets in1 = new Insets(15,100,15,20);
		gb1.insets = in1;
		Insets in2 = new Insets(15,20,15,100);
		Font f1 = new Font(Font.SERIF, Font.ROMAN_BASELINE,15);
		JLabel jl1 = new JLabel("Category");
		jl1.setFont(f1);
		gb1.gridx = 0;
		gb1.gridy = 0;
		gb1.insets = in1;
		child1.add(jl1, gb1);
		Insets in3 = new Insets(15,20,15,20);
		gb1.insets = in3;
		JLabel jl2 = new JLabel("Item Code");
		gb1.gridx = 1;
		jl2.setFont(f1);
		child1.add(jl2, gb1);
		JLabel jl3 = new JLabel("Name");
		gb1.gridx = 2;
		jl3.setFont(f1);
		child1.add(jl3, gb1);
		JLabel jl4 = new JLabel("Quantity");
		gb1.gridx = 3;
		gb1.insets = in2;
		jl4.setFont(f1);
		child1.add(jl4, gb1);
		String[] cat;
                ArrayList<StockObject> stock = null;
            try {
                stock = Visual.c.obj.fetch();
            } catch (RemoteException ex) {
                exceptions("purchaseRegister.txt", ex);
            }
                cat = new String[(stock.size())+1];
                cat[0] = "Select A Category";
                int i = 1;
                ListIterator<StockObject> it = stock.listIterator();
                boolean b = it.hasNext();
                while (b) {
                   cat[i] = ((StockObject)it.next()).name;
                   b = it.hasNext();
                   i++;
                }
		JComboBox jcb1 = new JComboBox(cat);
                String[] item = {"Select an Item"};
		JComboBox jcb2 = new JComboBox(item);
                jcb2.setMaximumRowCount(4);
                JTextField jtf1 = new JTextField("Name",12);
                UpdateItemName up = new UpdateItemName(jtf1, jcb2);
                jcb1.addActionListener(new UpdateItem(jcb1, jcb2, stock, up));
		gb1.gridx = 0;
		gb1.gridy = 1;
		gb1.insets = in1;
		jcb1.setMaximumRowCount(4);
		child1.add(jcb1, gb1);
		gb1.gridx = 1;
                gb1.ipadx = 50;
                gb1.insets = in3;
                jcb2.addActionListener(up);
                child1.add(jcb2, gb1);
		gb1.gridx = 2;
                gb1.ipadx = 0;
		jtf1.setEditable(false);
		child1.add(jtf1, gb1);
		JSpinner js = new JSpinner(new SpinnerNumberModel(1,0,10000,1));
		gb1.gridx = 3;
		gb1.insets = in2;
		child1.add(js, gb1);
		JButton jb1 = new JButton("ADD");
		gb1.gridx = 0;
		gb1.gridy = 2;
		gb1.gridwidth = 4;
		gb1.insets = in3;
                jb1.setOpaque(true);
		jb1.addActionListener(new StorePurchase(child1, 1, stock));
		child1.add(jb1, gb1);
		GridBagConstraints gb2 = new GridBagConstraints();
		JPanel child2 = new JPanel(new GridBagLayout());
		new ColorTitle(lb1, child2, "New Item");
		gb3.gridy = 1;
		parent.add(child2,gb3);
		gb2.insets = in1;
		JLabel jl5 = new JLabel("Category");
		gb2.gridx = 0;
		gb2.gridy = 0;
		jl5.setFont(f1);
		child2.add(jl5, gb2);
		gb2.insets = in3;
		JLabel jl6 = new JLabel("Item Code");
		gb2.gridx = 1;
		jl6.setFont(f1);
		child2.add(jl6, gb2);
		JLabel jl7 = new JLabel("Name");
		gb2.gridx = 2;
		jl7.setFont(f1);
		child2.add(jl7, gb2);
		JLabel jl8 = new JLabel("Quantity");
		gb2.gridx = 3;
		jl8.setFont(f1);
		child2.add(jl8, gb2);
		gb2.insets = in2;
		JLabel jl9 = new JLabel("Retail Price");
		gb2.gridx = 4;
		jl9.setFont(f1);
		child2.add(jl9, gb2);
		JComboBox jcb3 = new JComboBox(cat);
                jcb3.setMaximumRowCount(4);
		gb2.gridx = 0;
		gb2.gridy = 1;
		gb2.insets = in1;
		child2.add(jcb3, gb2);
		JTextField jtf6 = new JTextField(10);
		gb2.gridx = 1;
		gb2.insets = in3;
		child2.add(jtf6, gb2);
		JTextField jtf3 = new JTextField(10);
		gb2.gridx = 2;
		child2.add(jtf3, gb2);
		JSpinner js1 = new JSpinner(new SpinnerNumberModel(1,0,10000,1));
		gb2.gridx = 3;
		child2.add(js1, gb2);
                SpinnerNumberModel rprice = new SpinnerNumberModel(0.0, 0.0, 150000.0, 0.1);
		JSpinner price = new JSpinner(rprice);
		gb2.gridx = 4;
		gb2.insets = in2;
		child2.add(price, gb2);
		JButton jb2 = new JButton("ADD");
		gb2.gridx = 0;
		gb2.gridy = 2;
		gb2.gridwidth = 5;
		gb2.insets = in3;
		jb2.addActionListener(new StorePurchase(child2, 2, stock));
                jb2.setOpaque(true);
		child2.add(jb2, gb2);
		return parent;
	}
        static void exceptions (String dest, Throwable ex) {
            File file = new File("Errors\\item");
            if(!file.isDirectory())
                file.mkdirs();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream("Errors\\item\\"+dest, true));
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace(pw);
            }
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
        }
}