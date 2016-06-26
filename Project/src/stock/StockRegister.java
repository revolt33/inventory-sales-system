package stock;
import javax.swing.*;
import java.awt.*;
public class StockRegister
{
	public static JComponent display()
        {
		JPanel child = new JPanel();
		child.setLayout(new BorderLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		ViewStock.display(child);
		return child;
	}
}