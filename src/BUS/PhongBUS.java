/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static BUS.PhieuThuePhongBUS.updateTable;
import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.LoaiPhongDAO;
import DAO.PhieuDichVuDAO;
import DAO.PhieuThuePhongDAO;
import DAO.PhongDAO;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.LoaiPhong;
import DTO.PhieuDichVu;
import DTO.PhieuThuePhong;
import DTO.Phong;
import GUI.ThongBao;
import Tools.DateUtil;
import Tools.TableUtil;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Massan
 */
public class PhongBUS {

    public static void init(JTable tbl, JTable tbll) {
        PhongBUS.updateTablebyTang(tbl, -1);

        ArrayList<LoaiPhong> lstlp = LoaiPhongDAO.load();
        PhongBUS.uploadTableLP(tbll, lstlp);
    }

    public ArrayList<Phong> load() {
        return PhongDAO.load();
    }

    public static Phong getPhong(int maphg) {
        return PhongDAO.getPhong(maphg);
    }

    public ArrayList<LoaiPhong> loadLPhg() {
        return LoaiPhongDAO.load();
    }

    public ArrayList<Phong> getphongtrong() {
        ArrayList<Phong> listphongtrong = new ArrayList();
        for (Phong phongtrong : this.load()) {
            if ("trống".equals(phongtrong.getTinhtrang())) {

                listphongtrong.add(phongtrong);
            }
        }
        return listphongtrong;
    }

    public ArrayList<Phong> getphongdangchothue() {
        ArrayList<Phong> listphongdangchothue = new ArrayList();
        for (Phong phongdangthue : this.load()) {
            if ("đang dùng".equals(phongdangthue.getTinhtrang())) {

                listphongdangchothue.add(phongdangthue);
            }
        }
        return listphongdangchothue;
    }

    public ArrayList<Phong> getphongdangsua() {
        ArrayList<Phong> listphongdangsua = new ArrayList();
        for (Phong phongdangsua : this.load()) {
            if ("sửa chữa".equals(phongdangsua.getTinhtrang())) {

                listphongdangsua.add(phongdangsua);
            }
        }
        return listphongdangsua;
    }

    public static String getTenLPhg(int maloaiphong) {
        LoaiPhongDAO loaiphong = new LoaiPhongDAO();
        ArrayList<LoaiPhong> list = loaiphong.load();
        for (LoaiPhong lphg : list) {
            if (lphg.getMaLoaiPhg() == maloaiphong) {
                return lphg.getTenLoaiPhg();
            }
        }
        return "";
    }

    public static int getGiaLPhg(int maloaiphong) {
        LoaiPhongDAO loaiphong = new LoaiPhongDAO();
        ArrayList<LoaiPhong> list = loaiphong.load();
        for (LoaiPhong lphg : list) {
            if (lphg.getMaLoaiPhg() == maloaiphong) {
                return lphg.getGia();
            }
        }
        return 0;
    }

