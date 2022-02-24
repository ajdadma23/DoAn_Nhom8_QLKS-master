/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DichVuDAO;
import DAO.PhieuDichVuDAO;
import DTO.DichVu;
import DTO.PhieuDichVu;
import Tools.DateUtil;
import Tools.TableUtil;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tuan gh
 */
public class PhieuDichVuBUS {
    public static int getnewidphieudichvu()
    {
        PhieuDichVuDAO pdv =new PhieuDichVuDAO();
       return pdv.getNewID();
    }
    
    public static void init(JTable tbl) {
        updateTable(tbl);
    }

    public static PhieuDichVu getPhieuDichVu( int mapdv) {
        return PhieuDichVuDAO.get(mapdv);
    }
    
    

    public ArrayList<PhieuDichVu> load() {
        return PhieuDichVuDAO.load();
    }

    public static void add(PhieuDichVu pdv)
    {
        PhieuDichVuDAO pdvdao=new PhieuDichVuDAO();
        pdvdao.add(pdv);
    }
    public static void uploadTable(JTable tbl, ArrayList<PhieuDichVu> list) {
        String[] columnNames = {"Mã PDV", "Mã DV", "Ngày Đặt", "Số Lượng"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (PhieuDichVu pdv : list) {
            data[i][0] = pdv.getM_mapdv();
            data[i][1] = pdv.getM_madv();
            data[i][2] = pdv.getM_ngaydat();
            data[i][3] = pdv.getM_soluong();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public static void updateTable(JTable tbl) {
        PhieuDichVuDAO pdvDAO = new PhieuDichVuDAO();
        ArrayList<PhieuDichVu> list = PhieuDichVuDAO.load();
        uploadTable(tbl, list);
    }

    public static void loadInfo(JTable tbl, JTextField formMaPDV, JTextField formMaDV, JDateChooser formNDat, JTextField formSL) {
        PhieuDichVu pdv = PhieuDichVuDAO.get(TableUtil.getMaFromTable(tbl));

        formMaPDV.setText(String.valueOf(pdv.getM_mapdv()));
        formMaDV.setText(String.valueOf(pdv.getM_madv()));
        formNDat.setDate(DateUtil.convert(pdv.getM_ngaydat()));
        formSL.setText(String.valueOf(pdv.getM_soluong()));
    }

    
            
}
