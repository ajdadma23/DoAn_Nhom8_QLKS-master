/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.DichVuBUS;
import BUS.HoaDonBUS;
import BUS.PhieuDichVuBUS;
import BUS.PhongBUS;
import DAO.ChiTietHoaDonDAO;
import DAO.PhieuDichVuDAO;
import DTO.ChiTietHoaDon;
import DTO.DichVu;
import DTO.HoaDon;
import DTO.PhieuDichVu;
import DTO.PhieuThuePhong;
import Tools.DateUtil;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tuan gh
 */
public class DatDichVuGUI extends javax.swing.JDialog {

    public static ArrayList<DTO.DichVu> l_dichvu = new ArrayList<DTO.DichVu>();
    public static ArrayList<Integer> l_soluong = new ArrayList<Integer>();

    /**
     * Creates new form datdichvu
     *
     * @param parent
     * @param modal
     */
    public DatDichVuGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DichVuBUS dvbus = new DichVuBUS();
        for (DichVu dv : dvbus.load()) {
            cbbcacdichvu.addItem(dv.getTenDV());
        }
        this.pack();
        setLocationRelativeTo(null);

        //txtmaphgdatdv.setText(LeTanGUI.it.);
        String makh = LeTanGUI.it.getmakhdatphong();
        String maphg = LeTanGUI.it.getmaphgcandatdv();
        txtgiadv.setEditable(false);
        txtmaphgdatdv.setEditable(false);
        HoaDonBUS hd = new HoaDonBUS();
        PhongBUS phg = new PhongBUS();
        HoaDon hdkh = hd.gethoadonbymakh(Integer.valueOf(makh));
        txtmaphgdatdv.setText(maphg);

        for (ChiTietHoaDon cthd : hdkh.l_chitiet) {
            if (cthd.getM_ptp().getMaPHG() == Integer.valueOf(maphg)) {
                PhieuDichVuBUS pdvBUS = new PhieuDichVuBUS();
                PhieuDichVu pdv = new PhieuDichVu(BUS.PhieuDichVuBUS.getnewidphieudichvu());
                cthd.setM_pdv(pdv);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        pnCanHo = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldichvudachon = new javax.swing.JTable();
        pnKhu = new javax.swing.JPanel();
        txtgiadv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsoluongdat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmaphgdatdv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbbcacdichvu = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        pnBT = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnthoatdv = new javax.swing.JButton();
        btnxoadichvu = new javax.swing.JButton();
        btndat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("đặt dịch vụ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnCanHo.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(229, 70, 70));
        jLabel9.setText("dịch vụ đã chọn");

        tbldichvudachon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tên dịch vụ", "giá", "số lượng", "ngày đặt"
            }
        ));
        jScrollPane1.setViewportView(tbldichvudachon);

