package frames;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import business.Visual;
import java.util.*;
import fileStructure.*;
import java.rmi.RemoteException;

public class ShowTransactions implements ActionListener {

    JPanel north, center;

    public ShowTransactions(JPanel... jp) {
        north = jp[0];
        center = jp[1];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Component[] com = north.getComponents();
        String query;
        if (((JComboBox<String>) com[7]).getSelectedItem() != null && ((JComboBox<String>) com[8]).getSelectedItem() != null && ((JComboBox<String>) com[9]).getSelectedItem() != null) {
            query = "Transactions\\" + ((JComboBox<String>) com[7]).getSelectedItem() + "\\" + ((JComboBox<String>) com[8]).getSelectedItem() + "\\" + ((JComboBox<String>) com[9]).getSelectedItem() + "\\" + ((JComboBox<String>) com[5]).getSelectedItem();
            ArrayList<TransactionDetails> transact = null;
            try {
                transact = Visual.c.obj.reportTransactions(query);
            } catch (RemoteException ex) {
                AddPanel.exceptions("showTransactions.txt", ex);
            }
            if (transact != null) {
                JPanel jp = new JPanel(new GridBagLayout());
                JScrollPane jsp = new JScrollPane(jp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                center.removeAll();
                center.setLayout(new BorderLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                Insets in = new Insets(15, 0, 15, 0);
                gbc.insets = in;
                ListIterator<TransactionDetails> it = transact.listIterator();
                while (it.hasNext()) {
                    TransactionDetails transactionDetails = it.next();
                    DesignTable table = new DesignTable(transactionDetails.set);
                    JTable addTable = new JTable(table);
                    addTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
                    addTable.setRowHeight(25);
                    addTable.setShowGrid(false);
                    addTable.setShowHorizontalLines(false);
                    addTable.setShowVerticalLines(true);
                    addTable.setSelectionForeground(Color.yellow);
                    addTable.setSelectionBackground(new Color(50, 50, 255));
                    addTable.setGridColor(new Color(170, 255, 40));
                    JScrollPane scroll = new JScrollPane(addTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
                    JLabel time = new JLabel(transactionDetails.time);
                    JLabel display = new JLabel("Time:");
                    Font f1 = new Font(Font.SERIF, Font.CENTER_BASELINE, 15);
                    Color color = new Color(240, 250, 0);
                    time.setForeground(color);
                    time.setFont(f1);
                    display.setForeground(color);
                    display.setFont(f1);
                    top.add(display);
                    top.add(time);
                    top.setBackground(Color.BLUE);
                    top.setOpaque(true);
                    JPanel main = new JPanel(new BorderLayout());
                    main.add(top, BorderLayout.NORTH);
                    main.add(scroll, BorderLayout.CENTER);
                    jp.add(main, gbc);
                    center.add(jsp, BorderLayout.CENTER);
                    center.invalidate();
                    center.validate();
                    gbc.gridy++;
                }
                
            } else {
                JLabel label = new JLabel("No Transactions to display!");
                center.removeAll();
                center.invalidate();
                center.validate();
                center.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = 0;
                center.add(label, gbc);
                center.invalidate();
                center.validate();
            }
        }
    }
}