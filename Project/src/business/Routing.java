package business;
import frames.*;
import javax.swing.*;
import java.awt.event.*;
import java.rmi.*;
class Routing implements ActionListener
{
	Verification v;
	JTextField jtf;
        JPasswordField jpf;
	String s = "Stock Employee";
	String s1 = "Purchase Employee";
	String s2 = "Manager";
        String s3 = "Sales Employee";
	Routing(Verification v,JTextField jtf, JPasswordField jpf)
	{
		this.v = v;
		this.jtf = jtf;
                this.jpf = jpf;
	}
        @Override
	public void actionPerformed(ActionEvent ae)
	{
            String pass = new String(jpf.getPassword());
            String str = null;
            try {
                try{
                    str = Visual.c.obj.check(jtf.getText(), pass);
                } catch(ClassCastException cce) {
                    Business.exceptions("routing.txt", cce);
                }
            }
            catch (RemoteException ex) {
                Business.exceptions("routing.txt", ex);
            }
            if(str.equals("Invalid User")||str.equals("Invalid Password")||str.equals("In Use"))
            {
                if(str.equals("Invalid User"))
                    str = "Login Unsuccessful!\nReason: User ID is incorrect.";
                else if(str.equals("Invalid Password"))
                    str = "Login Unsuccessful!\nReason: Password is Incorrect.";
                else if (str.equals("In Use"))
                    str = "Login Unsuccessful!\nReason: A user with this id is already logged on.";
                JOptionPane.showMessageDialog(v, str, "Authentication Error", JOptionPane.ERROR_MESSAGE);
            }
            if(str!=null)
            {
               if(str.equals(s))
		{
			v.dispose();
			new CreateFrame("Stock Window").showFrame("Stock");
                        CreateFrame.id = jtf.getText();
		}
		else if(str.equals(s1))
		{
			v.dispose();
			new CreateFrame("Item Purchase Window").showFrame("Purchase");
                        CreateFrame.id = jtf.getText();
		}
		else if(str.equals(s2))
		{
			v.dispose();
			new CreateFrame("Manager Window").showFrame(s2);
                        CreateFrame.id = jtf.getText();
		}
                else if(str.equals(s3))
                {
                    v.dispose();
                    new CreateFrame("Sales Window").showFrame("Sales");
                    CreateFrame.id = jtf.getText();
                }
            }
	}
}