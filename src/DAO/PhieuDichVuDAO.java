/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.PhieuDichVu;
import GUI.ThongBao;
import java.util.ArrayList;
import UTILS.Database;
/**
 *
 * @author Massan
 */
public class PhieuDichVuDAO
{
	public static ArrayList<PhieuDichVu> load()
	{
		Database DB = new Database();
		DB.connect();
		ArrayList<PhieuDichVu> l_phieudv = new ArrayList<>();
		String sql="SELECT * FROM phieudichvu";
		ResultSet rs = DB.execution(sql);
		try {
			while (rs.next())
			{
				PhieuDichVu pdv = new PhieuDichVu(rs.getInt(1));
                                pdv.setM_madv(rs.getInt(2));
				pdv.setM_ngaydat(rs.getString(3));
				pdv.setM_soluong(rs.getInt(4));
				l_phieudv.add(pdv);							
			}
		} catch (SQLException e) {
			ThongBao.warning("[PhieuDichVuDAO:load] " + e);
		}

		DB.disconnect();
		return l_phieudv;
	}
	
	public static PhieuDichVu get(int mapdv)
	{
		Database DB = new Database();
		DB.connect();
		//ArrayList<PhieuDichVu> l_phieudv = new ArrayList<>();
		String sql="SELECT * FROM PhieuDichVu WHERE mapdv=" + mapdv;
		ResultSet rs = DB.execution(sql);
		try {
			while (rs.next())
			{
				PhieuDichVu pdv = new PhieuDichVu(rs.getInt(1));
                                pdv.setM_madv(rs.getInt(2));
				pdv.setM_ngaydat(rs.getString(3));
				pdv.setM_soluong(rs.getInt(4));
				//l_phieudv.add(pdv);							
                                DB.disconnect();
                                return pdv;
			}
			
		} catch (SQLException e) {
			ThongBao.warning("[PhieuDichVuDAO:get] " + e);
			return null;
		}

		DB.disconnect();

		return null;
	}
	
        public void delete(int mapdv)
	{
		Database DB = new Database();
		DB.connect();
		DB.update("DELETE FROM PhieuThuePhong WHERE PhieuDichVu.mapdv="+mapdv);
		DB.disconnect();
	}

	public static void edit(PhieuDichVu pdv)
	{
		Database DB = new Database();
		DB.connect();
		
		String sql = "UPDATE PhieuDichVu SET ";
		sql += "madv='"							+pdv.getM_madv();
		sql += "', ngaydat='"						+pdv.getM_ngaydat();
		sql += "', soluong='"						+pdv.getM_soluong();
		sql += "' WHERE PhieuDichVu.mapdv = "	+pdv.getM_mapdv()+";";
		
		DB.update(sql);
		DB.disconnect();
	}
	public static void add(PhieuDichVu pdv) {
		Database DB = new Database();
		DB.connect();

		String sql = "INSERT INTO phieudichvu (madv, ngaydat, soluong) VALUES ('";
		sql += pdv.getM_madv() + "', '";
		sql += pdv.getM_ngaydat() + "', '";
		sql += pdv.getM_soluong() + "');";
		DB.update(sql);
		DB.disconnect();
	}
	public static int getNewID()
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT MAX(mapdv) FROM phieudichvu");
		
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
			System.out.println("[PhieuDichVuDAO:getNewID] error sql: "+e);
		}
		
		DB.disconnect();
		
		return -1;
	}

}
