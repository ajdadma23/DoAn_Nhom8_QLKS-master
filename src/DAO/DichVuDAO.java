/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DichVu;
import GUI.ThongBao;
import UTILS.Database;

/**
 *
 * @author Massan
 */
public class DichVuDAO
{
	public static ArrayList<DichVu> load()
	{
		ArrayList<DichVu> l_dichvu = new ArrayList<>();
		
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM dichvu");
		
		try
		{
			while(rs.next())
			{
				DichVu dv = new DichVu(rs.getInt(1));

				dv.setTenDV(rs.getString(2));
				dv.setMoTa(rs.getString(3));
				dv.setGia(rs.getInt(4));
				l_dichvu.add(dv);
			}
		}
		catch(SQLException e)
		{
			ThongBao.warning("[dichvuDAO:load] "+e);
		}
		
		DB.disconnect();
		
		return l_dichvu;
	}
	
	public static DichVu getDichVu(int madv)
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT * FROM dichvu WHERE madv="+madv);
		
		try
		{
			while(rs.next())
			{
				DichVu dv = new DichVu(rs.getInt(1));

				dv.setTenDV(rs.getString(2));
				dv.setMoTa(rs.getString(3));
				dv.setGia(rs.getInt(4));
				DB.disconnect();
                                
				return dv;
			}
		}
		catch(SQLException e)
		{
			ThongBao.warning("[dichvuDAO:getdichvu] "+e);
		}
		
		DB.disconnect();
		
		return null;
	}
	
	public static int getNewID() {
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT MAX(madv) FROM dichvu");

		try {
			while (rs.next()) {
				int newid = rs.getInt(1) + 1;
				DB.disconnect();
				return newid;
			}
		} catch (SQLException e) {
			ThongBao.warning("[dichvuDAO:getNewID] " + e);
		}

		DB.disconnect();

		return -1;
	}

	public static  void add(DichVu dv)
	{
		Database DB = new Database();
		DB.connect();
		
		String sql = "INSERT INTO dichvu (madv,tendv, mota, gia) VALUES ('";
                sql += dv.getMaDV()+"', '";
		sql += dv.getTenDV()+"', '";
		sql += dv.getMoTa()+"', '";
		sql += dv.getGia()+"');";		
		DB.update(sql);
		DB.disconnect();
	}

	public static void delete(int madv)
	{
		Database DB = new Database();
		DB.connect();
		DB.update("DELETE FROM dichvu WHERE dichvu.madv="+madv);
		DB.disconnect();
	}

	public static void edit(DichVu dv)
	{
		Database DB = new Database();
		DB.connect();
		
		String sql = "UPDATE dichvu SET ";
		sql += "tendv='"					+dv.getTenDV();
		sql += "', mota='"					+dv.getMoTa();
		sql += "', gia='"					+dv.getGia();
		sql += "' WHERE dichvu.madv = "		+dv.getMaDV()+";";
		
		DB.update(sql);
		DB.disconnect();
	}

	public int getGia(int madv)
	{
		Database DB = new Database();
		DB.connect();

		ResultSet rs = DB.execution("SELECT gia FROM dichvu WHERE madv="+madv);
		
		try
		{
			while(rs.next())
			{
				int gia = rs.getInt(1);
				DB.disconnect();
				return gia;
			}
		}
		catch(SQLException e)
		{
			System.out.println("[dichvuDAO:getGia] error sql: "+e);
		}
		
		DB.disconnect();
		
		return 0;
	}

}
