package sales;
import business.*;
import fileStructure.*;
import frames.CreateFrame;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.rmi.RemoteException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;
public class Debit extends MouseAdapter{
    JSpinner[] qty;
    JComboBox<String>[] cat, item;
    JDialog jd;
    TransactionDetails detail;
    PrintBillBean bean[];
    String name, time, total;
    Debit (JSpinner[] qty, JDialog jd , TransactionDetails detail, PrintBillBean bean[],String total ,String name , String time, JComboBox<String>[]... com) {
        this.qty = qty;
        this.jd = jd;
        this.cat = com[0];
        this.item = com[1];
        this.detail = detail;
        this.bean = bean;
        this.name = name;
        this.time = time;
        this.total = total;
    }
    @Override
    public void mouseReleased (MouseEvent me) {
        String str[][] = new String[2][cat.length];
        int[] num = new int[cat.length];
        for (int i = 0; i<cat.length; i++) {
            str[0][i] = (String)cat[i].getSelectedItem();
            str[1][i] = (String)item[i].getSelectedItem();
            num[i] = (int)qty[i].getValue();
        }
        jd.dispose();
        try {
            if(Visual.c.obj.debit(str[0], str[1], num)) {
                Visual.c.obj.transaction(detail);
                billing();
            }
            else
                show();
        } catch (RemoteException ex) {
            exceptions("debit", ex);
        }
    }
    void billing() {
        Calendar date = Calendar.getInstance();
        time = ""+date.get(Calendar.DAY_OF_MONTH)+" "+date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+", "+date.get(Calendar.YEAR)+"("+time+")";
        String reportSource = "bill.jrxml";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("time", time);
        params.put("total", total);
        JRBeanArrayDataSource dataSource;
        dataSource = new JRBeanArrayDataSource(bean);
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            exceptions("debit.txt", ex);
        }
    }
    void show () {
        String msg = "Some of the Items are not present in Stock!";
        JOptionPane.showMessageDialog(CreateFrame.jf, msg, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    static void exceptions(String dest, Throwable ex) {
        File file = new File("Errors\\sales");
            if(!file.isDirectory())
                file.mkdirs();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream("Errors\\sales\\"+dest, true));
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace(pw);
            }
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
    }
}