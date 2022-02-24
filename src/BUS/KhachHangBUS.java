/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHang;
import Tools.DateUtil;
import Tools.TableUtil;
import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Massan
 */
public class KhachHangBUS {

    public static KhachHang getKhachHang(int makh) {
        return KhachHangDAO.getKhachHang(makh);
    }

    public static void init(JTable tbl) {
        ArrayList<KhachHang> dskh = KhachHangDAO.load();
        uploadTable(tbl, dskh);
    }

    public ArrayList<KhachHang> find(String ho, String ten) {
        KhachHangDAO khDAO = new KhachHangDAO();
        return khDAO.find(ho, ten);
    }

    public KhachHang find_unique(int sdt, int cmnd) {
        KhachHangDAO khDAO = new KhachHangDAO();

        if (sdt != 0) {
            return khDAO.find_sdt(sdt);
        } else if (cmnd != 0) {
            return khDAO.find_cmnd(cmnd);
        }

        return null;
    }

    public static void uploadTable(JTable tbl, ArrayList<KhachHang> list) {
        String[] columnNames = {"Mã", "Họ", "Tên", "Giới tính", "Ngày sinh", "SĐT", "Email", "CMND", "Quốc Tich"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (KhachHang kh : list) {
            data[i][0] = kh.getMaKH();
            data[i][1] = kh.getHo();
            data[i][2] = kh.getTen();
            if (kh.getGioiTinh() == 0) {
                data[i][3] = "nam";
            } else {
                data[i][3] = "nữ";
            }

            data[i][4] = kh.getNgaySinh();
            data[i][5] = kh.getSoDienThoai();
            data[i][6] = kh.getEmail();
            data[i][7] = kh.getCMND();
            data[i][8] = kh.getQuocTich();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        KhachHangDAO khDAO = new KhachHangDAO();
        ArrayList<KhachHang> dskh = khDAO.load();
        uploadTable(tbl, dskh);
    }

    public static void add(String ho, String ten, int gioitinh, String ngaysinh, int sdt, String email, int cmnd, String quoctich) {
        KhachHangDAO khDAO = new KhachHangDAO();
        //if(find_cmnd(cmnd)!=-1){
        KhachHang kh = new KhachHang(khDAO.getNewID());
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioitinh);
        kh.setNgaySinh(ngaysinh);
        kh.setSoDienThoai(sdt);
        kh.setEmail(email);
        kh.setCMND(cmnd);
        kh.setQuocTich(quoctich);

        khDAO.add(kh);
        //return true;
        //}
        //else
        //    return false;
    }

    // Xóa
    public static void delete(JTable tblkhData) {
        int makh = TableUtil.getMaFromTable(tblkhData);
        if (makh != 1) {
            KhachHangDAO khDAO = new KhachHangDAO();
            khDAO.delete(makh);
        }
    }

    public static void edit(int makh, String ho, String ten, int gioitinh, String ngaysinh, int sdt, String email, int cmnd, String quoctich) {
        KhachHang kh = new KhachHang(makh);
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioitinh);
        kh.setNgaySinh(ngaysinh);
        kh.setSoDienThoai(sdt);
        kh.setEmail(email);
        kh.setCMND(cmnd);
        kh.setQuocTich(quoctich);

        KhachHangDAO khDAO = new KhachHangDAO();
        khDAO.edit(kh);
    }

    public ArrayList<KhachHang> filter(ArrayList<KhachHang> listKH, String ho, String ten, int gioitinh, String ngaysinh, String quoctich) {
        if (!ho.isEmpty()) {
            for (KhachHang kh : listKH) {
                if (kh.getHo() != ho) {
                    listKH.remove(kh);
                }
            }
        } else if (!ten.isEmpty()) {
            for (KhachHang kh : listKH) {
                if (kh.getTen() != ten) {
                    listKH.remove(kh);
                }
            }
        } else if (gioitinh >= 0) {
            for (KhachHang kh : listKH) {
                if (kh.getGioiTinh() != gioitinh) {
                    listKH.remove(kh);
                }
            }
        } else if (!ngaysinh.isEmpty()) {
            for (KhachHang kh : listKH) {
                if (kh.getNgaySinh() != ngaysinh) {
                    listKH.remove(kh);
                }
            }
        } else if (!quoctich.isEmpty()) {
            for (KhachHang kh : listKH) {
                if (kh.getQuocTich() != quoctich) {
                    listKH.remove(kh);
                }
            }
        }

        return listKH;
    }

    public static void TimKiemkh(JTable tbl, String info) throws SQLException {
        ArrayList<KhachHang> dskh = DAO.KhachHangDAO.TimKiemkhachhang(info);
        uploadTable(tbl, dskh);
    }

    public static void LoadTablekhdp(JTable tbl, String inf) throws SQLException {
        ArrayList<KhachHang> dskh = DAO.KhachHangDAO.TimKiemkhachhang(inf);

        String[] columnNames = {"Mã kh", "Họ", "Tên", "CMNN"};
        Object[][] data = new Object[dskh.size()][4];
        int i = 0;
        for (KhachHang kh : dskh) {
            data[i][0] = kh.getMaKH();
            data[i][1] = kh.getHo();
            data[i][2] = kh.getTen();
            data[i][3] = kh.getCMND();

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public static KhachHang getkhbyid(int makh) {
        ArrayList<KhachHang> list = KhachHangDAO.load();
        for (KhachHang kh : list) {
            if (kh.getMaKH() == makh) {
                return kh;
            }
        }
        return null;
    }

    
    public static void loadInfo(JTable tblkhData, JTextField formMa, JTextField formHo, JTextField formTen, JComboBox<String> formGt, JDateChooser formNS, JTextField formSDT, JTextField formEmail, JTextField formCMND, JTextField formQT) {
        KhachHang kh = KhachHangDAO.getKhachHang(TableUtil.getMaFromTable(tblkhData));
        formMa.setText(String.valueOf(kh.getMaKH()));
        formHo.setText(kh.getHo());
        formTen.setText(kh.getTen());
        formGt.setSelectedIndex(kh.getGioiTinh());
        formNS.setDate(DateUtil.convert(kh.getNgaySinh()));
        formSDT.setText('0' + String.valueOf(kh.getSoDienThoai()));
        formEmail.setText(kh.getEmail());
        formCMND.setText(String.valueOf(kh.getCMND()));
        formQT.setText(kh.getQuocTich());
    }

}
