package frames;
import javax.swing.*;
import item.*;
import java.io.*;
import stock.*;
import sales.*;
import manage.*;
class AddPanel
{
	static JComponent add(String s, JLabel l)
	{
		if(s.equals("Stock"))
		{
			l.setText("Stock Register");
			return StockRegister.display();
		}
		else if(s.equals("Purchase"))
		{
			l.setText("Purchase Register");
			return PurchaseRegister.display();
		}
		else if(s.equals("Manager"))
		{
			l.setText("Manager Window");
			return Manager.display();
		}
                else if(s.equals("Sales"))
                {
                    l.setText("Sales Window");
                    return SalesRegister.display();
                }
		else
		{
			return new JPanel();
		}
	}
        static void exceptions (String dest, Throwable ex) {
            File file = new File("Errors\\frames");
            if(!file.isDirectory())
                file.mkdirs();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream("Errors\\frames\\"+dest, true));
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace(pw);
            }
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
        }
}