        javax.swing.GroupLayout pnCanHoLayout = new javax.swing.GroupLayout(pnCanHo);
        pnCanHo.setLayout(pnCanHoLayout);
        pnCanHoLayout.setHorizontalGroup(
            pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCanHoLayout.createSequentialGroup()
                .addGroup(pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCanHoLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel9))
                    .addGroup(pnCanHoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnCanHoLayout.setVerticalGroup(
            pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCanHoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnKhu.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        pnKhu.setForeground(new java.awt.Color(0, 90, 227));
        pnKhu.setPreferredSize(new java.awt.Dimension(326, 237));

        txtgiadv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("giá");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("số lượng");

        txtsoluongdat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("dịch vụ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("mã phòng");

        txtmaphgdatdv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(229, 70, 70));
        jLabel10.setText("đặt dịch vụ");

        cbbcacdichvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-dịch vụ-" }));
        cbbcacdichvu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbcacdichvuItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnKhuLayout = new javax.swing.GroupLayout(pnKhu);
        pnKhu.setLayout(pnKhuLayout);
        pnKhuLayout.setHorizontalGroup(
            pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuLayout.createSequentialGroup()
                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnKhuLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtgiadv, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnKhuLayout.createSequentialGroup()
                                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnKhuLayout.createSequentialGroup()
                                        .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(4, 4, 4))
                                    .addGroup(pnKhuLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtsoluongdat, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmaphgdatdv)
                                    .addComponent(cbbcacdichvu, 0, 200, Short.MAX_VALUE)))))
                    .addGroup(pnKhuLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel10)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pnKhuLayout.setVerticalGroup(
            pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addGap(35, 35, 35)
                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmaphgdatdv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbbcacdichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgiadv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(pnKhuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsoluongdat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnthoatdv.setText("Thoát");
        btnthoatdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatdvActionPerformed(evt);
            }
        });

        btnxoadichvu.setText("xóa");
        btnxoadichvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoadichvuActionPerformed(evt);
            }
        });

        btndat.setText("Đặt");
        btndat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBTLayout = new javax.swing.GroupLayout(pnBT);
        pnBT.setLayout(pnBTLayout);
        pnBTLayout.setHorizontalGroup(
            pnBTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBTLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnxoadichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(btndat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnthoatdv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        pnBTLayout.setVerticalGroup(
            pnBTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBTLayout.createSequentialGroup()
                .addGroup(pnBTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthoatdv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoadichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(pnBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnKhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnKhu, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(pnCanHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthoatdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatdvActionPerformed
        LeTanGUI.it.enabledFrame();
        this.dispose();
    }//GEN-LAST:event_btnthoatdvActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        LeTanGUI.it.enabledFrame();
    }//GEN-LAST:event_formWindowClosing

    private void cbbcacdichvuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbcacdichvuItemStateChanged
        DichVuBUS dvbus = new DichVuBUS();
        int selected = cbbcacdichvu.getSelectedIndex();
        if (selected == 0) {
//            System.out.println(selected);
            txtgiadv.setText("");
        } else {
            dvbus.load().forEach((dvc) -> {
                if (selected == dvc.getMaDV()) {
                    txtgiadv.setText(String.valueOf(dvc.getGia()));
                }
            });
        }


    }//GEN-LAST:event_cbbcacdichvuItemStateChanged

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        if (cbbcacdichvu.getSelectedIndex() == 0) {
            ThongBao.noitice("chưa chọn dịch vu nào.");
        } else if (txtsoluongdat.getText().isEmpty() || RegexExpression.issoluong(txtsoluongdat.getText()) == false) {
            ThongBao.noitice("số lượng không hợp lệ.");
        } else {
            DichVu dv = BUS.DichVuBUS.getDichVu(cbbcacdichvu.getSelectedIndex());
            boolean isnew = true;

            for (DichVu dv2 : l_dichvu) {
                if (dv2.getMaDV() == dv.getMaDV()) {
                    int index = l_dichvu.indexOf(dv2);
                    l_soluong.set(index, Integer.valueOf(txtsoluongdat.getText()));
                    isnew = false;
                    break;
                }
            }
            if (isnew) {
                l_dichvu.add(dv);
                l_soluong.add(Integer.valueOf(txtsoluongdat.getText()));
            }

            String[] columnNames = {"Tên dịch vu", "Giá", "Số Lượng", "ngày đặt"};
            Object[][] data = new Object[l_dichvu.size()][columnNames.length];

            int dvsize = l_dichvu.size();
            for (int i = 0; i < dvsize; i++) {
                dv = l_dichvu.get(i);
                data[i][0] = dv.getTenDV();
                data[i][1] = dv.getGia();
                data[i][2] = l_soluong.get(i);
                data[i][3] = DateUtil.getCurDate();
            }
            TableModel tableModel = new DefaultTableModel(data, columnNames);
            // TODO set editable column 3
            tbldichvudachon.setModel(tableModel);
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoadichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoadichvuActionPerformed
        if (tbldichvudachon.getSelectedRow() == -1) {
            ThongBao.noitice("Chọn dịch vụ cần xóa.");
        } else {

            l_dichvu.remove(tbldichvudachon.getSelectedRow());
            l_soluong.remove(tbldichvudachon.getSelectedRow());
            String[] columnNames = {"Tên dịch vu", "Giá", "Số Lượng", "ngày đặt"};
            Object[][] data = new Object[l_dichvu.size()][columnNames.length];
            int dvsize = l_dichvu.size();
            for (int i = 0; i < dvsize; i++) {
                DichVu dv = l_dichvu.get(i);
                data[i][0] = dv.getTenDV();
                data[i][1] = dv.getGia();
                data[i][2] = l_soluong.get(i);
                data[i][3] = DateUtil.getCurDate();
            }
            TableModel tableModel = new DefaultTableModel(data, columnNames);
            // TODO set editable column 3
            tbldichvudachon.setModel(tableModel);
        }
    }//GEN-LAST:event_btnxoadichvuActionPerformed

    private void btndatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatActionPerformed

        HoaDon hd = HoaDonBUS.gethoadonbymakh(Integer.valueOf(LeTanGUI.it.getmakhdatphong()));
        int ma_phg = Integer.valueOf(LeTanGUI.it.getmaphgcandatdv());
        if (l_dichvu.isEmpty()) {
            ThongBao.noitice("chưa chọn dịch vụ nào.");

        } else {
            DichVuBUS.thue(l_dichvu, l_soluong, ma_phg, hd.getMaHD());
            l_dichvu.removeAll(l_dichvu);
            l_soluong.removeAll(l_soluong);
            LeTanGUI.it.enabledFrame();
            this.dispose();
        }
/*        while (!l_dichvu.isEmpty())
        {
            DichVu dv = l_dichvu.get(0);
            
            boolean isadd = false;
            ArrayList<ChiTietHoaDon> list = ChiTietHoaDonDAO.load(ma_phg);
            for (ChiTietHoaDon cthd : list)
            {
                if (dv.getMaDV() == cthd.getM_pdv().getM_madv())
                {
                    int soluong = cthd.getM_pdv().getM_soluong();
                    soluong += l_soluong.get(0);
                    cthd.getM_pdv().setM_soluong(soluong);
					PhieuDichVu pdv=cthd.getM_pdv();
                    PhieuDichVuDAO.edit(pdv);
                }
                isadd = true;
            }
            if(!isadd)
                for (ChiTietHoaDon cthd : list)
                {
                    if (cthd.getM_pdv() == null)
                    {
                        PhieuDichVu pdv = new PhieuDichVu(PhieuDichVuDAO.getNewID());
                        pdv.getM_madv();
                        pdv.setM_ngaydat(DateUtil.getCurDate());
                        pdv.setM_soluong(l_soluong.get(0));
                        cthd.setM_pdv(pdv);
						PhieuDichVuDAO.add(pdv);
						ChiTietHoaDonDAO.edit(cthd);
                        isadd = true;
                    }
                    
                }
            if(!isadd)
            {
                PhieuDichVu pdv = new PhieuDichVu(0, dv.getMaDV());
                pdv.setM_ngaydat(DateUtil.getCurDate());
                pdv.setM_soluong(l_soluong.get(0));
                
                ChiTietHoaDon cthdnew = new ChiTietHoaDon(0);
                cthdnew.setM_pdv(pdv);
                cthdnew.setM_ptp(null);
                cthdnew.setM_thanhtien(0);
                
                hd.l_chitiet.add(cthdnew);
                
                PhieuDichVuDAO.add(pdv);
                ChiTietHoaDonDAO.add(cthdnew,hd.getMaHD());
            }
        
            l_dichvu.remove(0);
            l_soluong.remove(0);
        }
        */
    }//GEN-LAST:event_btndatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatDichVuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatDichVuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatDichVuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatDichVuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            DatDichVuGUI dialog = new DatDichVuGUI(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndat;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthoatdv;
    private javax.swing.JButton btnxoadichvu;
    private javax.swing.JComboBox<String> cbbcacdichvu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnBT;
    private javax.swing.JPanel pnCanHo;
    private javax.swing.JPanel pnKhu;
    private javax.swing.JTable tbldichvudachon;
    private javax.swing.JTextField txtgiadv;
    private javax.swing.JTextField txtmaphgdatdv;
    private javax.swing.JTextField txtsoluongdat;
    // End of variables declaration//GEN-END:variables
}
