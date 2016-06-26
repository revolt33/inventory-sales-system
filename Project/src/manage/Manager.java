package manage;
import javax.swing.*;
import java.awt.*;
import item.*;
import stock.*;
import sales.*;
public class Manager
{
	public static JPanel display()
	{
		JPanel parent = new JPanel();
		parent.setLayout(new BorderLayout());
		JPanel northChild = new JPanel();
		northChild.setLayout(new FlowLayout(FlowLayout.CENTER,60,30));
		JLabel jl1 = new JLabel("Stock Register");
		JLabel jl2 = new JLabel("Purchase Register");
		JLabel jl3 = new JLabel("Sales Register");
                JLabel jl4 = new JLabel("Add User");
		Font f1 = new Font(Font.SERIF, Font.ROMAN_BASELINE,30);
		jl1.setFont(f1);
		jl1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jl1.setForeground(new Color(120,0,120));
		jl2.setFont(f1);
		jl2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jl2.setForeground(new Color(120,0,120));
		jl3.setFont(f1);
		jl3.setForeground(new Color(120,0,120));
                jl3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jl4.setFont(f1);
		jl4.setForeground(new Color(120,0,120));
                jl4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		northChild.add(jl1);
		northChild.add(jl2);
		northChild.add(jl3);
                northChild.add(jl4);
		parent.add(northChild, BorderLayout.NORTH);
		JPanel center = new JPanel();
		CardLayout card = new CardLayout(10,10);
		center.setLayout(card);
		center.add(StockRegister.display(), "stock");
		center.add(PurchaseRegister.display(), "purchase");
                center.add(SalesRegister.display(), "sales");
                center.add(IntermediatePanel.display(), "add");
		jl1.addMouseListener(new ShowPanel("stock", center, card, jl1));
		jl2.addMouseListener(new ShowPanel("purchase", center, card, jl2));
                jl3.addMouseListener(new ShowPanel("sales", center, card, jl3));
                jl4.addMouseListener(new ShowPanel("add", center, card, jl4));
		parent.add(center, BorderLayout.CENTER);
		return parent;
	}
}