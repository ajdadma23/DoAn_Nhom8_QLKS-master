/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.PhieuDichVuDAO;
import DAO.PhieuThuePhongDAO;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.PhieuDichVu;
import DTO.PhieuThuePhong;
import Tools.DateUtil;
import Tools.TableUtil;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Massan
 */
public class HoaDonBUS {
     public static void init(JTable tbl) {
        updateTable(tbl);
    }

     public ArrayList<HoaDon> load() {
        return HoaDonDAO.load();
    }
     
    public void traPhong(int makh) {
        HoaDon hd = HoaDonDAO.getFromMaKH(makh);
        hd.l_chitiet = ChiTietHoaDonDAO.load(hd.getMaHD());

        for (ChiTietHoaDon cthd : hd.l_chitiet) {
            PhieuThuePhong ptp = cthd.getM_ptp();
            ptp.setNgayDi(DateUtil.getCurDate());
            PhieuThuePhongDAO.edit(ptp);
        }
    }

    public void thanhToan(int makh) {
//		HoaDonDAO hdDAO = new HoaDonDAO();
//		ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();
//		PhongDAO phgDAO = new PhongDAO();
//		DichVuDAO dvDAO = new DichVuDAO();
//		
//		HoaDon hd = hdDAO.getFromMaKH(makh);
//		hd.l_chitiet = cthdDAO.get(hd.getMaHD());
//		
//		for(ChiTietHoaDon cthd : hd.l_chitiet)
//		{
//			int gia = phgDAO.getGia(cthd.getPhieuThuePhong().getMaPHG());
//			
//			if(cthd.getPhieuDichVu() != null)
//				gia += dvDAO.getGia(cthd.getPhieuDichVu().getMaDV());
//			
//			cthd.setThanhtien(gia);
//			
//			hd.setTongtien(hd.getTongtien() + gia);
//			
//			cthdDAO.edit(cthd);
//		}
//		
//		hdDAO.edit(hd);
    }

    public static HoaDon gethoadonbymakh(int makh) {
        return HoaDonDAO.getFromMaKH(makh);
    }

    public static void find(JTable tbl, JTextField formKH, JTextField formNV) {
        int makh = 0, manv = 0, gia1 = 0, gia2 = 0, maphg = 0, madv = 0;
        String nl1 = "";
        if (!formKH.getText().isEmpty()) {
            makh = Integer.valueOf(formKH.getText());
        }
        if (!formNV.getText().isEmpty()) {
            manv = Integer.valueOf(formNV.getText());
        }

        ArrayList<HoaDon> l_hoadon = HoaDonDAO.find(makh, manv);
        //uploadTable(tbl, l_hoadon);
        //updateTable(tbl);
    }

    public static void uploadTable(JTable tbl, ArrayList<HoaDon> list) {
        String[] columnNames = {"Mã HD", "Mã KH", "Mã NV", "Ngày Lập", "Tổng Tiền"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (HoaDon hd : list) {
            data[i][0] = hd.getMaHD();
            data[i][1] = hd.getMaKH();
            data[i][2] = hd.getMaNV();
            data[i][3] = hd.getNgayLap();
            data[i][4] = hd.getTongtien();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public static void updateTable(JTable tbl) {
        HoaDonDAO hdDao = new HoaDonDAO();
        ArrayList<HoaDon> list = HoaDonDAO.load();
        uploadTable(tbl, list);
    }

    public static void loadInfo(JTable tbl, JTextField formMaKH, JTextField formMaNV) {
        HoaDon hd = HoaDonDAO.getHoaDon(TableUtil.getMaFromTable(tbl));

        //formMaHD.setText(String.valueOf(hd.getMaHD()));
        formMaKH.setText(String.valueOf(hd.getMaKH()));
        formMaNV.setText(String.valueOf(hd.getMaNV()));
        

    }

}
