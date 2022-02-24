/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PhieuThuePhongDAO;
import DTO.PhieuThuePhong;
import GUI.ThongBao;
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
 * @author mr.boy
 */
public class PhieuThuePhongBUS {

    public static void init(JTable tbl) {
        updateTable(tbl);
    }

    public static PhieuThuePhong getPhieuThuePhong(int maptp) {
        return PhieuThuePhongDAO.get(maptp);
    }

    public ArrayList<PhieuThuePhong> load() {
        return PhieuThuePhongDAO.load();
    }

    public static void add(int maphg, String ngayden, String ngaydi) {

        PhieuThuePhong ptp = new PhieuThuePhong(PhieuThuePhongDAO.getNewID());
        ptp.setMaPhg(maphg);
        ptp.setNgayDen(ngayden);
        ptp.setNgayDi(ngaydi);
        PhieuThuePhongDAO.add(ptp);

        ThongBao.noitice("Thêm thành công");

    }

    public static void edit(int maptp, int maphg, String ngayden, String ngaydi) {
        PhieuThuePhong ptp = new PhieuThuePhong(maptp);
        ptp.setMaPhg(maphg);
        ptp.setNgayDen(ngayden);
        ptp.getNgayDi(ngaydi);
        PhieuThuePhongDAO.edit(ptp);

        ThongBao.noitice("Sửa thành công");
    }

    public static void uploadTable(JTable tbl, ArrayList<PhieuThuePhong> list) {
        String[] columnNames = {"MãPTP", "Mã P", "Ngày Đến", "Ngày Đi"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (PhieuThuePhong ptp : list) {
            data[i][0] = ptp.getMaPTP();
            data[i][1] = ptp.getMaPHG();
            data[i][2] = ptp.getNgayDen();
            data[i][3] = ptp.getNgayDi();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public static void updateTable(JTable tbl) {
        PhieuThuePhongDAO ptpDao = new PhieuThuePhongDAO();
        ArrayList<PhieuThuePhong> list = PhieuThuePhongDAO.load();
        uploadTable(tbl, list);
    }

    public static void loadInfo(JTable tbl, JTextField formMaPTP, JTextField formMaPhg, JDateChooser formNDen, JDateChooser formNDi) {
        PhieuThuePhong ptp = PhieuThuePhongDAO.get(TableUtil.getMaFromTable(tbl));

        formMaPTP.setText(String.valueOf(ptp.getMaPTP()));
        formMaPhg.setText(String.valueOf(ptp.getMaPHG()));
        formNDen.setDate(DateUtil.convert(ptp.getNgayDen()));
        formNDi.setDate(DateUtil.convert(ptp.getNgayDi()));
    }

}
