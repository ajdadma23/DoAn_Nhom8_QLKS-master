/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Massan
 */
public class PhieuDichVu {

    
    private final int m_mapdv;
    private  int m_madv;
    private String m_ngaydat;
    private int m_soluong;

    public PhieuDichVu(int m_mapdv) {
        this.m_mapdv = m_mapdv;
    }
    
    

    public PhieuDichVu(int m_mapdv, int m_madv, String m_ngaydat, int m_soluong) {
        this.m_mapdv = m_mapdv;
        this.m_madv = m_madv;
        this.m_ngaydat = m_ngaydat;
        this.m_soluong = m_soluong;
    }

    public  int getM_mapdv(){
        return m_mapdv;
    }
    
    public int getM_madv() {
        return m_madv;
    }

    public void setM_madv(int m_madv) {
        this.m_madv = m_madv;
    }

    public String getM_ngaydat() {
        return m_ngaydat;
    }

    public void setM_ngaydat(String m_ngaydat) {
        this.m_ngaydat = m_ngaydat;
    }

    public int getM_soluong() {
        return m_soluong;
    }

    public void setM_soluong(int m_soluong) {
        this.m_soluong = m_soluong;
    }

}