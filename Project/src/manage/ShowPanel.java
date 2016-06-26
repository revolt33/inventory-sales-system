package manage;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class ShowPanel extends MouseAdapter
{
	String str;
	JComponent show;
	CardLayout card;
	JLabel jl;
	JPanel jp;
	ShowPanel(String str, JComponent show, CardLayout card, JLabel jl)
	{
		this.str = str;
		this.show = show;
		this.card = card;
		this.jl = jl;
	}
        @Override
	public void mouseClicked(MouseEvent me)
	{
		if(str=="stock")
			card.show(show, "stock");
		else if(str=="purchase")
			card.show(show, "purchase");
                else if(str=="sales")
                    card.show(show, "sales");
                else if(str=="add")
                    card.show(show, "add");
		else
                return;
	}
        @Override
	public void mouseEntered(MouseEvent me)
	{
		jl.setForeground(new Color(255,0,0));
	}
        @Override
	public void mouseExited(MouseEvent me)
	{
		jl.setForeground(new Color(120,0,120));
	}
}