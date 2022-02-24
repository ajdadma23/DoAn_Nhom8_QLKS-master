/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoan;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.TitledBorder;

/**
 *
 * @author tuan gh
 */

    
public class DangNhapGUI extends JFrame{	
	public static JTextField txtTaiKhoan,txtMatKhau;
	 JButton btnDangNhap,btnThoat;
	public DangNhapGUI(String title) {
		super(title);
		addControl();
		addEvents();	
	}

	private void addControl() {
            
		Container conn= getContentPane();
		JPanel pnMain=new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		conn.add(pnMain);
		JPanel pnTitle=new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitle=new JLabel("Đăng nhập hệ thống ");
		lblTitle.setForeground(Color.BLUE);
		Font fontTitle=new Font("arial", Font.BOLD, 18);
		lblTitle.setFont(fontTitle);
		pnTitle.add(lblTitle);
		pnMain.add(pnTitle);
		
		JPanel pnDangNhap=new JPanel();
		pnDangNhap.setLayout(new BoxLayout(pnDangNhap, BoxLayout.Y_AXIS));
		pnMain.add(pnDangNhap);
		
		JPanel pnTaiKhoan=new JPanel();
		pnTaiKhoan.setLayout(new FlowLayout());
		JLabel lblTaiKhoan=new JLabel("Tài khoản");
		txtTaiKhoan=new JTextField(20);
		pnTaiKhoan.add(lblTaiKhoan);
		pnTaiKhoan.add(txtTaiKhoan);
		pnDangNhap.add(pnTaiKhoan);
		
		JPanel pnMatKhau=new JPanel();
		pnMatKhau.setLayout(new FlowLayout());
		JLabel lblMatKhau=new JLabel("Mật Khẩu");
		txtMatKhau=new JPasswordField(20);
		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(txtMatKhau);
		pnDangNhap.add(pnMatKhau);
                
                txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        try {
                            if(evt.getKeyCode() == 10)
                            {
                                AutomaticallyClosedMsgBox(1500, "Đang đăng nhập...");
                                xuLyDangNhap();
                            }
                       } catch (SQLException ex) {
                           Logger.getLogger(DangNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                });
		
		JPanel pnButton=new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnDangNhap= new JButton("Đăng nhập");
		btnThoat=new JButton("Thoát");
		pnButton.add(btnDangNhap);
		pnButton.add(btnThoat);
		pnDangNhap.add(pnButton);
                btnThoat.setPreferredSize(btnDangNhap.getPreferredSize());
		TitledBorder border=new TitledBorder(BorderFactory.createLineBorder(Color.red),
				"Thông tin đăng nhập ");
		pnDangNhap.setBorder(border);
	}

	private void addEvents() {
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                            try {
                                 AutomaticallyClosedMsgBox(1500, "Đang đăng nhập...");
                                xuLyDangNhap();
                            } catch (SQLException ex) {
                                Logger.getLogger(DangNhapGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
				
			}
		});
                btnThoat.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
		
	}
	protected void xuLyDangNhap() throws SQLException  {
		
		if(TaiKhoanBUS.login(txtTaiKhoan.getText(), txtMatKhau.getText()))
		{
                    this.dispose();
                    if (TaiKhoanBUS.getUser().getQuyen()==1)
                    {
                        QuanLyGUI ql=new QuanLyGUI();
                        ql.setVisible(true);
                    }
                    else
                    {
                        LeTanGUI nv= new LeTanGUI();
                        //System.out.println(tk.getMaNV());
                        nv.setVisible(true);
                    }
		}
                else
                {
                    ThongBao.noitice("Đăng nhập thất bại ");
                }
	}
        public void AutomaticallyClosedMsgBox(long time, String text) {
        JOptionPane jop = new JOptionPane();
        jop.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        jop.setMessage(text);
        JDialog dialog = jop.createDialog(null, "Thông báo");

        // Set timer
        new Thread(() -> {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            dialog.dispose();
        }).start();

        dialog.setVisible(true);
    }

	public void showWindow() {
                this.setTitle("quản lý khách sạn");
		this.setSize(350, 250);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

    

}


