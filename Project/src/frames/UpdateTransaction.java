package frames;
import business.Visual;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.rmi.RemoteException;
public class UpdateTransaction implements ActionListener{
    static HashMap<String, String> map;
    JPanel jp;
    JComboBox<String> comp;
    int mode;
    Component[] com;
    public UpdateTransaction(JPanel jp, int mode) {
    this.jp = jp;
    this.mode = mode;
}
    @Override
    public void actionPerformed (ActionEvent ae) {
        comp = (JComboBox<String>)ae.getSource();
        com = (Component[])jp.getComponents();
        switch(mode) {
            case 0:
                updateName();
                break;
            case 1:
               if(((JComboBox<String>)com[7]).getSelectedItem() != null)
                   updateMonth();
                break;
            case 2:
                if(((JComboBox<String>)com[8]).getSelectedItem() != null)
                    updateDay();
                break;
        }
    }
    void updateName() {
        JTextField name = (JTextField) com[6];
        String val = map.get((String)comp.getSelectedItem());
        name.setText(val);
    }
    void updateMonth() {
        JComboBox<String> month = (JComboBox<String>) com[8];
        month.removeAllItems();
        String[] str = null;
        try {
            str = Visual.c.obj.directory("Transactions\\"+comp.getSelectedItem());
        } catch (RemoteException ex) {
            AddPanel.exceptions("updateTransactions.txt", ex);
        }
        for(String s : str)
            month.addItem(s);
    }
    void updateDay() {
        JComboBox<String> day = (JComboBox<String>) com[9];
        day.removeAllItems();
        String[] str = null;
        try {
            str = Visual.c.obj.directory("Transactions\\"+((JComboBox<String>)com[7]).getSelectedItem()+"\\" +((JComboBox<String>)com[8]).getSelectedItem());
        } catch (RemoteException ex) {
            AddPanel.exceptions("updateTransactions.txt", ex);
        }
        for(String s : str)
            day.addItem(s);
    }
}