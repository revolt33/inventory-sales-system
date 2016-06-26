package item;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
public class ColorTitle extends TitledBorder
{
	public ColorTitle(LineBorder lb1, JPanel jp, String str)
	{
		super(lb1, str, TitledBorder.TOP, TitledBorder.CENTER);
		this.titleColor = new Color(255,0,0);
		this.titleFont = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
		jp.setBorder(this);
	}
}