    public static void datphong(JTextField formKHMa, JTable tblPhg) {
        // Kiem tra hoa don khach hang
        int makh = 0;
        if (!formKHMa.getText().isEmpty()) {
            makh = Integer.valueOf(formKHMa.getText());
        }

        if (makh == 0) {
            ThongBao.warning("Chưa chọn khách hàng");
            return;
        }

//		int maphg = TableUtil.getMaFormTable(tblPhg); // FIXME
        if (tblPhg.getSelectedRow() == -1) {
            ThongBao.warning("Chưa chọn phòng");
            return;
        }
        int maphg = (int) tblPhg.getValueAt(tblPhg.getSelectedRow(), 0);

        int manv = TaiKhoanBUS.getUser().getMaNV();

        // Kiem tra dat phong
        Phong phg = PhongDAO.getPhong(maphg);
        if (!"trống".equals(phg.getTinhtrang())) {
            ThongBao.warning("Phòng đang bận");
            return;
        }

        HoaDon hd = HoaDonDAO.getFromMaKH(makh);
        if (hd == null) {
            // Tao hoa don moi
            hd = new HoaDon(HoaDonDAO.getNewID(), makh, manv);
            hd.setNgayLap(DateUtil.getCurDate());
            PhieuThuePhong ptp = new PhieuThuePhong(PhieuThuePhongDAO.getNewID());
            PhieuDichVu pdv = new PhieuDichVu(PhieuDichVuDAO.getNewID());
            ptp.setMaPhg(maphg);
            ptp.setNgayDen(DateUtil.getCurDate());
            ptp.setNgayDi("");
            pdv.getM_madv();
            //pdv.getM_ngaydat(DateUtil.getCurDate());
            pdv.getM_soluong();
            ChiTietHoaDon cthd = new ChiTietHoaDon(ChiTietHoaDonDAO.getNewID());
            cthd.setM_ptp(ptp);
            cthd.setM_pdv(pdv);
            cthd.setM_thanhtien(0);
            PhieuThuePhongDAO.add(ptp);
            //PhieuDichVuDAO.add(null);
            ChiTietHoaDonDAO.add(cthd, hd.getMaHD());
            HoaDonDAO.add(hd);
            phg.setTinhtrang("đang dùng");
            PhongDAO.edit(phg);
            ThongBao.noitice("Đặt thành công");
            return;
        }

        // Them phieu thue phong
        PhieuThuePhong ptp = new PhieuThuePhong(PhieuThuePhongDAO.getNewID());
        ptp.setMaPhg(maphg);
        ptp.setNgayDen(DateUtil.getCurDate());
        ptp.setNgayDi("");
        PhieuThuePhongDAO.add(ptp);

        // Them phieu dich vu
        PhieuDichVu pdv = new PhieuDichVu(PhieuDichVuDAO.getNewID());
        pdv.getM_madv();
        //pdv.getM_ngaydat(DateUtil.getCurDate());
        pdv.getM_soluong();
        PhieuDichVuDAO.add(pdv);
        
        boolean isadd = false;
        for (ChiTietHoaDon cthd : hd.l_chitiet) {
            if (cthd.getM_ptp() == null) {
                cthd.setM_ptp(ptp);
                ChiTietHoaDonDAO.edit(cthd);
                isadd = true;
            }
        }
        if (!isadd) {
            ChiTietHoaDon cthd = new ChiTietHoaDon(ChiTietHoaDonDAO.getNewID());
            cthd.setM_pdv(null);
            cthd.setM_ptp(ptp);
            cthd.setM_thanhtien(0);
            ChiTietHoaDonDAO.add(cthd, hd.getMaHD());
            hd.l_chitiet.add(cthd);
        }

        // Chuyen tinh trang
        phg.setTinhtrang("đang dùng");
        PhongDAO.edit(phg);

        // Thong bao
        ThongBao.noitice("Đặt thành công");
        updateTable(tblPhg);
    }

    public static boolean addphong(JTable tblp, JTable tbllp, JTextField formSoP, JTextField formTT) {
        int sophg = 0;
        if (!formSoP.getText().isEmpty()) {
            sophg = Integer.valueOf(formSoP.getText());
        }

        String tinhtrang = formTT.getText();
        int malphg = TableUtil.getMaFromTable(tbllp);

        if (validateForm(sophg, malphg, tinhtrang)) {
            Phong p = new Phong(sophg);
            p.setMaloaiphg(malphg);
            p.setTinhtrang(tinhtrang);
            PhongDAO.add(p);
            return true;
        }
        return false;
    }

    public static boolean addloaiphong(JTextField formT, JTextField formG, JTextArea formMT) {
        int malphg = LoaiPhongDAO.getNewID();

        String tenl = formT.getText();

        int gia = 0;
        if (!formG.getText().isEmpty()) {
            gia = Integer.valueOf(formG.getText());
        }

        String mota = formMT.getText();

        if (validateForm2(malphg, tenl, gia, mota)) {
            LoaiPhong lp = new LoaiPhong(malphg);
            lp.setTenloaiphg(tenl);
            lp.setGia(gia);
            lp.setMota(mota);
            LoaiPhongDAO.add(lp);
            return true;
        }
        return false;
    }

    public static boolean editphong(JTable tblp, JTable tbllp, JTextField formSoP, JTextField formTT) {
        int sophg = 0;
        if (!formSoP.getText().isEmpty()) {
            sophg = Integer.valueOf(formSoP.getText());
        }

        String tinhtrang = formTT.getText();
        int malphg = TableUtil.getMaFromTable(tbllp);

        if (validateForm(sophg, malphg, tinhtrang)) {
            Phong p = PhongDAO.getPhong(sophg);
            p.setMaloaiphg(malphg);
            p.setTinhtrang(tinhtrang);
            PhongDAO.edit(p);
            return true;
        }
        return false;
    }

    public static boolean editloaiphong(JTable tbll, JTextField formT, JTextField formG, JTextArea formMT) {
        int malphg = TableUtil.getMaFromTable(tbll);

        String tenl = formT.getText();

        int gia = 0;
        if (!formG.getText().isEmpty()) {
            gia = Integer.valueOf(formG.getText());
        }

        String mota = formMT.getText();

        if (validateForm2(malphg, tenl, gia, mota)) {
            LoaiPhong lp = LoaiPhongDAO.getLoaiPhong(malphg);
            lp.setTenloaiphg(tenl);
            lp.setGia(gia);
            lp.setMota(mota);
            LoaiPhongDAO.edit(lp);
            return true;
        }
        return false;
    }

