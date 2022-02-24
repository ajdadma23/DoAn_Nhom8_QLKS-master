/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ChiTietHoaDon;
import DTO.PhieuDichVu;
import DTO.PhieuThuePhong;
import GUI.ThongBao;
import UTILS.Database;

/**
 *
 * @author Massan
 */
public class ChiTietHoaDonDAO
{
	public static ArrayList<ChiTietHoaDon> load(int mahd)
	{
		ArrayList<ChiTietHoaDon> l_chitiet = new ArrayList<>();
                //ArrayList<Integer> l_mahd = new ArrayList<>();
		ArrayList<Integer> l_maptp = new ArrayList<>();
		ArrayList<Integer> l_mapdv = new ArrayList<>();
		
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM ChiTietHoaDon WHERE mahd="+mahd);
		
		try
		{
			while(rs.next())
			{
				ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getInt(1));
				cthd.setM_thanhtien(rs.getInt(5));
				
				l_maptp.add(rs.getInt(3));
				l_mapdv.add(rs.getInt(4));
				l_chitiet.add(cthd);
			}
		}
		catch(SQLException e)
		{
			ThongBao.warning("[ChiTietHoaDonDAO:load] "+e);
		}
		
		DB.disconnect();
		
		int sizeArr = l_chitiet.size();
		for(int i=0; i<sizeArr; i++)
		{
			int id = l_maptp.get(i);
			if(id != 0)
				l_chitiet.get(i).setM_ptp(PhieuThuePhongDAO.get(id));
			
			id = l_mapdv.get(i);
			if(id != 0)
				l_chitiet.get(i).setM_pdv(null);
		}
		
		return l_chitiet;
	}
        
	public static ChiTietHoaDon get(int macthd)
	{
		Database DB = new Database();
		DB.connect();
                
                ArrayList<ChiTietHoaDon> l_chitiet = new ArrayList<>();
		ArrayList<Integer> l_maptp = new ArrayList<>();
		ArrayList<Integer> l_mapdv = new ArrayList<>();

		ResultSet rs = DB.execution("SELECT * FROM ChiTietHoaDon WHERE macthd="+macthd);
		
		try
		{
			while(rs.next())
			{
				ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getInt(1));
				cthd.setM_thanhtien(rs.getInt(5));
				
				l_maptp.add(rs.getInt(3));
				l_mapdv.add(rs.getInt(4));
				l_chitiet.add(cthd);
				
				DB.disconnect();
				
				return cthd;
			}
		}
		catch(SQLException e)
		{
			System.out.println("[ChiTietHoaDonDAO:get] error sql: "+e);
		}
		
		DB.disconnect();
		
		return null;
	}
	
	public static ChiTietHoaDon getcthdbypdv(PhieuDichVu pdv)
	{
		Database DB = new Database();
		DB.connect();
		String sql="select cthd.macthd,cthd.mahd,cthd.maptp,cthd.mapdv, cthd.thanhtien\n" +
					"from PhieuDichVu pdv,chitiethoadon cthd\n" +
					"where pdv.macthd=cthd.macthd and pdv.mapdv='"+pdv.getM_mapdv()+"' ";
		ResultSet rs = DB.execution(sql);
		try
		{
			while(rs.next())
			{
				ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getInt(1));
				PhieuThuePhong ptp=PhieuThuePhongDAO.get(rs.getInt(3));
				cthd.setM_pdv(null);
				cthd.setM_ptp(ptp);
				cthd.setM_thanhtien(rs.getInt(5));
				DB.disconnect();
				return cthd;
			}
		}
		catch(SQLException e)
		{
			ThongBao.warning("[ChiTietHoaDonDAO:getcthdbypdv] "+e);
		}
		DB.disconnect();
		return null;
	}
	
	public static void add(ChiTietHoaDon cthd, int mahd)
	{
		Database DB = new Database();
		DB.connect();
		
                if (cthd.getM_pdv() != null)
                {
                    String sql = "INSERT INTO chitiethoadon (mahd, maptp, mapdv, thanhtien) VALUES ('";
                    sql += mahd+"', '";
                    sql += cthd.getM_ptp().getMaPTP()+"', '";
                    sql += cthd.getM_pdv().getM_mapdv()+"', '0');";
                    DB.update(sql);
                } else {
                    String sql = "INSERT INTO chitiethoadon (mahd, maptp, thanhtien) VALUES ('";
                    sql += mahd+"', '";
                    sql += cthd.getM_ptp().getMaPTP()+"', '0');";
                    DB.update(sql);
                }
                
		DB.disconnect();
	}

	public static void delete(int macthd)
	{
		Database DB = new Database();
		DB.connect();
		DB.update("DELETE FROM ChiTietHoaDon WHERE ChiTietHoaDon.macthd="+macthd);
		DB.disconnect();
	}

	public static void edit(ChiTietHoaDon cthd)
	{
		Database DB = new Database();
		DB.connect();
		
		String sql = "UPDATE ChiTietHoaDon SET ";
		sql += "maptp='"							+cthd.getM_ptp().getMaPTP();
		sql += "', mapdv='-1";
		sql += "', thanhtien='"					+cthd.getM_thanhtien();
		sql += "' WHERE ChiTietHoaDon.macthd = "	+cthd.getM_macthd()+";";
		
		DB.update(sql);
		DB.disconnect();
	}
	
	public static int getNewID()
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT MAX(macthd) FROM ChiTietHoaDon");
		
		try
		{
			while(rs.next())
			{
				int newid = rs.getInt(1) + 1;
				DB.disconnect();
				return newid;
			}
		}
		catch(SQLException e)
		{
			ThongBao.warning("[ChiTietHoaDonDAO:getNewID] "+e);
		}
		
		DB.disconnect();
		
		return -1;
	}

}
