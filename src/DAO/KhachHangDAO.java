/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.KhachHang;
import UTILS.Database;
import GUI.ThongBao;

/**
 *
 * @author
 */
public class KhachHangDAO {

    public static ArrayList<KhachHang> load() {
        ArrayList<KhachHang> l_khachhang = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM khachhang");

        try {
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getInt(4));
                kh.setNgaySinh(rs.getString(5));
                kh.setSoDienThoai(rs.getInt(6));
                kh.setEmail(rs.getString(7));
                kh.setCMND(rs.getInt(8));
                kh.setQuocTich(rs.getString(9));
                l_khachhang.add(kh);
            }
        } catch (SQLException e) {
            ThongBao.error("[KhachHangDAO:load] " + e);
        }

        DB.disconnect();

        return l_khachhang;
    }

    public static KhachHang getKhachHang(int makh) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM khachhang WHERE makh=" + makh);

        try {
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1));

                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getInt(4));
                kh.setNgaySinh(rs.getString(5));
                kh.setSoDienThoai(rs.getInt(6));
                kh.setEmail(rs.getString(7));
                kh.setCMND(rs.getInt(8));
                kh.setQuocTich(rs.getString(9));

                DB.disconnect();

                return kh;
            }
        } catch (SQLException e) {
            ThongBao.error("[KhachHangDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }
    // Them 1 khach hang

    public void add(KhachHang kh) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO  khachhang (ho, ten, gioitinh, ngaysinh, sdt, email, cmnd, quoctich) VALUES ('";
        sql += kh.getHo() + "', '";
        sql += kh.getTen() + "', '";
        sql += kh.getGioiTinh() + "', '";
        sql += kh.getNgaySinh() + "', '";
        sql += kh.getSoDienThoai() + "', '";
        sql += kh.getEmail() + "', '";
        sql += kh.getCMND() + "', '";
        sql += kh.getQuocTich() + "');";

        DB.update(sql);
        DB.disconnect();
    }
    // xóa 1 khahcs hàng

    public void delete(int makh) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM khachhang WHERE makh=" + makh);
        DB.disconnect();
    }
    // Sửa thông tin 

    public void edit(KhachHang kh) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE khachhang SET ";
		sql += "ho='"						+kh.getHo();
		sql += "', ten='"					+kh.getTen();
		sql += "', gioitinh='"				+kh.getGioiTinh();
		sql += "', ngaysinh='"				+kh.getNgaySinh();
		sql += "', sdt='"					+kh.getSoDienThoai();
		sql += "', email='"				+kh.getEmail();
		sql += "', cmnd='"				+kh.getCMND();
		sql += "', quoctich='"				+kh.getQuocTich();
		sql += "' WHERE khachhang.makh = "	+kh.getMaKH()+";";
		
        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(makh) FROM khachhang");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[KhachHangDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<KhachHang> find(String ho, String ten) {
        ArrayList<KhachHang> l_khachhang = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM khachhang WHERE ";

        if (!ho.isEmpty()) {
            sql += "ho='" + ho + "' AND ";
        }
        if (!ten.isEmpty()) {
            sql += "ten='" + ten + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1));

                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setGioiTinh(rs.getInt(4));
                kh.setNgaySinh(rs.getString(5));
                kh.setSoDienThoai(rs.getInt(6));
                kh.setEmail(rs.getString(7));
                kh.setCMND(rs.getInt(8));
                kh.setQuocTich(rs.getString(9));
                l_khachhang.add(kh);
            }
        } catch (SQLException e) {
            System.out.println("[KhachHangDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_khachhang;
    }

	public KhachHang find_sdt(int sdt)
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM KhachHang WHERE sdt="+sdt);
		
		try
		{
			while(rs.next())
			{
				KhachHang kh = new KhachHang(rs.getInt(1));

				kh.setHo(rs.getString(2));
				kh.setTen(rs.getString(3));
				kh.setGioiTinh(rs.getInt(4));
				kh.setNgaySinh(rs.getString(5));
				kh.setSoDienThoai(rs.getInt(6));
				kh.setEmail(rs.getString(7));
				kh.setCMND(rs.getInt(8));
				kh.setQuocTich(rs.getString(9));
				
				DB.disconnect();
				
				return kh;
			}
		}
		catch(SQLException e)
		{
			System.out.println("[KhachHangDAO:find] error sql: "+e);
		}
		
		DB.disconnect();
		
		return null;
	}

	public KhachHang find_cmnd(int cmnd)
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM khachhang WHERE cmnd="+cmnd);
		
		try
		{
			while(rs.next())
			{
				KhachHang kh = new KhachHang(rs.getInt(1));

				kh.setHo(rs.getString(2));
				kh.setTen(rs.getString(3));
				kh.setGioiTinh(rs.getInt(4));
				kh.setNgaySinh(rs.getString(5));
				kh.setSoDienThoai(rs.getInt(6));
				kh.setEmail(rs.getString(7));
				kh.setCMND(rs.getInt(8));
				kh.setQuocTich(rs.getString(9));
				
				DB.disconnect();
				
				return kh;
			}
		}
		catch(SQLException e)
		{
			System.out.println("[KhachHangDAO:find] error sql: "+e);
		}
		
		DB.disconnect();
		
		return null;
	}

	
         public static ArrayList TimKiemkhachhang(String info) throws SQLException{
               ArrayList<KhachHang> findkh =new ArrayList();
               
        String sql="select * from khachhang where makh like '%"+info+"%' or ho like N'%"+info+"%' or ten like N'%"+info+"%' or ngaysinh like N'%"+info+"%' or sdt like '%"+info+"%' or email like N'%"+info+"%' or cmnd like '%"+info+"%'" ;
        Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution(sql);
		
		try
		{
			while(rs.next())
			{
				KhachHang kh = new KhachHang(rs.getInt(1));

				kh.setHo(rs.getString(2));
				kh.setTen(rs.getString(3));
				kh.setGioiTinh(rs.getInt(4));
				kh.setNgaySinh(rs.getString(5));
				kh.setSoDienThoai(rs.getInt(6));
				kh.setEmail(rs.getString(7));
				kh.setCMND(rs.getInt(8));
				kh.setQuocTich(rs.getString(9));
				
				//DB.disconnect();
				
				findkh.add(kh);
			}
                        //return findkh;
		}
		catch(SQLException e)
		{
			System.out.println("[KhachHangDAO:find] error sql: "+e);
		}
		
		DB.disconnect();
		
		return findkh;
    }


}