    public static void deletephong(JTable tblp) {
        int maphg = TableUtil.getMaFromTable(tblp);
        if (maphg != 1) {
            PhongDAO.delete(maphg);
        }
    }

    public static void deleteloaiphong(JTable tbllp) {
        int malphg = TableUtil.getMaFromTable(tbllp);
        if (malphg != 1) {
            PhongDAO.deleteloaiphong(malphg);
        }
    }

    public static void uploadTableP(JTable tbl, ArrayList<Phong> lst) {
        String[] columnNames = {"Số phòng", "Tình trạng"};
        Object[][] data = new Object[lst.size()][columnNames.length];

        int i = 0;
        for (Phong p : lst) {
            data[i][0] = p.getMaphg();
            data[i][1] = p.getTinhtrang();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public static void uploadTableLP(JTable tbl, ArrayList<LoaiPhong> lst) {
        String[] columnNames = {"mã", "Tên Loại", "Giá", "Mô tả"};
        Object[][] data = new Object[lst.size()][columnNames.length];

        int i = 0;
        for (LoaiPhong lp : lst) {
            data[i][0] = lp.getMaLoaiPhg();
            data[i][1] = lp.getTenLoaiPhg();
            data[i][2] = lp.getGia();
            data[i][3] = lp.getMota();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public static void updateTablebyTang(JTable tbl, int tang) {
        PhongDAO pDAO = new PhongDAO();
        ArrayList<Phong> lstfull = pDAO.load();

        if (tang == -1) {
            uploadTableP(tbl, lstfull);
        } else {
            ArrayList<Phong> lst = new ArrayList<>();
            for (Phong p : lstfull) {
                if (p.getMaphg() / 100 == tang) {
                    lst.add(p);
                }
            }
            uploadTableP(tbl, lst);
        }
    }

    public static void updateTableL(JTable tbl) {
        ArrayList<LoaiPhong> lst = LoaiPhongDAO.load();
        uploadTableLP(tbl, lst);
    }

    public static void loadInfo(JTable tblp, JTable tbllp, JTextField formSoP, JTextField formTT, JTextField formLP, JTextField formG, JTextArea formMT) {
        int sophg = TableUtil.getMaFromTable(tblp);
        Phong phg = PhongDAO.getPhong(sophg);
        int malphg = phg.getMaloaiphg();
        LoaiPhong lphg = LoaiPhongDAO.getLoaiPhong(malphg);

        formSoP.setText(String.valueOf(phg.getMaphg()));
        formTT.setText(phg.getTinhtrang());
        formLP.setText(lphg.getTenLoaiPhg());
        formG.setText(String.valueOf(lphg.getGia()));
        formMT.setText(lphg.getMota());

        tbllp.setRowSelectionInterval(malphg - 1, malphg - 1);
    }

    public static void loadInfo2(JTable tbllp, JTextField formT, JTextField formG, JTextArea formMT) {
        int malphg = TableUtil.getMaFromTable(tbllp);
        LoaiPhong lphg = LoaiPhongDAO.getLoaiPhong(malphg);

        formT.setText(lphg.getTenLoaiPhg());
        formG.setText(String.valueOf(lphg.getGia()));
        formMT.setText(lphg.getMota());
    }

    private static boolean validateForm(int sophg, int malphg, String tinhtrang) {
        if (sophg == 0 || sophg > 9999) {
            ThongBao.warning("So phong khong hop le");
        } else if (tinhtrang.isEmpty()) {
            ThongBao.warning("Tinh trang phong khong hop le");
        } else if (malphg == -1 || LoaiPhongDAO.getLoaiPhong(malphg) == null) {
            ThongBao.warning("Loai phong khong hop le");
        } else {
            return true;
        }
        return false;
    }

    private static boolean validateForm2(int malphg, String ten, int gia, String mota) {
        if (malphg == -1) {
            ThongBao.warning("Chưa chọn loại phòng");
        } else if (ten.isEmpty()) {
            ThongBao.warning("Ten loai phong khong hop le");
        } else if (gia == 0) {
            ThongBao.warning("gia phong khong hop le");
        } else if (mota.isEmpty()) {
            ThongBao.warning("mo ta khong hop le");
        } else {
            return true;
        }
        return false;
    }

}
